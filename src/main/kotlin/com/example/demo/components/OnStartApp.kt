package com.example.demo.components

import com.example.demo.models.*
import com.example.demo.service.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component


@Component
class OnStartApp {

    @Autowired
    var userService: UserImpl? = null

    @Autowired
    var bookService: BookImpl? = null

    @Autowired
    var authorService: AuthorImpl? = null

    @Autowired
    var genreService: GenreImpl? = null

    @Autowired
    var storeService: StoreImpl? = null

    @Autowired
    var typeService: TypeImpl? = null

    @Autowired
    var orderService: OrderImpl? = null

    @Autowired
    var orderTypeService: OrderTypeImpl? = null

    private val passwordEncoder: PasswordEncoder = BCryptPasswordEncoder(4)

    @EventListener
    fun appReady(event: ApplicationReadyEvent) {
        val user = ModelUser(1, "adminname", "admin", "admin123", mutableSetOf(RoleEnum.ADMIN), true)
        user.setPassword((passwordEncoder.encode(user.getPassword())))
        val user1 = ModelUser(1, "username", "user", "user123", mutableSetOf(RoleEnum.USER), true)
        user1.setPassword((passwordEncoder.encode(user1.getPassword())))
        val user2 = ModelUser(2, "managername", "manager", "manager123", mutableSetOf(RoleEnum.MANAGER), true)
        user2.setPassword((passwordEncoder.encode(user2.getPassword())))

        userService!!.addUser(user)
        userService!!.addUser(user1)
        userService!!.addUser(user2)
        println("Users added successfully")

        val author = AuthorModel(1, "Имя автора 1", "Фамилия его")
        authorService!!.addAuthor(author)

        val genre = Genre(1, "Жанр 1")
        genreService!!.addGenre(genre)

        val store = StoreModel(1, "Адрес 1")
        storeService!!.addStore(store)
        val type = TypeModel(1, "Электронная")
        typeService!!.addType(type)
        val type2 = TypeModel(2, "Бумажная")
        typeService!!.addType(type2)
        val book = BookModel(0, "Книга 1", author, genre, type, store)
        bookService!!.addBook(book)
        book.setId(1)
        val book2 = BookModel(1, "Книга 2", author, genre, type2, store)
        bookService!!.addBook(book2)
        book2.setId(2)
        val book3 = BookModel(2, "Книга 3", author, genre, type2, store)
        bookService!!.addBook(book3)
        book3.setId(3)
        println("Books added successfully")
        bookService!!.getBooks().forEach { println(it.getData()) }

        val orderType = OrderTypeModel(1, "Заказ собирается")
        val orderType2 = OrderTypeModel(2, "Заказ ожидает выдачи")
        val orderType3 = OrderTypeModel(3, "Заказ в доставке")
        val orderType4 = OrderTypeModel(4, "Заказ выдан")
        orderTypeService!!.addOrderType(orderType)
        orderTypeService!!.addOrderType(orderType2)
        orderTypeService!!.addOrderType(orderType3)
        orderTypeService!!.addOrderType(orderType4)
        val order = Order(1, mutableListOf(book), orderType)
        orderService!!.addOrder(order)
        println("Order added successfully")
    }
}