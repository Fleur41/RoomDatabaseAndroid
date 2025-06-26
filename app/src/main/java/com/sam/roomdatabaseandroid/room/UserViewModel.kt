package com.sam.roomdatabaseandroid.room

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class UserViewModel @Inject constructor(
   private val userRepository: UserRepository
): ViewModel() {
    fun insertUser(
        userName: String,
        email: String,
        fullName: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.insertUser(userName, email, fullName)
        }
    }
}