package com.example.demo.repository

import com.example.demo.models.Genre
import com.example.demo.models.TypeModel
import org.springframework.stereotype.Repository
import java.util.concurrent.atomic.AtomicLong
import java.util.stream.IntStream


@Repository
class TypeDAO(private val types: MutableList<TypeModel>) {

    private val idCounter = AtomicLong(1)

    fun getTypes(): List<TypeModel> {
        return types
    }

    fun addType(type: TypeModel): TypeModel {
        type.setId(idCounter.getAndIncrement())
        types.add(type)
        return type
    }

    fun findTypeById(id: Long): TypeModel? {
        return types.stream().filter { el -> el.getId() == id }.findFirst().orElse(null)
    }

    fun updateType(type: TypeModel): TypeModel? {
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

    fun deleteType(id: Long) {
        val type = findTypeById(id)
        if (type != null) {
            types.remove(type)
        }
    }
}