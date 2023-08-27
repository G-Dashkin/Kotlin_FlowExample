package com.example.kotlin_flowexample.FlowRoom.repository

import com.example.kotlin_flowexample.FlowRoom.db.ContactsDao
import com.example.kotlin_flowexample.FlowRoom.db.ContactsEntity
import javax.inject.Inject

class DatabaseRepository @Inject constructor(private val dao: ContactsDao) {
    suspend fun saveContact(entity : ContactsEntity) = dao.saveContact(entity)
    fun getAllContacts() = dao.getAllContacts()
    fun deleteAllContact() = dao.deleteAllContacts()
    fun sortedASC() = dao.sortedASC()
    fun sortedDESC() = dao.sortedDESC()
    fun searchContact(name: String) = dao.searchContact(name)
    suspend fun updateContact(entity: ContactsEntity) = dao.updateContact(entity)
    suspend fun deleteContact(entity: ContactsEntity) = dao.deleteContact(entity)
    fun getContact(id:Int) = dao.getContact(id)
}