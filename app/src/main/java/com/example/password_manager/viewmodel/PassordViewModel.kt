package com.example.passwordmanager.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordmanager.data.PasswordEntity
import com.example.passwordmanager.repository.PasswordRepository
import kotlinx.coroutines.launch

class PasswordViewModel(private val repository: PasswordRepository) : ViewModel() {

    val passwords = repository.getAllPasswords()

    fun addPassword(password: PasswordEntity) {
        viewModelScope.launch {
            repository.insertPassword(password)
        }
    }

    fun updatePassword(password: PasswordEntity) {
        viewModelScope.launch {
            repository.updatePassword(password)
        }
    }

    fun deletePassword(password: PasswordEntity) {
        viewModelScope.launch {
            repository.deletePassword(password)
        }
    }
}