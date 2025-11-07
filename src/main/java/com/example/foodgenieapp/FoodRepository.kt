package com.example.foodgenieapp


import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FoodRepository @Inject constructor(
    private val appDao: AppDao
) {
    fun getMeals(): Flow<List<FoodItemEntity>> {
        return appDao.getFoodItemsByCategory("meals")
    }

    fun getSides(): Flow<List<FoodItemEntity>> {
        return appDao.getFoodItemsByCategory("sides")
    }

    fun getDrinks(): Flow<List<FoodItemEntity>> {
        return appDao.getFoodItemsByCategory("drinks")
    }

    suspend fun getFoodItem(foodId: String): FoodItemEntity? {
        return appDao.getFoodItem(foodId)
    }
}