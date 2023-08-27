package com.example.kotlin_flowexample.FlowRoom.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ContactsEntity::class], version = 2, exportSchema = false)
abstract class ContactsDB : RoomDatabase() {
    abstract fun contactsDao(): ContactsDao
}