package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Book
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor() : BookRepository {

    private val _booksList = mutableListOf(
        Book(
            isbn = "978-0132350884",
            title = "Clean Code",
            nbPages = 464,
            imageUrl = "https://m.media-amazon.com/images/I/41xShlnTZTL._SX376_BO1,204,203,200_.jpg",
            status = "Finished"
        ),
        Book(
            isbn = "978-0135957059",
            title = "The Pragmatic Programmer",
            nbPages = 352,
            imageUrl = "https://images-na.ssl-images-amazon.com/images/I/41as+4j3HEL._SX396_BO1,204,203,200_.jpg",
            status = "Reading"
        ),
        Book(
            isbn = "978-0201633610",
            title = "Design Patterns",
            nbPages = 660,
            imageUrl = "https://m.media-amazon.com/images/I/51szD9HC9pL._SX395_BO1,204,203,200_.jpg",
            status = "Reading"
        ),
        Book(
            isbn = "978-0134757599",
            title = "Refactoring",
            nbPages = 448,
            imageUrl = "https://images-na.ssl-images-amazon.com/images/I/418pS8S87LL._SX396_BO1,204,203,200_.jpg",
            status = "Finished"
        ),
        Book(
            isbn = "978-0596007126",
            title = "Head First Design Patterns",
            nbPages = 638,
            imageUrl = "https://images-na.ssl-images-amazon.com/images/I/51S3828G6DL._SX396_BO1,204,203,200_.jpg",
            status = "Reading"
        )
    )

    private val booksFlow = MutableSharedFlow<List<Book>>(replay = 1).apply {
        tryEmit(_booksList.toList())
    }

    override fun getAllBooks(): Flow<List<Book>> = flow {
        delay(1000) // Simulate delay
        emitAll(booksFlow)
    }

    override fun getBookByIsbn(isbn: String): Book? {
        return _booksList.find { it.isbn == isbn }
    }

    override fun addBook(book: Book) {
        _booksList.add(book)
        booksFlow.tryEmit(_booksList.toList())
    }
}