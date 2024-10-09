package com.example.demo.models

import com.example.demo.models.Order
import jakarta.persistence.*
import jakarta.validation.constraints.Size

@Entity
class OrderTypeModel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null,
    @Size(min = 3, max = 20, message = "Тип должен быть не меньше 3 символов и не больше 20!") private var type: String? = null,
    @OneToMany(cascade = [(CascadeType.REMOVE)], orphanRemoval = true, mappedBy = "type") private var orders: MutableList<Order> = mutableListOf(),
) {
    fun setId(id: Long){
        this.id = id
    }
    fun setType(type: String) {
        this.type = type
    }
    fun getType(): String? {
        return type
    }
    fun getId(): Long {
        return id!!
    }
    fun getOrders(): MutableList<Order> {
        return orders
    }
}