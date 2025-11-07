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
public final class AppModule_ProvideAppDaoFactory implements Factory<AppDao> {
  private final Provider<AppDatabase> appDatabaseProvider;

  public AppModule_ProvideAppDaoFactory(Provider<AppDatabase> appDatabaseProvider) {
    this.appDatabaseProvider = appDatabaseProvider;
  }

  @Override
  public AppDao get() {
    return provideAppDao(appDatabaseProvider.get());
  }

  public static AppModule_ProvideAppDaoFactory create(Provider<AppDatabase> appDatabaseProvider) {
    return new AppModule_ProvideAppDaoFactory(appDatabaseProvider);
  }

  public static AppDao provideAppDao(AppDatabase appDatabase) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideAppDao(appDatabase));
  }
}
