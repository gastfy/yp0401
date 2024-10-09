package com.example.demo.repository

import com.example.demo.models.Genre
import com.example.demo.models.StoreModel
import org.springframework.stereotype.Repository
import java.util.concurrent.atomic.AtomicLong
import java.util.stream.IntStream


@Repository
class StoreDAO (private var stores: MutableList<StoreModel>) {

    private val idCounter = AtomicLong(1)

    fun getStores(): List<StoreModel> {
        return stores
    }

    fun addStore(store: StoreModel): StoreModel {
        store.setId(idCounter.getAndIncrement())
        stores.add(store)
        return store
    }

    fun findStoreById(id: Long): StoreModel? {
        return stores.stream().filter { el -> el.getId() == id }.findFirst().orElse(null)
    }

    fun updateStore(store: StoreModel): StoreModel? {
        val genInd = IntStream.range(0, stores.size)
            .filter { ind -> stores[ind].getId() == store.getId() }
            .findFirst()
            .orElse(-1)
        if (genInd == -1){
            return null
        }
        stores[genInd] = store
        return store
    }

    fun deleteStore(id: Long) {
        val store = findStoreById(id)
        if (store != null) {
            stores.remove(store)
        }
    }
}