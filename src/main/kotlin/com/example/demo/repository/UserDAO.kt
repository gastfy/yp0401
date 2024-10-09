package com.example.demo.repository

import com.example.demo.models.ModelUser
import org.springframework.stereotype.Repository
import java.util.concurrent.atomic.AtomicLong
import java.util.stream.IntStream


@Repository
class UserDAO(private var users: MutableList<ModelUser>) {
    private val idCounter = AtomicLong(1)

    fun getUsers(): MutableList<ModelUser> {
        return users
    }

    fun addUser(user: ModelUser) : ModelUser {
        user.setId(idCounter.incrementAndGet())
        users.add(user)
        return user
    }

    fun getUser(id: Long): ModelUser? {
        return users.stream().filter { el -> el.getId() == id }.findFirst().orElse(null)
    }

    fun updateUser(user: ModelUser) : ModelUser? {
        val genInd = IntStream.range(0, users.size)
            .filter { ind -> users[ind].getId() == user.getId() }
            .findFirst()
            .orElse(-1)
        if (genInd == -1){
            return null
        }
        users[genInd] = user
        return user
    }

    fun deleteUser(id: Long) {
        val user = getUser(id)
        if (user != null) {
            users.remove(user)
        }
    }

    fun findByUserName(userName: String): ModelUser? {
        val id = IntStream.range(0, users.size)
            .filter { ind -> users[ind].getUsername() == userName}
            .findFirst()
            .orElse(-1)
        if (id == -1){
            return null
        }
        return users[id]
    }

}