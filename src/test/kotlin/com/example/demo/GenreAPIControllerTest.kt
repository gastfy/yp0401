package com.example.demo

import com.example.demo.controllers.GenreAPI
import com.example.demo.models.Genre
import com.example.demo.service.GenreService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.*
import org.mockito.ArgumentMatchers.any
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(GenreAPI::class)
class GenreAPIControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var genreService : GenreService

    @BeforeEach
    fun setUp() { MockitoAnnotations.openMocks(this); }

    @Test
    fun testCreateGenre(){
        val genre = Genre(1, "Жанр 1")

        Mockito.`when`(genreService.addGenre(Genre(1))).thenReturn(genre)

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/api/genres")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"Жанр 1\"}"))
            .andExpect(status().is2xxSuccessful)
    }

    @Test
    fun testGetGenreById(){
        val genreId = 1L

        genreService.addGenre(Genre(genreId))

        Mockito.`when`(genreService.findGenreById(genreId)).thenReturn(Genre(id = genreId))
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/genres/$genreId"))
            .andExpect(status().is2xxSuccessful)
    }
}