package com.example.foodgenieapp



import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OrderRepository @Inject constructor(
    private val appDao: AppDao
) {
    suspend fun placeOrder(order: OrderEntity) {
        appDao.insertOrder(order)
    }

    fun getUserOrders(userId: String): Flow<List<OrderEntity>> {
        return appDao.getUserOrders(userId)
    }
}