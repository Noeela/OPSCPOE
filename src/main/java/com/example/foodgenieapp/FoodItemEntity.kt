package com.example.foodgenieapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_items")
data class FoodItemEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val category: String, // "meals", "sides", "drinks"
    val imageRes: Int,
    val rating: Double = 4.5,
    val preparationTime: Int = 15
)