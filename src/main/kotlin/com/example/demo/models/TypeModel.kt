package com.example.demo.models

import com.example.demo.models.BookModel
import jakarta.persistence.*
import jakarta.validation.constraints.Size


@Entity
class TypeModel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null,
    @Size(min = 3, max = 20, message = "Имя должно быть не меньше 3 символов и не больше 20!") private var name: String? = null,
    @OneToMany(cascade = [(CascadeType.REMOVE)], orphanRemoval = true, mappedBy = "type") private val books: MutableList<BookModel> = mutableListOf(),
) {
    fun getId(): Long {
        return id!!
    }
    fun setId(id: Long) {
        this.id = id
    }
    fun getName(): String? {
        return name
    }
    fun setName(name: String) {
        this.name = name
    }
    fun getBooks(): List<BookModel> {
        return books
    }
}