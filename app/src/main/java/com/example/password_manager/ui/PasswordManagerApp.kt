package com.example.passwordmanager

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.passwordmanager.data.PasswordEntity
import com.example.passwordmanager.viewmodel.PasswordViewModel

@Composable
fun PasswordManagerApp(viewModel: PasswordViewModel = viewModel()) {
    val passwords by viewModel.passwords.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Password Manager") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                // Handle Add Password
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Password")
            }
        }
    ) {
        PasswordList(passwords, viewModel)
    }
}

@Composable
fun PasswordList(passwords: List<PasswordEntity>, viewModel: PasswordViewModel) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(passwords) { password ->
            PasswordItem(password, viewModel)
        }
    }
}

@Composable
fun PasswordItem(password: PasswordEntity, viewModel: PasswordViewModel) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable { isExpanded = !isExpanded }
        .padding(16.dp)) {
        Text(text = password.accountType)
        if (isExpanded) {
            Text(text = "Username: ${password.username}")
            Text(text = "Password: ${password.password}")
            Row {
                Button(onClick = {
                    // Handle Edit
                }) {
                    Text("Edit")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    viewModel.deletePassword(password)
                }) {
                    Text("Delete")
                }
            }
        }
    }
}