package com.example.demo.service

import com.example.demo.service.AuthorService
import com.example.demo.models.AuthorModel
import com.example.demo.repository.AuthorDAO
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Service
class AuthorImpl(private val repository: AuthorDAO) : AuthorService {

    override fun getAuthors(): List<AuthorModel> {
        return repository.getAuthors()
    }

    override fun findAuthorById(id: Long): AuthorModel? {
        return repository.findAuthorById(id)
    }

    override fun addAuthor(author: AuthorModel): AuthorModel {
        return repository.addAuthor(author)
    }

    override fun updateAuthor(author: AuthorModel): AuthorModel? {
        return repository.updateAuthor(author)
    }

    override fun deleteAuthor(id: Long) {
        repository.deleteAuthor(id)
    }
}