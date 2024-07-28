package com.aherrera.fakelogin.di

import com.aherrera.fakelogin.data.api.DummyJsonApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideDummyJsonApi(retrofit: Retrofit): DummyJsonApi =
        retrofit.create(DummyJsonApi::class.java)

}