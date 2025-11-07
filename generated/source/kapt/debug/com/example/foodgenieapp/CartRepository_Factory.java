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
public final class CartRepository_Factory implements Factory<CartRepository> {
  private final Provider<AppDao> appDaoProvider;

  public CartRepository_Factory(Provider<AppDao> appDaoProvider) {
    this.appDaoProvider = appDaoProvider;
  }

  @Override
  public CartRepository get() {
    return newInstance(appDaoProvider.get());
  }

  public static CartRepository_Factory create(Provider<AppDao> appDaoProvider) {
    return new CartRepository_Factory(appDaoProvider);
  }

  public static CartRepository newInstance(AppDao appDao) {
    return new CartRepository(appDao);
  }
}
