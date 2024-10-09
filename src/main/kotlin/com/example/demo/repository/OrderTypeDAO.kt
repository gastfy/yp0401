package com.example.demo.repository

import com.example.demo.models.OrderTypeModel
import com.example.demo.models.TypeModel
import org.springframework.stereotype.Repository
import java.util.concurrent.atomic.AtomicLong
import java.util.stream.IntStream

@Repository
class OrderTypeDAO(private val types: MutableList<OrderTypeModel>) {

    private val idCounter = AtomicLong(1)

    fun getOrderTypes(): List<OrderTypeModel> {
        return types
    }

    fun addOrderType(type: OrderTypeModel): OrderTypeModel {
        type.setId(idCounter.getAndIncrement())
        types.add(type)
        return type
    }

    fun findOrderTypeById(id: Long): OrderTypeModel? {
        return types.stream().filter { el -> el.getId() == id }.findFirst().orElse(null)
    }

    fun updateOrderType(type: OrderTypeModel): OrderTypeModel? {
        val genInd = IntStream.range(0, types.size)
            .filter { ind -> types[ind].getId() == type.getId() }
            .findFirst()
            .orElse(-1)
        if (genInd == -1){
            return null
        }
        types[genInd] = type
        return type
    }

    fun deleteOrderType(id: Long) {
        val type = findOrderTypeById(id)
        if (type != null) {
            types.remove(type)
        }
    }
}