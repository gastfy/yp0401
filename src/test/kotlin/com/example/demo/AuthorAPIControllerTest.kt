package com.example.demo

import com.example.demo.controllers.AuthorAPI
import com.example.demo.models.AuthorModel
import com.example.demo.service.AuthorService
import com.google.gson.Gson
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(AuthorAPI::class)
class AuthorAPIControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var authorService: AuthorService

    @BeforeEach
    fun setUp() { MockitoAnnotations.openMocks(this); }

    @Test
    fun testCreatingNewAuthor() {
        val author = AuthorModel(1, "First name", "Last name")

        Mockito.`when`(authorService.addAuthor(AuthorModel(1))).thenReturn(author)

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/api/authors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(Gson().toJson(author))
        ).andExpect(status().is2xxSuccessful)
    }

    @Test
    fun testGetAllAuthors() {
        Mockito.`when`(authorService.getAuthors()).thenReturn(arrayListOf(AuthorModel(1, "First name", "Last name")))

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/authors"))
            .andExpect(status().is2xxSuccessful)
    }
}