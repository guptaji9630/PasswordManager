package com.example.passwordmanager.repository

import com.example.passwordmanager.data.PasswordDao
import com.example.passwordmanager.data.PasswordEntity

class PasswordRepository(private val passwordDao: PasswordDao) {

    suspend fun getAllPasswords() = passwordDao.getAllPasswords()

    suspend fun insertPassword(password: PasswordEntity) = passwordDao.insertPassword(password)

    suspend fun updatePassword(password: PasswordEntity) = passwordDao.updatePassword(password)

    suspend fun deletePassword(password: PasswordEntity) = passwordDao.deletePassword(password)
}