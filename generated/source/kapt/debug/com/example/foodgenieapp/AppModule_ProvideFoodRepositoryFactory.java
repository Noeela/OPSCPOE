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
public final class AppModule_ProvideFoodRepositoryFactory implements Factory<FoodRepository> {
  private final Provider<AppDao> appDaoProvider;

  public AppModule_ProvideFoodRepositoryFactory(Provider<AppDao> appDaoProvider) {
    this.appDaoProvider = appDaoProvider;
  }

  @Override
  public FoodRepository get() {
    return provideFoodRepository(appDaoProvider.get());
  }

  public static AppModule_ProvideFoodRepositoryFactory create(Provider<AppDao> appDaoProvider) {
    return new AppModule_ProvideFoodRepositoryFactory(appDaoProvider);
  }

  public static FoodRepository provideFoodRepository(AppDao appDao) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideFoodRepository(appDao));
  }
}
