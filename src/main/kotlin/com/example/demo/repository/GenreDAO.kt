package com.example.demo.repository

import com.example.demo.errors.GenreNotFound
import com.example.demo.models.Genre
import org.springframework.stereotype.Repository
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong
import java.util.stream.IntStream


@Repository
class GenreDAO(private val genres: MutableList<Genre>) {

    private val idCounter = AtomicLong(1)

    fun getGenres(): List<Genre> {
        return genres
    }

    fun addGenre(genre: Genre): Genre {
        genre.setId(idCounter.getAndIncrement())
        genres.add(genre)
        return genre
    }

    fun findGenreById(id: Long): Genre? {
        return genres.stream().filter { el -> el.getId() == id }.findFirst().orElse(null)
    }

    fun updateGenre(genre: Genre): Genre? {
        val genInd = IntStream.range(0, genres.size)
            .filter { ind -> genres[ind].getId() == genre.getId() }
            .findFirst()
            .orElse(-1)
        if (genInd == -1){
            return null
        }
        genres[genInd] = genre
        return genre
    }

    fun deleteGenre(id: Long) {
        val genre = findGenreById(id)
        if (genre != null) {
            genres.remove(genre)
        }
    }




}