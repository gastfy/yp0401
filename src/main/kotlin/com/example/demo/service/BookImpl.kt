package com.example.demo.service

import com.example.demo.models.BookModel
import com.example.demo.repository.BookDAO
import org.springframework.stereotype.Service

@Service
class BookImpl(private val repos: BookDAO) : BookService {
    override fun getBooks(): List<BookModel> {
        return repos.getBooks()
    }

    override fun findBookById(id: Long): BookModel? {
        return repos.findBookById(id)
    }

    override fun addBook(book: BookModel): BookModel {
        return repos.addBook(book)
    }

    override fun updateBook(book: BookModel): BookModel? {
        return repos.updateBook(book)
    }

    override fun deleteBook(id: Long) {
        repos.deleteBook(id)
    }
}