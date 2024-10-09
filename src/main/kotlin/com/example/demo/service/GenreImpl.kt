package com.example.demo.service

import com.example.demo.models.Genre
import com.example.demo.repository.GenreDAO
import org.springframework.stereotype.Service

@Service
class GenreImpl(private val repos: GenreDAO) : GenreService {
    override fun getGenres(): List<Genre> {
        return repos.getGenres()
    }

    override fun addGenre(genre: Genre): Genre {
        return repos.addGenre(genre)
    }

    override fun findGenreById(id: Long): Genre? {
        return repos.findGenreById(id)
    }

    override fun updateGenre(genre: Genre): Genre? {
        return repos.updateGenre(genre)
    }

    override fun deleteGenre(id: Long) {
        repos.deleteGenre(id)
    }
}