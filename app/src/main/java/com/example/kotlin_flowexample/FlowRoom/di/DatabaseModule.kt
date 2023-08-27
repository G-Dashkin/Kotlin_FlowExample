package com.example.kotlin_flowexample.FlowRoom.di

import android.content.Context
import androidx.room.Room
import com.example.kotlin_flowexample.FlowRoom.db.ContactsDB
import com.example.kotlin_flowexample.FlowRoom.db.ContactsEntity
import com.example.kotlin_flowexample.FlowRoom.utils.Constants.CONTACTS_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, ContactsDB::class.java, CONTACTS_DATABASE
    ).allowMainThreadQueries()
        .fallbackToDestructiveMigration().build()


    @Provides
    @Singleton
    fun provideDao(db:ContactsDB) = db.contactsDao()


    @Provides
    @Singleton
    fun provideEntity()= ContactsEntity()

}