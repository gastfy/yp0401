package com.example.demo.controllers

import com.example.demo.models.*
import com.example.demo.service.*
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*


@Controller
@PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
class ManagerController {

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
    var orderTypeService: OrderTypeImpl? = null

    @Autowired
    var orderService: OrderImpl? = null

    @GetMapping("/books")
    fun books(model: Model): String {
        model.addAttribute("books", bookService!!.getBooks())
        model.addAttribute("authors", authorService!!.getAuthors())
        model.addAttribute("genres", genreService!!.getGenres())
        model.addAttribute("types", typeService!!.getTypes())
        model.addAttribute("stores", storeService!!.getStores())
        model.addAttribute("book1", BookModel())
        return "books"
    }

    @PostMapping("/books")
    fun createBook(@RequestParam params: Map<String,String>, model: Model): String {
        if(params["name"]!!.length < 3 || params["name"]!!.length > 20){
            return "redirect:/books"
        }

        val book = BookModel(0, params["name"],
            authorService!!.findAuthorById(params["author"]!!.toLong()),
            genreService!!.findGenreById(params["genre"]!!.toLong()),
            typeService!!.findTypeById(params["type"]!!.toLong()),
            storeService!!.findStoreById(params["store"]!!.toLong())
        )

        bookService!!.addBook(book)
        return "redirect:/books"
    }

    @GetMapping("/books/edit/{id}")
    fun editBook(@PathVariable("id") id: Long, model: Model): String {
        val book = bookService!!.findBookById(id)
        model.addAttribute("book", book)
        model.addAttribute("authors", authorService!!.getAuthors())
        model.addAttribute("genres", genreService!!.getGenres())
        model.addAttribute("types", typeService!!.getTypes())
        model.addAttribute("stores", storeService!!.getStores())
        return "book_update"
    }

    @PostMapping("/books/update")
    fun updateBook(@RequestParam params: Map<String,String>, model: Model): String {
        if(params["name"]!!.length < 3 || params["name"]!!.length > 20){
            return "redirect:/books"
        }
        val book = BookModel(params["id"]!!.toLong(), params["name"], authorService!!.findAuthorById(params["author"]!!.toLong()),
            genreService!!.findGenreById(params["genre"]!!.toLong()),
            typeService!!.findTypeById(params["type"]!!.toLong()),
            storeService!!.findStoreById(params["store"]!!.toLong()))
        bookService!!.updateBook(book)
        return "redirect:/books"
    }

    @GetMapping("/books/delete/{id}")
    fun deleteBook(@PathVariable("id") id: Long, model: Model): String {
        bookService!!.deleteBook(id)
        return "redirect:/books"
    }

    //authors

    @GetMapping("/authors")
    fun authors(model: Model): String {
        model.addAttribute("authors", authorService!!.getAuthors())
        model.addAttribute("author", AuthorModel())
        return "authors"
    }

    @PostMapping("/authors")
    fun createAuthor(@RequestParam params: Map<String,String>, model: Model): String {

        if(params["firstName"]!!.length < 3 || params["firstName"]!!.length > 20){
            return "redirect:/authors"
        }
        if(params["lastName"]!!.length < 3 || params["lastName"]!!.length > 20){
            return "redirect:/authors"
        }

        val author = AuthorModel(1, params["firstName"]!!, params["lastName"]!!)
        authorService!!.addAuthor(author)
        return "redirect:/authors"
    }

    @GetMapping("/authors/edit/{id}")
    fun editAuthor(@PathVariable("id") id: Long, model: Model): String {
        val author = authorService!!.findAuthorById(id)
        model.addAttribute("author", author)
        return "update_author"
    }

    @PostMapping("/authors/update")
    fun updateAuthor(@RequestParam params: Map<String,String>, model: Model): String {
        if(params["firstName"]!!.length < 3 || params["firstName"]!!.length > 20){
            return "redirect:/authors"
        }
        if(params["lastName"]!!.length < 3 || params["lastName"]!!.length > 20){
            return "redirect:/authors"
        }
        val books = authorService!!.findAuthorById(params["id"]!!.toLong())!!.getBooks()
        val author = AuthorModel(params["id"]!!.toLong(), params["firstName"]!!, params["lastName"]!!, books.toMutableList())
        authorService!!.updateAuthor(author)
        return "redirect:/authors"
    }

    @GetMapping("/authors/delete/{id}")
    fun deleteAuthor(@PathVariable("id") id: Long, model: Model): String {
        authorService!!.deleteAuthor(id)
        return "redirect:/authors"
    }


    //genres

    @GetMapping("/genres")
    fun genres(model: Model): String {
        model.addAttribute("genres", genreService!!.getGenres())
        model.addAttribute("genre", Genre())
        return "genres"
    }

    @GetMapping("/genres/edit/{id}")
    fun genresEdit(@PathVariable("id") id: Long, model: Model): String {
        val genre = genreService!!.findGenreById(id)
        model.addAttribute("genre", genre)
        return "edit_genre"
    }

    @PostMapping("/genres/update")
    fun updateGenre(@RequestParam params: Map<String,String>, model: Model): String {

        if(params["name"]!!.length < 3 || params["name"]!!.length > 20){
            return "redirect:/genres"
        }

        val books = genreService!!.findGenreById(params["id"]!!.toLong())!!.getBooks()
        val genre = Genre(params["id"]!!.toLong(), params["name"]!!, books)
        genreService!!.updateGenre(genre)
        return "redirect:/genres"
    }

    @PostMapping("/genres")
    fun createGenre(@RequestParam params: Map<String,String>, model: Model): String {
        if(params["name"]!!.length < 3 || params["name"]!!.length > 20){
            return "redirect:/genres"
        }
        val genre = Genre(1, params["name"]!!)
        genreService!!.addGenre(genre)
        return "redirect:/genres"
    }

    @GetMapping("/genres/delete/{id}")
    fun deleteGenre(@PathVariable("id") id: Long, model: Model): String {
        genreService!!.deleteGenre(id)
        return "redirect:/genres"
    }

    //stores

    @GetMapping("/stores")
    fun stores(model: Model): String {
        model.addAttribute("stores", storeService!!.getStores())
        model.addAttribute("store", StoreModel())
        return "stores"
    }

    @GetMapping("/stores/edit/{id}")
    fun storeEdit(@PathVariable("id") id: Long, model: Model): String {
        val store = storeService!!.findStoreById(id)
        model.addAttribute("store", store)
        return "edit_store"
    }

    @PostMapping("/stores/update")
    fun updateStore(@RequestParam params: Map<String,String>, model: Model): String {

        if(params["address"]!!.length < 3 || params["address"]!!.length > 20){
            return "redirect:/stores"
        }

        val books = storeService!!.findStoreById(params["id"]!!.toLong())!!.getBooks()
        val st = StoreModel(params["id"]!!.toLong(), params["address"]!!, books)
        storeService!!.updateStore(st)
        return "redirect:/stores"
    }

    @PostMapping("/stores")
    fun createStore(@RequestParam params: Map<String,String>, model: Model): String {
        if(params["address"]!!.length < 3 || params["address"]!!.length > 20){
            return "redirect:/stores"
        }
        val store = StoreModel(1, params["address"]!!)
        storeService!!.addStore(store)
        return "redirect:/stores"
    }

    @GetMapping("/stores/delete/{id}")
    fun deleteStore(@PathVariable("id") id: Long, model: Model): String {
        storeService!!.deleteStore(id)
        return "redirect:/stores"
    }

    //types

    @GetMapping("/types")
    fun types(model: Model): String {
        model.addAttribute("types", typeService!!.getTypes())
        model.addAttribute("type", TypeModel())
        return "types"
    }

    @GetMapping("/types/edit/{id}")
    fun typeEdit(@PathVariable("id") id: Long, model: Model): String {
        val type = typeService!!.findTypeById(id)
        model.addAttribute("type", type)
        return "edit_type"
    }

    @PostMapping("/types/update")
    fun updateType(@RequestParam params: Map<String,String>, model: Model): String {

        if(params["name"]!!.length < 3 || params["name"]!!.length > 20){
            return "redirect:/types"
        }

        val books = typeService!!.findTypeById(params["id"]!!.toLong())!!.getBooks()
        val type = TypeModel(params["id"]!!.toLong(), params["name"]!!, books.toMutableList())
        typeService!!.updateType(type)
        return "redirect:/types"
    }

    @PostMapping("/types")
    fun createType(@RequestParam params: Map<String,String>, model: Model): String {
        if(params["name"]!!.length < 3 || params["name"]!!.length > 20){
            return "redirect:/types"
        }
        val type = TypeModel(1, params["name"]!!)
        typeService!!.addType(type)
        return "redirect:/types"
    }

    @GetMapping("/types/delete/{id}")
    fun deleteType(@PathVariable("id") id: Long, model: Model): String {
        typeService!!.deleteType(id)
        return "redirect:/types"
    }

    // order type

    @GetMapping("/orderTypes")
    fun orderTypes(model: Model): String {
        model.addAttribute("types", orderTypeService!!.getOrderTypes())
        model.addAttribute("orderType", OrderTypeModel())
        return "orderTypes"
    }

    @GetMapping("/orderTypes/edit/{id}")
    fun orderTypesEdit(@PathVariable("id") id: Long, model: Model): String {
        val type = orderTypeService!!.findOrderTypeById(id)
        model.addAttribute("orderType", type)
        return "edit_orderType"
    }

    @PostMapping("/orderTypes/update")
    fun updateOrderType(@RequestParam params: Map<String,String>, model: Model): String {

        if(params["type"]!!.length < 3 || params["type"]!!.length > 20){
            return "redirect:/orderTypes"
        }

        val orders = orderTypeService!!.findOrderTypeById(params["id"]!!.toLong())!!.getOrders()
        val type = OrderTypeModel(params["id"]!!.toLong(), params["type"]!!, orders)
        orderTypeService!!.updateOrderType(type)
        return "redirect:/orderTypes"
    }

    @PostMapping("/orderTypes")
    fun createOrderType(@RequestParam params: Map<String,String>, model: Model): String {
        if(params["type"]!!.length < 3 || params["type"]!!.length > 20){
            return "redirect:/orderTypes"
        }
        val type = OrderTypeModel(1, params["type"]!!)
        orderTypeService!!.addOrderType(type)
        return "redirect:/orderTypes"
    }

    @GetMapping("/orderTypes/delete/{id}")
    fun deleteOrderType(@PathVariable("id") id: Long, model: Model): String {
        orderTypeService!!.deleteOrderType(id)
        return "redirect:/orderTypes"
    }

    //orders

    @GetMapping("/ordersList")
    fun orderList(model: Model): String {
        model.addAttribute("orders", orderService!!.getOrders())
        return "ordersList"
    }

    @GetMapping("/ordersList/edit/{id}")
    fun orderListEdit(@PathVariable("id") id: Long, model: Model): String {
        model.addAttribute("order", orderService!!.findOrderById(id))
        model.addAttribute("types", orderTypeService!!.getOrderTypes())
        return "orderEditType"
    }

    @PostMapping("/ordersList/update")
    fun updateOrderList(@RequestParam params: Map<String,String>, model: Model): String {
        val order = orderService!!.findOrderById(params["id"]!!.toLong())
        order!!.setType(orderTypeService!!.findOrderTypeById(params["type"]!!.toLong())!!)
        orderService!!.updateOrder(order)
        return "redirect:/ordersList"
    }

}