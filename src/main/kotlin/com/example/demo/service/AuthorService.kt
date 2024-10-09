package com.example.demo.service

import com.example.demo.models.AuthorModel

interface AuthorService {
    fun getAuthors(): List<AuthorModel>
    fun findAuthorById(id: Long): AuthorModel?
    fun addAuthor(author: AuthorModel): AuthorModel
    fun updateAuthor(author: AuthorModel): AuthorModel?
    fun deleteAuthor(id: Long)
}