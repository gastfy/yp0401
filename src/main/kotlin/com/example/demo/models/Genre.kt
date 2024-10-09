package com.example.demo.models

import com.example.demo.models.BookModel
import jakarta.persistence.*
import jakarta.validation.constraints.Size

@Entity
class Genre(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long = 1,
    @Size(min = 3, max = 20, message = "Имя должно быть не меньше 3 символов и не больше 20!") private var name: String = "",
    @OneToMany(cascade = [(CascadeType.REMOVE)], orphanRemoval = true, mappedBy = "genre") private val books: MutableSet<BookModel> = mutableSetOf(),
) {
    fun getId(): Long = id
    fun setId(id: Long) {
        this.id = id
    }
    fun getName() = name
    fun setName(name: String) {
        this.name = name
    }
    fun getBooks() = books
    fun setBooks(books: MutableSet<BookModel>) {
        this.books.clear()
        this.books.addAll(books)
    }
}