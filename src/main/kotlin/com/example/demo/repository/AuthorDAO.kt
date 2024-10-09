package com.example.demo.repository

import com.example.demo.models.AuthorModel

import org.springframework.stereotype.Repository
import java.util.concurrent.atomic.AtomicLong
import java.util.stream.IntStream


@Repository
class AuthorDAO(private val authors: MutableList<AuthorModel>) {

    private val idCounter = AtomicLong(1)

    fun getAuthors(): List<AuthorModel> {
        return authors
    }
    fun findAuthorById(id: Long): AuthorModel? {
        return authors
            .stream()
            .filter { el -> el.getId()!! == id }
            .findFirst()
            .orElse(null)
    }
    fun addAuthor(author: AuthorModel): AuthorModel {
        author.setId(idCounter.incrementAndGet())
        authors.add(author)
        return author
    }
    fun updateAuthor(author: AuthorModel): AuthorModel? {
        val index = IntStream.range(0, authors.size)
            .filter{index -> authors[index].getId()!! == author.getId() }
            .findFirst()
            .orElse(-1)
        if (index == -1){
            return null
        }
        authors[index] = author
        return authors[index]
    }
    fun deleteAuthor(id: Long) {
        val au = findAuthorById(id)
        if (au != null) {
            authors.remove(au)
        }
    }

}