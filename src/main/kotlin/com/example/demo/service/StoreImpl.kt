package com.example.demo.service

import com.example.demo.models.StoreModel
import com.example.demo.repository.StoreDAO
import org.springframework.stereotype.Service


@Service
class StoreImpl(private val repo: StoreDAO) : StoreService {
    override fun getStores(): List<StoreModel> {
        return repo.getStores()
    }

    override fun addStore(store: StoreModel): StoreModel {
        return repo.addStore(store)
    }

    override fun findStoreById(id: Long): StoreModel? {
        return repo.findStoreById(id)
    }

    override fun updateStore(store: StoreModel): StoreModel? {
        return repo.updateStore(store)
    }

    override fun deleteStore(id: Long) {
        repo.deleteStore(id)
    }
}