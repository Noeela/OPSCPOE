package com.example.foodgenieapp


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CartRepository @Inject constructor(
    private val appDao: AppDao
) {
    fun getCartItems(userId: String): Flow<List<CartItemWithDetails>> {
        return appDao.getCartItems(userId)
    }

    suspend fun addToCart(userId: String, foodId: String, quantity: Int = 1) {
        val cartItems = getCartItems(userId).first()
        val existingItem = cartItems.find { it.foodId == foodId }

        if (existingItem != null) {
            appDao.updateCartItemQuantity(existingItem.cartItemId, existingItem.quantity + quantity)
        } else {
            val cartItem = CartItemEntity(userId = userId, foodId = foodId, quantity = quantity)
            appDao.insertCartItem(cartItem)
        }
    }

    suspend fun updateCartItemQuantity(cartItemId: Long, quantity: Int) {
        if (quantity <= 0) {
            appDao.removeCartItem(cartItemId)
        } else {
            appDao.updateCartItemQuantity(cartItemId, quantity)
        }
    }

    suspend fun removeFromCart(cartItemId: Long) {
        appDao.removeCartItem(cartItemId)
    }

    suspend fun clearCart(userId: String) {
        appDao.clearUserCart(userId)
    }

    fun getCartTotal(userId: String): Flow<Double> {
        return appDao.getCartItems(userId).map { cartItems ->
            cartItems.sumOf { it.price * it.quantity }
        }
    }
}