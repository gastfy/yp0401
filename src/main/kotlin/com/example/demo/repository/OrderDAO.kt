package com.example.demo.repository

import com.example.demo.models.Order
import com.example.demo.models.StoreModel
import org.springframework.stereotype.Repository
import java.util.concurrent.atomic.AtomicLong
import java.util.stream.IntStream

@Repository
class OrderDAO (private var orders: MutableList<Order>) {

    private val idCounter = AtomicLong(1)

    fun getOrders(): List<Order> {
        return orders
    }

    fun addOrder(order: Order): Order {
        order.setId(idCounter.getAndIncrement())
        orders.add(order)
        return order
    }

    fun findOrderById(id: Long): Order? {
        return orders.stream().filter { el -> el.getId() == id }.findFirst().orElse(null)
    }

    fun updateOrder(order: Order): Order? {
        val genInd = IntStream.range(0, orders.size)
            .filter { ind -> orders[ind].getId() == order.getId() }
            .findFirst()
            .orElse(-1)
        if (genInd == -1){
            return null
        }
        orders[genInd] = order
        return order
    }

    fun deleteOrder(id: Long) {
        val order = findOrderById(id)
        if (order != null) {
            orders.remove(order)
        }
    }
}