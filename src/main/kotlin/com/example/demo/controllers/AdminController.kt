package com.example.demo.controllers

import com.example.demo.models.ModelUser
import com.example.demo.models.OrderTypeModel
import com.example.demo.models.RoleEnum
import com.example.demo.service.UserImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*


@PreAuthorize("hasAnyAuthority('ADMIN')")
@Controller
class AdminController {

    @Autowired
    var userService: UserImpl? = null

    private val passwordEncoder: PasswordEncoder = BCryptPasswordEncoder(4)

    @GetMapping("/users")
    fun users(model: Model): String {

        val auth: Authentication = SecurityContextHolder.getContext().authentication
        var user: ModelUser? = null
        user = if (auth.name.toString() == "anonymousUser"){
            userService!!.getUsers().first()
        } else{
            userService!!.findByUserName(auth.name)
        }

        model.addAttribute("users", userService!!.getUsers().filter { u -> u.getId() != user!!.getId() })
        model.addAttribute("user", ModelUser())
        model.addAttribute("roles", RoleEnum.entries.toTypedArray())
        return "users"
    }

    @GetMapping("/users/edit/{id}")
    fun userEdit(@PathVariable("id") id: Long, model: Model): String {
        val user = userService!!.getUser(id)
        model.addAttribute("user", user)
        model.addAttribute("roles", RoleEnum.entries.toTypedArray())
        return "edit_user"
    }

    @PostMapping("/users/update")
    fun updateUser(@RequestParam params: Map<String,String>, model: Model): String {
        if(params["name"] == null || params["name"]!!.length < 3 || params["name"]!!.length > 20){
            return "redirect:/users"
        }
        if(params["username"] == null || params["username"]!!.length < 3 || params["username"]!!.length > 20){
            return "redirect:/users"
        }
        if (params["username"] == null || userService!!.findByUserName(params["username"]!!) != null) {
            model.addAttribute("message", "Пользователь с таким логином уже существует")
            return "redirect:/users"
        }
        if(params["password"] == null || params["password"]!!.length < 6) {
            model.addAttribute("message", "Пароль меньше 6 символов!")
            return "redirect:/users"
        }
        if(params["password"] == null || params["password"]!!.filter { it.isDigit() }.firstOrNull() == null) {
            model.addAttribute("message", "Пароль не содержит цифры!")
            return "redirect:/users"
        }
        if(params["password"] == null || params["username"]!!.isEmpty()) {
            model.addAttribute("message", "Логин не заполнен!")
            return "redirect:/users"
        }

        val user = ModelUser(params["id"]!!.toLong(), params["name"]!!, params["username"]!!, params["password"]!!, mutableSetOf(RoleEnum.valueOf(params["roles"]!!)), true)
        user.setPassword((passwordEncoder.encode(user.getPassword())))
        userService!!.updateUser(user)
        return "redirect:/users"
    }

    @PostMapping("/users")
    fun createUser(@RequestParam params: Map<String,String>, model: Model): String {
        if(params["name"] == null || params["name"]!!.length < 3 || params["name"]!!.length > 20){
            return "redirect:/users"
        }
        if(params["username"] == null || params["username"]!!.length < 3 || params["username"]!!.length > 20){
            return "redirect:/users"
        }
        if (params["username"] == null || userService!!.findByUserName(params["username"]!!) != null) {
            model.addAttribute("message", "Пользователь с таким логином уже существует")
            return "redirect:/users"
        }
        if(params["password"] == null || params["password"]!!.length < 6) {
            model.addAttribute("message", "Пароль меньше 6 символов!")
            return "redirect:/users"
        }
        if(params["password"] == null || params["password"]!!.filter { it.isDigit() }.firstOrNull() == null) {
            model.addAttribute("message", "Пароль не содержит цифры!")
            return "redirect:/users"
        }
        if(params["password"] == null || params["username"]!!.isEmpty()) {
            model.addAttribute("message", "Логин не заполнен!")
            return "redirect:/users"
        }

        val user = ModelUser(1, params["name"]!!, params["username"]!!, params["password"]!!, mutableSetOf(RoleEnum.valueOf(params["roles"]!!)), true)
        user.setPassword((passwordEncoder.encode(user.getPassword())))
        userService!!.addUser(user)
        return "redirect:/users"
    }

    @GetMapping("/users/delete/{id}")
    fun deleteOrderType(@PathVariable("id") id: Long, model: Model): String {
        userService!!.deleteUser(id)
        return "redirect:/users"
    }


}