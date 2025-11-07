package com.example.foodgenieapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val userId: String,
    val email: String,
    val name: String,
    val password: String,
    val phone: String = "",
    val address: String = "",
    val language: String = "en", // Language preference
    val notificationsEnabled: Boolean = true,
    val createdAt: Long = System.currentTimeMillis()
)