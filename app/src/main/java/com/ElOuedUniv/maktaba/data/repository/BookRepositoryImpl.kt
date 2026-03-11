package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Book

class BookRepositoryImpl : BookRepository {

    private val booksList = listOf(Book(isbn = "978-0132350884", title = "Clean Code", nbPages = 464),
        Book(isbn = "978-0201616224", title = "The Pragmatic Programmer", nbPages = 352),
        Book(isbn = "978-0201633610", title = "Design Patterns", nbPages =395 ),
        Book(isbn = "978-0201485677", title = "Refactoring", nbPages =448 ),
        Book(isbn="978-1491950357", title="Learning Python", nbPages=1648),
        Book( isbn="978-0596007126", title="Head First Design Patterns", nbPages=694),
        Book(isbn="978-0131103627", title="The C Programming Language", nbPages=272),
        Book(isbn="978-0134685991", title="Effective Java", nbPages=416),
        Book(isbn="978-0596007126", title="Head First Design Patterns", nbPages=6)

    )

    override fun getAllBooks(): List<Book> {
        return booksList
    }

    override fun getBookByIsbn(isbn: String): Book? {
        return booksList.find { it.isbn == isbn }
    }
}
