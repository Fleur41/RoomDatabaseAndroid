package com.sam.roomdatabaseandroid.room

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserScreen(
    viewModel: UserViewModel = hiltViewModel()
) {
//    val scrollState = rememberScrollState()
    var id by remember { mutableStateOf<Long?>(null) }
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var aadhaarCardNumber by remember { mutableStateOf("") }
    var showInsertUserUri by remember { mutableStateOf(false) }
    var isUsernameError by remember { mutableStateOf(false) }
    var isEmailError by remember { mutableStateOf(false) }
    var isFullNameError by remember { mutableStateOf(false) }
    var isCityError by remember { mutableStateOf(false) }
    var isAadhaarCardNumberError by remember { mutableStateOf(false) }

//    val users by viewModel.users.collectAsState()
    val users by viewModel.users.collectAsState(emptyList())

//    LaunchedEffect(Unit){
//        viewModel.getAllUsers()
//    }

    //Clear data
    fun clearData() {
        id = null
        userName = ""
        email = ""
        fullName = ""
        showInsertUserUri = false
    }

    //Clear error
    fun clearError() {
        isUsernameError = false
        isEmailError = false
        isFullNameError = false
        isCityError = false
        isAadhaarCardNumberError = false
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "User List") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
//                .verticalScroll(scrollState)
        ) {
            UsersList(
                modifier = Modifier.weight(1f),
                users = users,
//                users = users,
                onInsertUserClick = {
                    showInsertUserUri = true
                },
                onEditClick = {user ->
                    id = user.user.id
                    userName = user.user.userName
                    email = user.user.email
                    fullName = user.user.fullName
                    showInsertUserUri = true
                },
                onDeleteClick = {user ->
                    viewModel.deleteUser(user.user)
                }

            )

            AnimatedVisibility (visible = showInsertUserUri) {

                InsertUser(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    userName = userName,
                    onUserNameChange = { userName = it },
                    email = email,
                    onEmailChange = { email = it },
                    fullName = fullName,
                    city = city,
                    onCityChange = { city = it },
                    aadhaarCardNumber = aadhaarCardNumber,
                    onAadhaarCardNumberChange = { aadhaarCardNumber = it },
                    isAadhaarCardNumberError = isAadhaarCardNumberError,
                    onFullNameChange = { fullName = it },
                    isUsernameError = isUsernameError,
                    isEmailError = isEmailError,
                    isFullNameError = isFullNameError,
                    isCityError = isCityError,
                    onSaveUser = {
                        clearError()

                        if(userName.isBlank()) isUsernameError = true
                        if(email.isBlank()) isEmailError = true
                        if(fullName.isBlank()) isFullNameError = true
                        if(city.isBlank()) isCityError = true
                        if(aadhaarCardNumber.isBlank()) isAadhaarCardNumberError = true

                        if(isUsernameError || isEmailError || isFullNameError || isCityError) return@InsertUser

                        viewModel.insertUser(id, userName, email, fullName, city, aadhaarCardNumber)
                        clearData()
//                        viewModel.getAllUsers()
                    },
                    onCancel = {
                        clearData()
                    }

                )
            }



        }
    }
}

@Composable
fun UsersList(
    modifier: Modifier = Modifier,
//    users: List<User>,
    users: List<UserAndAadhaarCard>,
    onInsertUserClick: () -> Unit,
    onEditClick: (UserAndAadhaarCard) -> Unit,
    onDeleteClick: (UserAndAadhaarCard) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
//        items(users.size) { index ->
////            val wrapperItem = users[index]
//            UserCard(
//                modifier = Modifier.fillMaxWidth(), //Added this line
////                user = wrapperItem,
//                user = users[index],
//                onEditClick = onEditClick,
//                onDeleteClick = onDeleteClick
//
//            )
//        }

        items(users) { user ->
//            val wrapperItem = users[index]
            UserCard(
                modifier = Modifier.fillMaxWidth(), //Added this line
                user = user,
                onEditClick = onEditClick,
                onDeleteClick = onDeleteClick

            )
        }

        item{
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center

            ) {
                Button(onClick = { onInsertUserClick() }) {
                    Text(text = "Insert User")
                }
            }
        }

    }
}

@Composable
fun InsertUser(
    modifier: Modifier = Modifier,
    userName : String,
    onUserNameChange : (String) -> Unit,
    email : String,
    onEmailChange : (String) -> Unit,
    fullName : String,
    onFullNameChange : (String) -> Unit,
    city: String,
    onCityChange: (String) -> Unit,
    aadhaarCardNumber: String,
    onAadhaarCardNumberChange: (String) -> Unit,
    isUsernameError: Boolean,
    isEmailError: Boolean,
    isFullNameError: Boolean,
    isCityError: Boolean,
    isAadhaarCardNumberError: Boolean,
    onSaveUser: () -> Unit,
    onCancel: () -> Unit,
    ) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        MyTextField(
            value = userName,
            onValueChange = onUserNameChange,
            placeholderText = "Enter your username",
            isError = isUsernameError,
            supportingText = "Please enter your user name..."
        )

        MyTextField(
            value = email,
            onValueChange = onEmailChange,
            placeholderText = "Enter your email",
            isError = isEmailError,
            supportingText = "Please enter your email..."
        )

        MyTextField(
            value = fullName,
            onValueChange = onFullNameChange,
            placeholderText = "Enter your full name",
            isError = isFullNameError,
            supportingText = "Please enter your Full Name..."
        )
        MyTextField(
            value = city,
            onValueChange = onCityChange,
            placeholderText = "Enter your city",
            isError = isCityError,
            supportingText = "Please enter your City..."
        )
        MyTextField(
            value = aadhaarCardNumber,
            onValueChange = onAadhaarCardNumberChange,
            placeholderText = "Enter your Aadhaar card number",
            isError = isAadhaarCardNumberError,
            supportingText = "Please enter your Aadhaar card number..."
        )

//        TextField(
//            modifier = Modifier
//                .padding(horizontal = 16.dp)
//                .fillMaxWidth(),
//            value = fullName,
//            onValueChange = { onFullNameChange(it) },
//            placeholder = { Text("Enter full name") },
//            singleLine = true,
//            isError = isFullNameError,
//            supportingText = {
//                if (isFullNameError) {
//                    Text(text = "Please enter your Full Name...")
//                }
//            }
//        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { onSaveUser() }
            ) {
                Text(text = "Save User")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { onCancel() }
            ) {
                Text(text = "Cancel")
            }
        }
    }
}

@Composable
fun MyTextField(
    modifier: Modifier = Modifier
        .padding(horizontal = 16.dp)
        .fillMaxWidth(),
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    isError: Boolean,
    supportingText: String,
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholderText) },
        singleLine = true,
        isError = isError,
        supportingText = {
            if (isError) {
                Text(text = supportingText)
            }
        }
    )
}
@Composable
fun UserCard(
    modifier: Modifier = Modifier,
//    user: User,
//    onEditClick: (User) -> Unit,
//    onDeleteClick: (User) -> Unit
    user: UserAndAadhaarCard,
    onEditClick: (UserAndAadhaarCard) -> Unit,
    onDeleteClick: (UserAndAadhaarCard) -> Unit

) {
    Card(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.weight(1f)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
//                    Text(text = "User Id: ${user.id}")
//                    Text(text = "User Name: ${user.userName}")
//                    Text(text = "City: ${user.address.city}")
//                    Text(text = "Created At: ${user.createdAt}")
                    Text(text = "User Id: ${user.user.id}")
                    Text(text = "User Name: ${user.user.userName}")
                    Text(text = "City: ${user.user.address.city}")
                    Text(text = "Created At: ${user.user.createdAt}")
                    Text(text = "Aadhaar id: ${user.aadhaarCard?.id}")

                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(onClick = {onEditClick(user)}) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null
                    )
                }

                IconButton(onClick = {onDeleteClick(user)}) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null
                    )
                }
            }

        }

    }
}

