package com.example.android.hilt.di

import com.example.android.hilt.navigator.AppNavigator
import com.example.android.hilt.navigator.AppNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/**
 * AppNavigator bir interface olduğu için constructor enjeksiyonu kullanamayız.
 * Hilt'e interface için hangi implementationun kullanılacağını söylemek için
 * Hilt modülü içindeki bir fonksiyona @Binds annotationu kullanabilirsiniz.
 *
 * Bind abstract bir sinif modulunun icinde abstract bir functiona yazilir.
 *
 * Soyut fonksiyonun dönüş tipi, implementasyon sağlamak istediğimiz interfacedir (yani, AppNavigator).
 * Implementasyon, interface implementasyon tipine (yani, AppNavigatorImpl) benzersiz bir parametre eklenerek belirtilir.
 */
@Module
@InstallIn(ActivityComponent::class) //appnavigatorimp bir activity dependencysi gerektiriyor
abstract class NavigationModule {
    @Binds //bir interface e dependency saglamak icin bu annotation gereklidir
    abstract fun bindNavigator(imp: AppNavigatorImpl) : AppNavigator
}