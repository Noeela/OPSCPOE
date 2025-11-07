package com.example.foodgenieapp

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideAppDao(appDatabase: AppDatabase) = appDatabase.appDao()

    @Provides
    @Singleton
    fun provideUserRepository(appDao: AppDao) = UserRepository(appDao)

    @Provides
    @Singleton
    fun provideFoodRepository(appDao: AppDao) = FoodRepository(appDao)

    @Provides
    @Singleton
    fun provideCartRepository(appDao: AppDao) = CartRepository(appDao)

    @Provides
    @Singleton
    fun provideOrderRepository(appDao: AppDao) = OrderRepository(appDao)

    @Provides
    @Singleton
    fun provideDatabaseInitializer(
        @ApplicationContext context: Context,
        appDao: AppDao
    ): DatabaseInitializer {
        return DatabaseInitializer(
            context = context,
            appDao = appDao,
            coroutineScope = CoroutineScope(Dispatchers.IO)
        )
    }

    @Provides
    @Singleton
    fun provideLocalizationManager(@ApplicationContext context: Context) = LocalizationManager(context)

    @Provides
    @Singleton
    fun provideNotificationService(@ApplicationContext context: Context) = NotificationService(context)
}