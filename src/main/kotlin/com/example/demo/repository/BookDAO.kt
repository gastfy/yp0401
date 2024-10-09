package com.example.demo.repository

import com.example.demo.errors.BookNotFound
import com.example.demo.models.BookModel
import org.springframework.stereotype.Repository
import java.awt.print.Book
import java.util.concurrent.atomic.AtomicLong
import java.util.stream.IntStream

@Repository
class BookDAO(private val books: MutableList<BookModel>) {

    private val idCounter = AtomicLong(1)

    fun getBooks(): List<BookModel> {
        return books
    }

    fun findBookById(id: Long): BookModel? {
        return books.stream().filter { el -> el.getId() == id }.findFirst().orElse(null)
    }

    fun addBook(book: BookModel): BookModel {
        book.setId(idCounter.incrementAndGet())
        books.add(book)
        return book
    }

    fun updateBook(book: BookModel): BookModel? {
        val bookInd = IntStream.range(0, books.size)
            .filter { ind -> books[ind].getId() == book.getId() }
            .findFirst()
            .orElse(-1)
        if (bookInd == -1) {
            return null
        }
        books[bookInd] = book
        return books[bookInd]
    }

    fun deleteBook(id: Long) {
        val book = findBookById(id)
        if (book != null) {
            books.remove(book)
        }
    }



}