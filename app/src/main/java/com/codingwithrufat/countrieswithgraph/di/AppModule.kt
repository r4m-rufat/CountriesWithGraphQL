package com.codingwithrufat.countrieswithgraph.di

import android.app.Application
import android.content.Context
import com.apollographql.apollo3.ApolloClient
import com.codingwithrufat.countrieswithgraph.helper.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideURL(): String {
        return BASE_URL
    }

    @Provides
    fun provideApolloClient(url: String): ApolloClient{
        return ApolloClient.Builder().serverUrl(url).build()
    }

    @Singleton
    @Provides
    fun provideContext(application:Application): Context = application.applicationContext

}