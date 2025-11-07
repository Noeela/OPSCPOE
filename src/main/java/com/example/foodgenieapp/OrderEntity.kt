package com.example.foodgenieapp



import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey
    val orderId: String,
    val userId: String,
    val totalAmount: Double,
    val status: String, // "pending", "confirmed", "preparing", "delivered"
    val deliveryAddress: String,
    val paymentMethod: String,
    val orderDate: Long = System.currentTimeMillis(),
    val estimatedDelivery: Long = System.currentTimeMillis() + 45 * 60 * 1000
)