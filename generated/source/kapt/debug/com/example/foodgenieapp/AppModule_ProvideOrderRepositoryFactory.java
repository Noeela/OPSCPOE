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
public final class AppModule_ProvideOrderRepositoryFactory implements Factory<OrderRepository> {
  private final Provider<AppDao> appDaoProvider;

  public AppModule_ProvideOrderRepositoryFactory(Provider<AppDao> appDaoProvider) {
    this.appDaoProvider = appDaoProvider;
  }

  @Override
  public OrderRepository get() {
    return provideOrderRepository(appDaoProvider.get());
  }

  public static AppModule_ProvideOrderRepositoryFactory create(Provider<AppDao> appDaoProvider) {
    return new AppModule_ProvideOrderRepositoryFactory(appDaoProvider);
  }

  public static OrderRepository provideOrderRepository(AppDao appDao) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideOrderRepository(appDao));
  }
}
