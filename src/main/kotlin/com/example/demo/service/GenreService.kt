package com.example.demo.service

import com.example.demo.models.Genre

interface GenreService {
    fun getGenres(): List<Genre>
    fun addGenre(genre: Genre): Genre
    fun findGenreById(id: Long): Genre?
    fun updateGenre(genre: Genre): Genre?
    fun deleteGenre(id: Long)
}