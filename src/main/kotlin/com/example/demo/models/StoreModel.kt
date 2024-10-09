package com.example.demo.models

import com.example.demo.models.BookModel
import jakarta.persistence.*
import jakarta.validation.constraints.Size


@Entity
class StoreModel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null,
    @Size(min = 3, max = 20, message = "Адрес должно быть не меньше 3 символов и не больше 20!") private var address: String? = null,
    @OneToMany(cascade = [(CascadeType.REMOVE)], orphanRemoval = true, mappedBy = "store") private val books: MutableList<BookModel> = mutableListOf(),
) {
    fun getAddress(): String? {
        return address
    }
    fun getId(): Long? {
        return id
    }
    fun setId(id: Long?) {
        this.id = id
    }
    fun setAddress(newAddress: String?) {
        this.address = newAddress
    }
    fun getBooks(): MutableList<BookModel> {
        return books
    }
}