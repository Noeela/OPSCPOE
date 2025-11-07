package com.example.foodgenieapp



import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItemEntity(
    @PrimaryKey(autoGenerate = true)
    val cartItemId: Long = 0,
    val userId: String,
    val foodId: String,
    val quantity: Int,
    val addedAt: Long = System.currentTimeMillis()
)