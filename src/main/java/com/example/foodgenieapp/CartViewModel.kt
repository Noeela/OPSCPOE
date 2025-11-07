// CartViewModel.kt
package com.example.foodgenieapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val orderRepository: OrderRepository,
    private val notificationService: NotificationService
) : ViewModel() {

    private val _currentUserId = MutableStateFlow<String?>(null)

    // Use the actual user ID from the state flow
    val cartItems: StateFlow<List<CartItemWithDetails>> = _currentUserId
        .flatMapLatest { userId ->
            if (userId != null) {
                cartRepository.getCartItems(userId)
            } else {
                flowOf(emptyList())
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val totalAmount: StateFlow<Double> = _currentUserId
        .flatMapLatest { userId ->
            if (userId != null) {
                cartRepository.getCartTotal(userId)
            } else {
                flowOf(0.0)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0.0
        )

    fun setUserId(userId: String) {
        _currentUserId.value = userId
    }

    fun updateQuantity(cartItemId: Long, newQuantity: Int) {
        viewModelScope.launch {
            cartRepository.updateCartItemQuantity(cartItemId, newQuantity)
        }
    }

    fun removeItem(cartItemId: Long) {
        viewModelScope.launch {
            cartRepository.removeFromCart(cartItemId)
        }
    }

    fun placeOrder(navController: NavController, currentUser: UserEntity?) {
        viewModelScope.launch {
            if (currentUser != null && cartItems.value.isNotEmpty()) {
                val orderId = "order_${System.currentTimeMillis()}"
                val total = totalAmount.value

                val order = OrderEntity(
                    orderId = orderId,
                    userId = currentUser.userId,
                    totalAmount = total,
                    status = "pending",
                    deliveryAddress = currentUser.address.ifEmpty { "123 Main Street" },
                    paymentMethod = "Cash on Delivery"
                )

                orderRepository.placeOrder(order)
                cartRepository.clearCart(currentUser.userId)

                notificationService.showOrderNotification(
                    "Order Placed!",
                    "Your order #${orderId.substringAfterLast("_")} has been placed successfully."
                )

                navController.navigate("orders") {
                    popUpTo("cart") { inclusive = true }
                }
            }
        }
    }
}