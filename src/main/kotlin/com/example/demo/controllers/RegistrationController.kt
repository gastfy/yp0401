package com.example.demo.controllers

import com.example.demo.models.ModelUser
import com.example.demo.models.RoleEnum
import com.example.demo.repository.UserDAO
import com.example.demo.service.UserImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.postForObject


@Controller
class RegistrationController() {

    @Autowired
    var userService: UserImpl? = null

    private val passwordEncoder: PasswordEncoder = BCryptPasswordEncoder(4)

    @GetMapping("/reg")
    fun regView(): String {
        return "regis"
    }

    @PostMapping("/reg")
    fun reg(user: ModelUser, model: Model): String {

        if (userService!!.findByUserName(user.getUsername()!!) != null) {
            model.addAttribute("message", "Пользователь с таким логином уже существует")
            return "regis"
        }
        if(user.getPassword()!!.length < 6) {
            model.addAttribute("message", "Пароль меньше 6 символов!")
            return "regis"
        }
        if(user.getPassword()!!.filter { it.isDigit() }.firstOrNull() == null) {
            model.addAttribute("message", "Пароль не содержит цифры!")
            return "regis"
        }
        if(user.getUsername()!!.isEmpty()) {
            model.addAttribute("message", "Логин не заполнен!")
            return "regis"
        }

        user.setActive(true)
        user.setRoles((mutableSetOf(RoleEnum.USER)))
        user.setPassword((passwordEncoder.encode(user.getPassword())))
        userService!!.addUser(user)
        return "redirect:/login"
    }


}