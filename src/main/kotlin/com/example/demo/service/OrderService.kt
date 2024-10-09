package com.example.demo.service

import com.example.demo.models.Order

interface OrderService {
    fun getOrders(): List<Order>
    fun addOrder(order: Order): Order
    fun findOrderById(id: Long): Order?
    fun updateOrder(order: Order): Order?
    fun deleteOrder(id: Long)
}