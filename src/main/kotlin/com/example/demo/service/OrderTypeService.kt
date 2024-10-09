package com.example.demo.service

import com.example.demo.models.OrderTypeModel

interface OrderTypeService {
    fun getOrderTypes(): List<OrderTypeModel>
    fun addOrderType(type: OrderTypeModel): OrderTypeModel
    fun findOrderTypeById(id: Long): OrderTypeModel?
    fun updateOrderType(type: OrderTypeModel): OrderTypeModel?
    fun deleteOrderType(id: Long)
}