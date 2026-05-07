package com.ElOuedUniv.maktaba.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

@Module
@InstallIn(SingletonComponent::class)
object SupabaseModule {

    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = "https://dtkecnwvdxjtqrrldezpe.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImR0a2Vjbnd2ZHhqdHFybGRlenBlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzgxMzU4NzQsImV4cCI6MjA5MzcxMTg3NH0.L8D85Tz8h6_bGf5PC7zM5pLeH7QiDySpIlRTe9xrArU"
        ) {
            install(Postgrest)
            install(Storage)
        }
    }
}