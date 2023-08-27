package com.example.kotlin_flowexample.FlowRoom.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_flowexample.FlowRoom.db.ContactsEntity
import com.example.kotlin_flowexample.FlowRoom.repository.DatabaseRepository
import com.example.kotlin_flowexample.FlowRoom.utils.DataStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DatabaseViewModel @Inject constructor(private val repository: DatabaseRepository) : ViewModel() {

    private val _contactsList = MutableLiveData<DataStatus<List<ContactsEntity>>>()
    val contactsList : LiveData<DataStatus<List<ContactsEntity>>>
        get() = _contactsList

    private val _contactDetails = MutableLiveData<DataStatus<ContactsEntity>>()
    val contactDetails :LiveData<DataStatus<ContactsEntity>>
        get() = _contactDetails

    fun saveContact(isEdite: Boolean, entity: ContactsEntity) = viewModelScope.launch {
        if (isEdite) {
            repository.updateContact(entity)
        } else {
            repository.saveContact(entity)
        }
    }

    fun deleteContact(entity: ContactsEntity) = viewModelScope.launch {
        repository.deleteContact(entity)
    }

    fun getAllContacts() = viewModelScope.launch {
        _contactsList.postValue(DataStatus.loading())
        repository.getAllContacts()
            .catch { _contactsList.postValue(DataStatus.error(it.message.toString())) }
            .collect { _contactsList.postValue(DataStatus.success(it, it.isEmpty())) }
    }

    fun deleteAllContact() = viewModelScope.launch {
        repository.deleteAllContact()
    }

    fun sortedASC() = viewModelScope.launch {
        repository.sortedASC()
            .catch { _contactsList.postValue(DataStatus.error(it.message.toString())) }
            .collect { _contactsList.postValue(DataStatus.success(it, it.isEmpty())) }
    }

    fun sortedDESC() = viewModelScope.launch {
        repository.sortedDESC()
            .catch { _contactsList.postValue(DataStatus.error(it.message.toString())) }
            .collect { _contactsList.postValue(DataStatus.success(it, it.isEmpty())) }
    }

    fun searchContact(name: String) = viewModelScope.launch {
        repository.searchContact(name)
            .collect { _contactsList.postValue(DataStatus.success(it, it.isEmpty())) }
    }

    fun getDetailsContact(id: Int) = viewModelScope.launch {
        repository.getContact(id).collect {
            _contactDetails.postValue(DataStatus.success(it, false))
        }
    }

}