package com.example.demo.service

import com.example.demo.models.TypeModel

interface TypeService {
    fun getTypes(): List<TypeModel>
    fun addType(type: TypeModel): TypeModel
    fun findTypeById(id: Long): TypeModel?
    fun updateType(type: TypeModel): TypeModel?
    fun deleteType(id: Long)
}