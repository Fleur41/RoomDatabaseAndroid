package com.sam.roomdatabaseandroid.room

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import java.time.Instant
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
   private val userRepository: UserRepository
): ViewModel() {
//    private val _users = MutableStateFlow<List<User>>(emptyList())
//    val users: StateFlow<List<User>> get() = _users.asStateFlow()

//    val users = userRepository.getAllUsersFlow()
    val users = emptyFlow<List<User>>()
//    @OptIn(ExperimentalCoroutinesApi::class)
    fun insertUser(
        id: Long? = null,
        userName: String,
        email: String,
        fullName: String,
        city: String,
        aadhaarId: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            if (id == null){
                val userId = userRepository.insertUser(userName, email, fullName, Address(city))
                userRepository.insertAadhaarCard(AadhaarCard(id = aadhaarId, userId = userId))
            } else{
                val user = User(id, userName, email, fullName, Address(city), Instant.now())
                userRepository.insertUser(user)
            }
        }
    }


    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.deleteUser(user)
        }
    }


//    fun getAllUsers() {
//        viewModelScope.launch(Dispatchers.IO) {
//            val users = userRepository.getAllUsers()
//            _users.value = users
//        }
//    }
}