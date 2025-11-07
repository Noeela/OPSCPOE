package com.example.foodgenieapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodMenuScreen(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel(),
    foodMenuViewModel: FoodMenuViewModel = hiltViewModel()
) {
    val currentUser by authViewModel.currentUser.collectAsState()
    val meals by foodMenuViewModel.meals.collectAsState()
    val sides by foodMenuViewModel.sides.collectAsState()
    val drinks by foodMenuViewModel.drinks.collectAsState()
    val searchQuery by foodMenuViewModel.searchQuery.collectAsState()
    val searchResults by foodMenuViewModel.searchResults.collectAsState()

    // Set userId when screen loads
    LaunchedEffect(currentUser) {
        currentUser?.userId?.let { foodMenuViewModel.setUserId(it) }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Food Menu") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("cart") },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = foodMenuViewModel::onSearchQueryChange,
                placeholder = { Text("Search food items...") },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                singleLine = true
            )

            if (searchQuery.isNotEmpty()) {
                // Search Results
                if (searchResults.isEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "No items found",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "Try searching for something else",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                } else {
                    FoodItemsList(
                        title = "Search Results (${searchResults.size} items)",
                        foodItems = searchResults,
                        onAddToCart = foodMenuViewModel::addToCart,
                        modifier = Modifier.weight(1f)
                    )
                }
            } else {
                // Categories
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        FoodItemsList(
                            title = "🍔 Meals (${meals.size})",
                            foodItems = meals,
                            onAddToCart = foodMenuViewModel::addToCart
                        )
                    }
                    item {
                        FoodItemsList(
                            title = "🍟 Sides (${sides.size})",
                            foodItems = sides,
                            onAddToCart = foodMenuViewModel::addToCart
                        )
                    }
                    item {
                        FoodItemsList(
                            title = "🥤 Drinks (${drinks.size})",
                            foodItems = drinks,
                            onAddToCart = foodMenuViewModel::addToCart
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FoodItemsList(
    title: String,
    foodItems: List<FoodItemEntity>,
    onAddToCart: (FoodItemEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        if (foodItems.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "No items available",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Check back later for new items",
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        } else {
            Column {
                foodItems.forEach { foodItem ->
                    FoodItemCard(
                        foodItem = foodItem,
                        onAddToCart = { onAddToCart(foodItem) }
                    )
                }
            }
        }
    }
}

@Composable
fun FoodItemCard(
    foodItem: FoodItemEntity,
    onAddToCart: () -> Unit
) {
    var showAddedMessage by remember { mutableStateOf(false) }

    // Show success message when item is added
    if (showAddedMessage) {
        LaunchedEffect(showAddedMessage) {
            kotlinx.coroutines.delay(2000) // Hide after 2 seconds
            showAddedMessage = false
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = foodItem.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = foodItem.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 2
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Rating and preparation time
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.Star,
                                contentDescription = "Rating",
                                modifier = Modifier.size(16.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "%.1f".format(foodItem.rating),
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Medium
                            )
                        }

                        Text(
                            text = "•",
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Text(
                            text = "${foodItem.preparationTime} min",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "R${"%.2f".format(foodItem.price)}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(
                        onClick = {
                            onAddToCart()
                            showAddedMessage = true
                        },
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Add to cart",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                    if (showAddedMessage) {
                        Text(
                            text = "Added!",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}