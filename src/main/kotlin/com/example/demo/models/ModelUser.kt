package com.example.demo.models

import jakarta.persistence.*
import jakarta.validation.constraints.Size

@Entity
class ModelUser(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) private var id: Long? = null,
    @Size(min = 2, max = 20, message = "Имя должно быть не меньше 3 символов и не больше 20!") private var name: String? = null,
    @Size(min = 2, max = 20, message = "Логин должно быть не меньше 3 символов и не больше 20!") private var username: String? = null,
    @Size(min = 2, max = 20, message = "Пароль должно быть не меньше 3 символов и не больше 20!") private var password: String? = null,
    @ElementCollection(targetClass = RoleEnum::class, fetch = FetchType.EAGER) @CollectionTable(
        name = "user_role",
        joinColumns = [JoinColumn(name = "user_id")]
    ) @Enumerated(EnumType.STRING)
    private var roles: MutableSet<RoleEnum?>? = null,
    private var active: Boolean = true,
) {
    fun getId(): Long {
        return id ?: 0
    }
    fun setId(id: Long) {
        this.id = id
    }
    fun getUsername(): String? {
        return username
    }
    fun getPassword(): String? {
        return password
    }
    fun getName(): String? {
        return name
    }
    fun getRoles(): MutableSet<RoleEnum?>? {
        return roles
    }
    fun setActive(active: Boolean) {
        this.active = active
    }
    fun getActive(): Boolean {
        return active
    }
    fun setRoles(roles: MutableSet<RoleEnum?>?) {
        this.roles = roles
    }
    fun setPassword(password: String?) {
        this.password = password
    }
    fun getData() : String{
        var data = ""
        roles!!.forEach { data += (it!!.name + "; ") }
        return "ID: ${id}, Имя: ${name};\n${username}:${password}\nРоли: ${data}"
    }
}