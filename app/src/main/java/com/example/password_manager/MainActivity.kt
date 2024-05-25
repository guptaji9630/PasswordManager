package com.example.passwordmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.passwordmanager.data.AppDatabase
import com.example.passwordmanager.data.PasswordEntity
import com.example.passwordmanager.repository.PasswordRepository
import com.example.passwordmanager.ui.theme.PasswordManagerTheme
import com.example.passwordmanager.viewmodel.PasswordViewModel
import com.example.passwordmanager.viewmodel.PasswordViewModelFactory

class MainActivity : ComponentActivity() {
    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "passwords-db"
        ).build()
    }
    private val repository by lazy { PasswordRepository(database.passwordDao()) }
    private val viewModel: PasswordViewModel by viewModels {
        PasswordViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PasswordManagerTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    PasswordManagerApp(viewModel)
                }
            }
        }
    }
}