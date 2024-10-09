package com.example.demo.models

import jakarta.persistence.*
import jakarta.validation.constraints.Size

@Entity
class AuthorModel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null,
    @Size(min = 2, max = 20, message = "Имя должно быть не меньше 3 символов и не больше 20!") private var firstName: String = "",
    @Size(min = 2, max = 20, message = "Фамилия должна быть не меньше 3 символов и не больше 20!") private var lastName: String = "",
    @OneToMany(cascade = [CascadeType.REMOVE], orphanRemoval = true, mappedBy = "author") private val books: MutableList<BookModel> = mutableListOf(),
) {
    fun getId(): Long? = id
    fun setId(id: Long?) {
        this.id = id
    }
    fun getFirstName(): String = this.firstName
    fun getLastName(): String = this.lastName
    fun getBooks(): List<BookModel> = books
    fun setBooks(books: List<BookModel>) {
        this.books.clear()
        this.books.addAll(books)
    }
    fun setFirstName(firstName: String) {
        this.firstName = firstName
    }
    fun setLastName(lastName: String) {
        this.lastName = lastName
    }
    fun getTotalName() : String {
        return "$firstName $lastName"
    }
}