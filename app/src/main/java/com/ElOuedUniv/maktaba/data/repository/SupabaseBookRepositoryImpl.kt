package com.ElOuedUniv.maktaba.data.repository

import android.content.Context
import android.net.Uri
import com.ElOuedUniv.maktaba.data.model.Book
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import java.util.UUID
import javax.inject.Inject

class SupabaseBookRepositoryImpl @Inject constructor(
    private val supabase: SupabaseClient,
    @ApplicationContext private val context: Context
) : BookRepository {

    override fun getAllBooks(): Flow<List<Book>> = flow {
        val books = supabase
            .from("books")
            .select()
            .decodeList<Book>()

        emit(books)
    }

    override fun getBookByIsbn(isbn: String): Book? = runBlocking {
        supabase
            .from("books")
            .select()
            .decodeList<Book>()
            .find { it.isbn == isbn }
    }

    override fun addBook(book: Book) {
        runBlocking {
            var finalImageUrl = book.imageUrl

            if (!book.imageUrl.isNullOrBlank() && book.imageUrl.startsWith("content://")) {
                val uri = Uri.parse(book.imageUrl)
                val bytes = context.contentResolver.openInputStream(uri)?.use { it.readBytes() }

                if (bytes != null) {
                    val filePath = "covers/${UUID.randomUUID()}.jpg"
                    val bucket = supabase.storage.from("book_covers")

                    bucket.upload(filePath, bytes)
                    finalImageUrl = bucket.publicUrl(filePath)
                }
            }

            val finalBook = book.copy(imageUrl = finalImageUrl)

            supabase
                .from("books")
                .insert(finalBook)
        }
    }
}
