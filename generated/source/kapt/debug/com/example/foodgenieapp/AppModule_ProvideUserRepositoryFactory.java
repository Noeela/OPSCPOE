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
public final class AppModule_ProvideUserRepositoryFactory implements Factory<UserRepository> {
  private final Provider<AppDao> appDaoProvider;

  public AppModule_ProvideUserRepositoryFactory(Provider<AppDao> appDaoProvider) {
    this.appDaoProvider = appDaoProvider;
  }

  @Override
  public UserRepository get() {
    return provideUserRepository(appDaoProvider.get());
  }

  public static AppModule_ProvideUserRepositoryFactory create(Provider<AppDao> appDaoProvider) {
    return new AppModule_ProvideUserRepositoryFactory(appDaoProvider);
  }

  public static UserRepository provideUserRepository(AppDao appDao) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideUserRepository(appDao));
  }
}
