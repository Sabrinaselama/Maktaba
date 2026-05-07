package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Category
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class SupabaseCategoryRepositoryImpl @Inject constructor(
    private val supabase: SupabaseClient
) : CategoryRepository {

    override fun getAllCategories(): Flow<List<Category>> = flow {
        val categories = supabase
            .from("categories")
            .select()
            .decodeList<Category>()

        emit(categories)
    }

    override fun getCategoryById(id: String): Category? = runBlocking {
        supabase
            .from("categories")
            .select()
            .decodeList<Category>()
            .find { it.id == id }
    }
}
