package com.example.demo.controllers

import com.example.demo.models.ModelUser
import com.example.demo.models.Order
import com.example.demo.service.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*


@Controller
@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
class UserController {

    @Autowired
    var userImpl: UserImpl? = null

    @Autowired
    var orderService: OrderImpl? = null

    @Autowired
    var bookService: BookImpl? = null

    @Autowired
    var orderTypeService: OrderTypeImpl? = null


    @GetMapping("/orders")
    fun orders(model: Model) : String {
        model.addAttribute("orders", orderService!!.getOrders())
        model.addAttribute("books", bookService!!.getBooks())
        model.addAttribute("order", Order())
        return "orders"
    }

    @PostMapping("/orders")
    fun order(@RequestParam params: Map<String,String>, model: Model) : String {

        if(!params.containsKey("books")) {
            return "redirect:/orders"
        }

        val auth: Authentication = SecurityContextHolder.getContext().authentication
        var user: ModelUser? = null
        user = if (auth.principal == "anonymousUser"){
            userImpl!!.getUsers().first()
        } else{
            userImpl!!.findByUserName(auth.name)
        }

        val order = Order(
            0, mutableListOf(bookService!!.findBookById(params["books"]!!.toLong())!!),
            orderTypeService!!.findOrderTypeById(1), mutableListOf(user!!)
        )

        orderService!!.addOrder(order)

        return "redirect:/orders"
    }

    @GetMapping("/orders/edit/{id}")
    fun edit(@PathVariable("id") id: Long, model: Model) : String {

        val order = orderService!!.findOrderById(id)

        model.addAttribute("books", bookService!!.getBooks())
        model.addAttribute("order", order)

        return "order_change"
    }

    @PostMapping("/orders/update")
    fun update(@RequestParam params: Map<String,String>, model: Model) : String {
        println(params.keys)
        println("--------------------")
        println(params.values)

        val book = bookService!!.findBookById(params["books"]!!.toLong())

        val auth: Authentication = SecurityContextHolder.getContext().authentication
        var user: ModelUser? = null
        user = if (auth.principal == "anonymousUser"){
            userImpl!!.getUsers().first()
        } else{
            userImpl!!.findByUserName(auth.name)
        }

        val order = Order(params["id"]!!.toLong(), mutableListOf(book!!), orderTypeService!!.findOrderTypeById(1), mutableListOf(user!!))
        orderService!!.updateOrder(order)

        return "redirect:/orders"
    }

    @GetMapping("/orders/delete/{id}")
    fun deleteById(@PathVariable("id") id: Long, model: Model) : String {
        orderService!!.deleteOrder(id)
        return "redirect:/orders"
    }

}