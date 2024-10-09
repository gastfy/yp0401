package com.example.demo.service

import com.example.demo.models.ModelUser
import com.example.demo.repository.UserDAO
import org.springframework.stereotype.Service

@Service
class UserImpl(private val repo: UserDAO) : UserService {
    override fun getUsers(): MutableList<ModelUser> {
        return repo.getUsers()
    }

    override fun addUser(user: ModelUser) : ModelUser {
        return repo.addUser(user)
    }

    override fun getUser(id: Long): ModelUser? {
        return repo.getUser(id)
    }

    override fun updateUser(user: ModelUser): ModelUser? {
        return repo.updateUser(user)
    }

    override fun deleteUser(id: Long) {
        repo.deleteUser(id)
    }

    override fun findByUserName(userName: String): ModelUser? {
        return repo.findByUserName(userName)
    }
}