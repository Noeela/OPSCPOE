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
public final class AuthViewModel_Factory implements Factory<AuthViewModel> {
  private final Provider<UserRepository> userRepositoryProvider;

  private final Provider<LocalizationManager> localizationManagerProvider;

  public AuthViewModel_Factory(Provider<UserRepository> userRepositoryProvider,
      Provider<LocalizationManager> localizationManagerProvider) {
    this.userRepositoryProvider = userRepositoryProvider;
    this.localizationManagerProvider = localizationManagerProvider;
  }

  @Override
  public AuthViewModel get() {
    return newInstance(userRepositoryProvider.get(), localizationManagerProvider.get());
  }

  public static AuthViewModel_Factory create(Provider<UserRepository> userRepositoryProvider,
      Provider<LocalizationManager> localizationManagerProvider) {
    return new AuthViewModel_Factory(userRepositoryProvider, localizationManagerProvider);
  }

  public static AuthViewModel newInstance(UserRepository userRepository,
      LocalizationManager localizationManager) {
    return new AuthViewModel(userRepository, localizationManager);
  }
}
