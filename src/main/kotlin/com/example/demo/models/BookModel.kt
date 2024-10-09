package com.example.demo.models

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@Entity
class BookModel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null,
    @Size(min = 3, max = 20, message = "Имя должно быть не меньше 3 символов и не больше 20!")
    private var name: String? = null,
    @NotNull @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    private var author: AuthorModel? = null,
    @NotNull @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "genre_id", referencedColumnName = "id", nullable = false)
    private var genre: Genre? = null,
    @NotNull @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
    private var type: TypeModel? = null,
    @NotNull @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "store_id", referencedColumnName = "id", nullable = false)
    private var store: StoreModel? = null,
    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private var order: Order? = null,
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
    fun getAuthor(): AuthorModel? {
        return author
    }
    fun setAuthor(author: AuthorModel) {
        this.author = author
    }
    fun getGenre(): Genre? {
        return genre
    }
    fun setGenre(genre: Genre) {
        this.genre = genre
    }
    fun getData() : String {
        return "${id}: ${name}, ${author}, ${genre}, ${type}, ${store}, $order"
    }
    fun getType() : TypeModel? {
        return type
    }
    fun getDataForOrder(): String {
        return "$name - Адрес магазина: ${store!!.getAddress()}"
    }
    fun getStore(): StoreModel? {
        return store
    }
}