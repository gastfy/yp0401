package com.example.demo

import com.example.demo.controllers.BookAPI
import com.example.demo.models.AuthorModel
import com.example.demo.models.BookModel
import com.example.demo.models.Genre
import com.example.demo.service.AuthorService
import com.example.demo.service.BookService
import com.example.demo.service.GenreService
import com.google.gson.Gson
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.doNothing
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.awt.print.Book
import java.time.LocalDate
import java.util.Date

@WebMvcTest(BookAPI::class)
class BookAPIControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var bookService: BookService

    @MockBean
    private lateinit var authorService: AuthorService

    @MockBean
    private lateinit var genreService : GenreService

    @BeforeEach
    fun setUp() { MockitoAnnotations.openMocks(this); }

    @Test
    fun testCreateBook() {
        val author = AuthorModel(1, "John", "Smith")
        val genre = Genre(1, "Book 1 genre")
        authorService.addAuthor(author)
        genreService.addGenre(genre)

        val book = BookModel(1, "book 1", author, genre)
        Mockito.`when`(bookService.addBook(book)).thenReturn(book)
        mockMvc.perform(post("/v1/api/books")
            .contentType(MediaType.APPLICATION_JSON)
            .content(Gson().toJson(book))
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
    }

    @Test
    fun testDeleteBook() {
        val author = AuthorModel(1, "John", "Smith")
        val genre = Genre(1, "Book 1 genre")
        authorService.addAuthor(author)
        genreService.addGenre(genre)
        val book = BookModel(1, "book 1", author, genre)
        bookService.addBook(book)

        val id = 1L;

        doNothing().`when`(bookService).deleteBook(id)
        mockMvc.perform(delete("/v1/api/books/$id"))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
    }
}