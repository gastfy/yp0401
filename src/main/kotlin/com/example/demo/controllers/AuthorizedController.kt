package com.example.demo.controllers

import com.example.demo.models.ModelUser
import com.example.demo.service.UserImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.annotation.Secured
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN', 'USER', 'MANAGER')")
class AuthorizedController {

    @Autowired
    var userImpl: UserImpl? = null

    private val passwordEncoder: PasswordEncoder = BCryptPasswordEncoder(4)

    @GetMapping("/profile")
    fun profile(model: Model): String {

        val auth: Authentication = SecurityContextHolder.getContext().authentication

        var user: ModelUser? = null
        user = if (auth.principal == "anonymousUser"){
            userImpl!!.getUsers().first()
        } else{
            userImpl!!.findByUserName(auth.name)
        }

        model.addAttribute("user", user)

        return "profile"
    }

    @GetMapping("/profile/change")
    fun profileChange(model: Model): String {
        val auth: Authentication = SecurityContextHolder.getContext().authentication
        var user: ModelUser? = null
        println(auth.name)
        user = if (auth.name.toString() == "anonymousUser"){
            userImpl!!.getUsers().first()
        } else{
            userImpl!!.findByUserName(auth.name)
        }
        model.addAttribute("user", user)
        return "change_profile"
    }

    @PostMapping("/profile/change")
    fun changeProfile(@RequestParam params: Map<String,String>, model: Model) : String{

        if(params["name"].toString().length < 2 || params["name"].toString().length > 20){
            return "redirect:/profile"
        }
        if(params["username"].toString().length < 2 || params["username"].toString().length > 20){
            return "redirect:/profile"
        }
        if(params["password"].toString().length < 2 || params["password"].toString().length > 20){
            return "redirect:/profile"
        }

        val password = passwordEncoder.encode(params["password"].toString())
        val roles = userImpl!!.getUser(params["id"]!!.toLong())!!.getRoles()
        val user = ModelUser(params["id"]!!.toLong(), params["name"], params["username"], password, roles, true)
        userImpl!!.updateUser(user)
        return "redirect:/profile"
    }

    @GetMapping("/profile/delete")
    fun profileDelete(model: Model): String {
        val auth: Authentication = SecurityContextHolder.getContext().authentication
        var user: ModelUser? = null
        user = if (auth.name.toString() == "anonymousUser"){
            userImpl!!.getUsers().first()
        } else{
            userImpl!!.findByUserName(auth.name.toString())
        }
        userImpl!!.deleteUser(user!!.getId())
        return "redirect:/logout"
    }

}