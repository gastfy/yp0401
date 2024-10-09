package com.example.demo.service

import com.example.demo.models.Order
import com.example.demo.repository.OrderDAO
import org.springframework.stereotype.Service

@Service
class OrderImpl(private val repo: OrderDAO) : OrderService {

    override fun getOrders(): List<Order> {
        return repo.getOrders()
    }

    override fun addOrder(order: Order): Order {
        return repo.addOrder(order)
    }

    override fun findOrderById(id: Long): Order? {
        return repo.findOrderById(id)
    }

    override fun updateOrder(order: Order): Order? {
        return repo.updateOrder(order)
    }

    override fun deleteOrder(id: Long) {
        repo.deleteOrder(id)
    }
}