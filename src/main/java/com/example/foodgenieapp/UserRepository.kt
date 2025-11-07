package com.example.foodgenieapp

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val appDao: AppDao
) {
    suspend fun registerUser(user: UserEntity): Boolean {
        return try {
            val existingUser = appDao.getUserByEmail(user.email)
            if (existingUser != null) return false
            appDao.insertUser(user)
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun loginUser(email: String, password: String): UserEntity? {
        return try {
            appDao.authenticateUser(email, password)
        } catch (e: Exception) {
            null
        }
    }

    fun getUser(userId: String): Flow<UserEntity?> {
        return appDao.getUser(userId)
    }

    suspend fun updateUser(user: UserEntity) {
        appDao.updateUser(user)
    }
}