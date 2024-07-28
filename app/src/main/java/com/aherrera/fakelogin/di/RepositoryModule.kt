package com.aherrera.fakelogin.di

import com.aherrera.fakelogin.data.api.DummyJsonApi
import com.aherrera.fakelogin.data.dataSource.remote.LoginRemoteDataSourceImpl
import com.aherrera.fakelogin.data.repository.LoginRepositoryImpl
import com.aherrera.fakelogin.domain.repository.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindsLoginRepository(
        repositoryImpl: LoginRepositoryImpl
    ): LoginRepository
}

@Module
@InstallIn(ViewModelComponent::class)
internal object RepositoryModuleImpl {

    @ViewModelScoped
    @Provides
    internal fun providesLoginRepositoryImpl(
        api: DummyJsonApi
    ): LoginRepositoryImpl = LoginRepositoryImpl(
        ioDispatcher = DispatchersModule.provideIODispatcher(),
        loginRemoteDs = LoginRemoteDataSourceImpl(
            api = api,
        )
    )
}
