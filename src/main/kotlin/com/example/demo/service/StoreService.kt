package com.example.demo.service

import com.example.demo.models.StoreModel

interface StoreService {
    fun getStores(): List<StoreModel>
    fun addStore(store: StoreModel): StoreModel
    fun findStoreById(id: Long): StoreModel?
    fun updateStore(store: StoreModel): StoreModel?
    fun deleteStore(id: Long)
}