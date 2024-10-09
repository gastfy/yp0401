package com.example.demo.models

import jakarta.persistence.*


@Entity
class Order(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null,
    @OneToMany(cascade = [(CascadeType.REMOVE)], orphanRemoval = true, mappedBy = "order") private var books: MutableList<BookModel> = mutableListOf(),
    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
    private var type: OrderTypeModel? = null,
    @ManyToMany(cascade = [(CascadeType.REMOVE)], fetch = FetchType.EAGER) private var users: MutableList<ModelUser> = mutableListOf(),
) {
    fun setId(id: Long?) {
        this.id = id
    }
    fun getId(): Long? = id
    fun addBook(book: BookModel) {
        books.add(book)
    }
    fun getBooks(): MutableList<BookModel> = books
    fun getType(): OrderTypeModel? = type
    fun setType(type: OrderTypeModel) {
        this.type = type
    }
}