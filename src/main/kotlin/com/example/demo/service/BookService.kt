package com.example.demo.service

import com.example.demo.models.BookModel

interface BookService {
    fun getBooks(): List<BookModel>
    fun findBookById(id: Long): BookModel?
    fun addBook(book: BookModel): BookModel
    fun updateBook(book: BookModel): BookModel?
    fun deleteBook(id: Long)
}