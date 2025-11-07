package com.example.foodgenieapp


import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch



class DatabaseInitializer(
    private val context: Context,
    private val appDao: AppDao,
    private val coroutineScope: CoroutineScope
) {

    fun initializeSampleData() {
        coroutineScope.launch {
            insertSampleFoodItems()
        }
    }

    private suspend fun insertSampleFoodItems() {
        val existingItems = appDao.getFoodItemsByCategory("meals").firstOrNull()
        if (!existingItems.isNullOrEmpty()) return

        val sampleFoodItems = listOf(
            // Meals
            FoodItemEntity(
                id = "meal_1",
                name = "Classic Burger",
                description = "Juicy beef patty with fresh lettuce, tomato, and special sauce",
                price = 65.0,
                category = "meals",
                imageRes = android.R.drawable.ic_dialog_email,
                rating = 4.5,
                preparationTime = 15
            ),
            FoodItemEntity(
                id = "meal_2",
                name = "Pepperoni Pizza",
                description = "Freshly baked pizza with pepperoni and mozzarella cheese",
                price = 95.0,
                category = "meals",
                imageRes = android.R.drawable.ic_dialog_email,
                rating = 4.7,
                preparationTime = 20
            ),
            FoodItemEntity(
                id = "meal_3",
                name = "Chicken Wrap",
                description = "Grilled chicken with fresh veggies in a soft tortilla",
                price = 55.0,
                category = "meals",
                imageRes = android.R.drawable.ic_dialog_email,
                rating = 4.3,
                preparationTime = 10
            ),
            FoodItemEntity(
                id = "meal_4",
                name = "Beef Steak",
                description = "Tender beef steak with mashed potatoes and vegetables",
                price = 120.0,
                category = "meals",
                imageRes = android.R.drawable.ic_dialog_email,
                rating = 4.8,
                preparationTime = 25
            ),
            FoodItemEntity(
                id = "meal_5",
                name = "Vegetable Pasta",
                description = "Pasta with fresh vegetables and tomato sauce",
                price = 70.0,
                category = "meals",
                imageRes = android.R.drawable.ic_dialog_email,
                rating = 4.2,
                preparationTime = 15
            ),

            // Sides
            FoodItemEntity(
                id = "side_1",
                name = "French Fries",
                description = "Golden and crispy French fries with seasoning",
                price = 25.0,
                category = "sides",
                imageRes = android.R.drawable.ic_dialog_email,
                rating = 4.4,
                preparationTime = 8
            ),
            FoodItemEntity(
                id = "side_2",
                name = "Onion Rings",
                description = "Crispy battered onion rings with dipping sauce",
                price = 30.0,
                category = "sides",
                imageRes = android.R.drawable.ic_dialog_email,
                rating = 4.3,
                preparationTime = 10
            ),
            FoodItemEntity(
                id = "side_3",
                name = "Garlic Bread",
                description = "Toasted bread with garlic butter and herbs",
                price = 20.0,
                category = "sides",
                imageRes = android.R.drawable.ic_dialog_email,
                rating = 4.5,
                preparationTime = 5
            ),
            FoodItemEntity(
                id = "side_4",
                name = "Mozzarella Sticks",
                description = "Breaded mozzarella cheese sticks with marinara sauce",
                price = 35.0,
                category = "sides",
                imageRes = android.R.drawable.ic_dialog_email,
                rating = 4.6,
                preparationTime = 12
            ),

            // Drinks
            FoodItemEntity(
                id = "drink_1",
                name = "Coca-Cola",
                description = "Chilled Coca-Cola 330ml",
                price = 15.0,
                category = "drinks",
                imageRes = android.R.drawable.ic_dialog_email,
                rating = 4.2,
                preparationTime = 2
            ),
            FoodItemEntity(
                id = "drink_2",
                name = "Orange Juice",
                description = "Freshly squeezed orange juice",
                price = 20.0,
                category = "drinks",
                imageRes = android.R.drawable.ic_dialog_email,
                rating = 4.4,
                preparationTime = 3
            ),
            FoodItemEntity(
                id = "drink_3",
                name = "Iced Coffee",
                description = "Chilled coffee with milk and ice",
                price = 25.0,
                category = "drinks",
                imageRes = android.R.drawable.ic_dialog_email,
                rating = 4.5,
                preparationTime = 5
            ),
            FoodItemEntity(
                id = "drink_4",
                name = "Mineral Water",
                description = "500ml bottled mineral water",
                price = 12.0,
                category = "drinks",
                imageRes = android.R.drawable.ic_dialog_email,
                rating = 4.1,
                preparationTime = 1
            ),
            FoodItemEntity(
                id = "drink_5",
                name = "Milkshake",
                description = "Creamy vanilla milkshake",
                price = 30.0,
                category = "drinks",
                imageRes = android.R.drawable.ic_dialog_email,
                rating = 4.7,
                preparationTime = 7
            )
        )

        sampleFoodItems.forEach { foodItem ->
            appDao.insertFoodItem(foodItem)
        }
    }
}