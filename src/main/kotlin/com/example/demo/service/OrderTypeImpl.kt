package com.example.demo.service

import com.example.demo.models.OrderTypeModel
import com.example.demo.repository.OrderTypeDAO
import org.springframework.stereotype.Service

@Service
class OrderTypeImpl(private val repo: OrderTypeDAO) : OrderTypeService {
    override fun getOrderTypes(): List<OrderTypeModel> {
        return repo.getOrderTypes()
    }

    override fun addOrderType(type: OrderTypeModel): OrderTypeModel {
        return repo.addOrderType(type)
    }

    override fun findOrderTypeById(id: Long): OrderTypeModel? {
        return repo.findOrderTypeById(id)
    }

    override fun updateOrderType(type: OrderTypeModel): OrderTypeModel? {
        return repo.updateOrderType(type)
    }

    override fun deleteOrderType(id: Long) {
        repo.deleteOrderType(id)
    }

}