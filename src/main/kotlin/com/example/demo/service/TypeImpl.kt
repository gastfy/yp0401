package com.example.demo.service

import com.example.demo.models.TypeModel
import com.example.demo.repository.TypeDAO
import org.springframework.stereotype.Service

@Service
class TypeImpl(private val repo: TypeDAO) : TypeService {
    override fun getTypes(): List<TypeModel> {
        return repo.getTypes()
    }

    override fun addType(type: TypeModel): TypeModel {
        return repo.addType(type)
    }

    override fun findTypeById(id: Long): TypeModel? {
        return repo.findTypeById(id)
    }

    override fun updateType(type: TypeModel): TypeModel? {
        return repo.updateType(type)
    }

    override fun deleteType(id: Long) {
        repo.deleteType(id)
    }
}