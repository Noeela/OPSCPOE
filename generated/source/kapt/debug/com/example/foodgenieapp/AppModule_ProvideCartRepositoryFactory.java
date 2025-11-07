package com.example.foodgenieapp;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class AppModule_ProvideCartRepositoryFactory implements Factory<CartRepository> {
  private final Provider<AppDao> appDaoProvider;

  public AppModule_ProvideCartRepositoryFactory(Provider<AppDao> appDaoProvider) {
    this.appDaoProvider = appDaoProvider;
  }

  @Override
  public CartRepository get() {
    return provideCartRepository(appDaoProvider.get());
  }

  public static AppModule_ProvideCartRepositoryFactory create(Provider<AppDao> appDaoProvider) {
    return new AppModule_ProvideCartRepositoryFactory(appDaoProvider);
  }

  public static CartRepository provideCartRepository(AppDao appDao) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideCartRepository(appDao));
  }
}
