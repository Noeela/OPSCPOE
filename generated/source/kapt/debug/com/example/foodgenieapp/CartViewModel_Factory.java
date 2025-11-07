package com.example.foodgenieapp;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class CartViewModel_Factory implements Factory<CartViewModel> {
  private final Provider<CartRepository> cartRepositoryProvider;

  private final Provider<OrderRepository> orderRepositoryProvider;

  private final Provider<NotificationService> notificationServiceProvider;

  public CartViewModel_Factory(Provider<CartRepository> cartRepositoryProvider,
      Provider<OrderRepository> orderRepositoryProvider,
      Provider<NotificationService> notificationServiceProvider) {
    this.cartRepositoryProvider = cartRepositoryProvider;
    this.orderRepositoryProvider = orderRepositoryProvider;
    this.notificationServiceProvider = notificationServiceProvider;
  }

  @Override
  public CartViewModel get() {
    return newInstance(cartRepositoryProvider.get(), orderRepositoryProvider.get(), notificationServiceProvider.get());
  }

  public static CartViewModel_Factory create(Provider<CartRepository> cartRepositoryProvider,
      Provider<OrderRepository> orderRepositoryProvider,
      Provider<NotificationService> notificationServiceProvider) {
    return new CartViewModel_Factory(cartRepositoryProvider, orderRepositoryProvider, notificationServiceProvider);
  }

  public static CartViewModel newInstance(CartRepository cartRepository,
      OrderRepository orderRepository, NotificationService notificationService) {
    return new CartViewModel(cartRepository, orderRepository, notificationService);
  }
}
