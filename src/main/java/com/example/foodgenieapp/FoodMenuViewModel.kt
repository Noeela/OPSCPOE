package com.example.foodgenieapp


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

// FoodMenuViewModel.kt


@HiltViewModel
class FoodMenuViewModel @Inject constructor(
    private val foodRepository: FoodRepository,
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _currentUserId = MutableStateFlow<String?>(null)

    val meals: StateFlow<List<FoodItemEntity>> = foodRepository.getMeals()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val sides: StateFlow<List<FoodItemEntity>> = foodRepository.getSides()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val drinks: StateFlow<List<FoodItemEntity>> = foodRepository.getDrinks()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val searchResults: StateFlow<List<FoodItemEntity>> = combine(
        _searchQuery,
        meals,
        sides,
        drinks
    ) { query, mealsList, sidesList, drinksList ->
        if (query.isBlank()) {
            emptyList()
        } else {
            val allItems = mealsList + sidesList + drinksList
            allItems.filter { item ->
                item.name.contains(query, ignoreCase = true) ||
                        item.description.contains(query, ignoreCase = true)
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun setUserId(userId: String) {
        _currentUserId.value = userId
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun addToCart(foodItem: FoodItemEntity) {
        viewModelScope.launch {
            val userId = _currentUserId.value ?: return@launch
            cartRepository.addToCart(userId, foodItem.id)
        }
    }
}