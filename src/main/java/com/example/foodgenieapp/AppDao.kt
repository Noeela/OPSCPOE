package com.example.foodgenieapp



import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    // User operations
    @Query("SELECT * FROM users WHERE email = :email AND password = :password LIMIT 1")
    suspend fun authenticateUser(email: String, password: String): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM users WHERE userId = :userId LIMIT 1")
    fun getUser(userId: String): Flow<UserEntity?>

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): UserEntity?

    @Update
    suspend fun updateUser(user: UserEntity)

    // Food items operations
    @Query("SELECT * FROM food_items WHERE category = :category")
    fun getFoodItemsByCategory(category: String): Flow<List<FoodItemEntity>>

    @Query("SELECT * FROM food_items WHERE name LIKE '%' || :query || '%'")
    fun searchFoodItems(query: String): Flow<List<FoodItemEntity>>

    @Query("SELECT * FROM food_items WHERE id = :foodId LIMIT 1")
    suspend fun getFoodItem(foodId: String): FoodItemEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodItem(foodItem: FoodItemEntity)

    // Cart operations
    @Query("SELECT cart_items.*, food_items.name, food_items.price, food_items.imageRes FROM cart_items INNER JOIN food_items ON cart_items.foodId = food_items.id WHERE cart_items.userId = :userId")
    fun getCartItems(userId: String): Flow<List<CartItemWithDetails>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartItem: CartItemEntity)

    @Query("DELETE FROM cart_items WHERE cartItemId = :cartItemId")
    suspend fun removeCartItem(cartItemId: Long)

    @Query("DELETE FROM cart_items WHERE userId = :userId")
    suspend fun clearUserCart(userId: String)

    @Query("UPDATE cart_items SET quantity = :quantity WHERE cartItemId = :cartItemId")
    suspend fun updateCartItemQuantity(cartItemId: Long, quantity: Int)

    // Order operations
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: OrderEntity)

    @Query("SELECT * FROM orders WHERE userId = :userId ORDER BY orderDate DESC")
    fun getUserOrders(userId: String): Flow<List<OrderEntity>>
}

data class CartItemWithDetails(
    val cartItemId: Long,
    val userId: String,
    val foodId: String,
    val quantity: Int,
    val addedAt: Long,
    val name: String,
    val price: Double,
    val imageRes: Int
)