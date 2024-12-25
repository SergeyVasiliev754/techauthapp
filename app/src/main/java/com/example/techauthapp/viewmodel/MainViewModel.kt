package com.example.techauthapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.techauthapp.database.UserDao
import com.example.techauthapp.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val userDao: UserDao) : ViewModel() {

    fun registerUser(username: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val user = User(username = username, password = password)
                userDao.insertUser(user)
                onResult(true)
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }

    fun loginUser(username: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = userDao.loginUser(username, password)
            onResult(user != null)
        }
    }
}
