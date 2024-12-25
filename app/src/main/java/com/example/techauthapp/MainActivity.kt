package com.example.techauthapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import com.example.techauthapp.database.AppDatabase
import com.example.techauthapp.ui.theme.TechAuthAppTheme
import com.example.techauthapp.screens.AuthScreen
import com.example.techauthapp.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Инициализация базы данных
        val authDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "auth_database"
        ).build()

        val userDao = authDatabase.userDao()
        mainViewModel = MainViewModel(userDao)

        setContent {
            TechAuthAppTheme {
                AuthScreen(mainViewModel) // Запускаем экран авторизации
            }
        }
    }
}
