package com.example.demo.service

import com.example.demo.models.ModelUser

interface UserService {
    fun getUsers(): MutableList<ModelUser>
    fun addUser(user: ModelUser) : ModelUser
    fun getUser(id: Long): ModelUser?
    fun updateUser(user: ModelUser) : ModelUser?
    fun deleteUser(id: Long)
    fun findByUserName(userName: String): ModelUser?
}