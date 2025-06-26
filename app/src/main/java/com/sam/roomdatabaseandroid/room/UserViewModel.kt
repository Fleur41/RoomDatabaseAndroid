package com.sam.roomdatabaseandroid.room

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class UserViewModel @Inject constructor(
   private val userRepository: UserRepository
): ViewModel() {
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> get() = _users.asStateFlow()

    fun insertUser(
        userName: String,
        email: String,
        fullName: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.insertUser(userName, email, fullName)
        }
    }

    fun getAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            val users = userRepository.getAllUsers()
            _users.value = users
            }
        }
}