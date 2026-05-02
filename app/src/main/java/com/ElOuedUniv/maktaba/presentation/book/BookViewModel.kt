package com.ElOuedUniv.maktaba.presentation.book

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ElOuedUniv.maktaba.domain.usecase.GetBooksUseCase
import com.ElOuedUniv.maktaba.domain.usecase.AddBookUseCase
import com.ElOuedUniv.maktaba.data.model.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

@HiltViewModel
class BookViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase,
    private val addBookUseCase: AddBookUseCase
) : ViewModel() {

    private val _uiEvent = Channel<String>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiState = MutableStateFlow(BookUiState())
    val uiState: StateFlow<BookUiState> = _uiState.asStateFlow()

    init {
        loadBooks()
    }

    fun loadBooks() {
        viewModelScope.launch {
            getBooksUseCase()
                .onStart {
                    _uiState.update { it.copy(isLoading = true) }
                }
                .catch {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = "Error loading books"
                        )
                    }
                }
                .collect { bookList ->
                    _uiState.update {
                        it.copy(
                            books = bookList,
                            isLoading = false
                        )
                    }
                }
        }
    }

    fun onAction(action: BookUiAction) {
        when (action) {

            BookUiAction.RefreshBooks -> refreshBooks()

            BookUiAction.OnAddBookClick -> {
                _uiState.update { it.copy(isAddingBook = true) }
            }

            BookUiAction.OnDismissAddBook -> {
                _uiState.update { it.copy(isAddingBook = false) }
            }

            is BookUiAction.OnAddBookConfirm -> {
                viewModelScope.launch {

                    val newBook = Book(
                        isbn = action.isbn,
                        title = action.title,
                        nbPages = action.nbPages
                    )

                    addBookUseCase(newBook)

                    _uiEvent.send("Book added successfully")

                    _uiState.update {
                        it.copy(isAddingBook = false)
                    }

                }
            }
        }
    }

    fun refreshBooks() {
        loadBooks()
    }

}



