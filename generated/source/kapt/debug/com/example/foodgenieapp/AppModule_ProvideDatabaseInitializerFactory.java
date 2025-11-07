package com.example.foodgenieapp;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class AppModule_ProvideDatabaseInitializerFactory implements Factory<DatabaseInitializer> {
  private final Provider<Context> contextProvider;

  private final Provider<AppDao> appDaoProvider;

  public AppModule_ProvideDatabaseInitializerFactory(Provider<Context> contextProvider,
      Provider<AppDao> appDaoProvider) {
    this.contextProvider = contextProvider;
    this.appDaoProvider = appDaoProvider;
  }

  @Override
  public DatabaseInitializer get() {
    return provideDatabaseInitializer(contextProvider.get(), appDaoProvider.get());
  }

  public static AppModule_ProvideDatabaseInitializerFactory create(
      Provider<Context> contextProvider, Provider<AppDao> appDaoProvider) {
    return new AppModule_ProvideDatabaseInitializerFactory(contextProvider, appDaoProvider);
  }

  public static DatabaseInitializer provideDatabaseInitializer(Context context, AppDao appDao) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideDatabaseInitializer(context, appDao));
  }
}
