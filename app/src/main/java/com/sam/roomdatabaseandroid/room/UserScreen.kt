package com.sam.roomdatabaseandroid.room

import android.R.attr.onClick
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.TextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import java.nio.file.WatchEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserScreen(
    viewModel: UserViewModel = hiltViewModel()
) {
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }
    var showInsertUserUri by remember { mutableStateOf(false) }

    val users by viewModel.users.collectAsState()

    LaunchedEffect(Unit){
        viewModel.getAllUsers()
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "DatabaseScreen") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)

        ) {
            UsersList(
                modifier = Modifier.weight(1f),
                users = users,
                onInsertUserClick = {
                    showInsertUserUri = true
                }
            )

            AnimatedVisibility (visible = showInsertUserUri) {

                InsertUser(
                    modifier = Modifier.fillMaxWidth(),
                    userName = userName,
                    onUserNameChange = { userName = it },
                    email = email,
                    onEmailChange = { email = it },
                    fullName = fullName,
                    onFullNameChange = { fullName = it },
                    onSaveUser = {
                        viewModel.insertUser(userName, email, fullName)
//                        viewModel.getAllUsers()
                    },
                    onCancel = {
                        showInsertUserUri = false
                    }

                )
            }



        }
    }
}

@Composable
fun UsersList(
    modifier: Modifier = Modifier,
    users: List<User>,
    onInsertUserClick: () -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(users.size) { index ->
            UserCard(
                modifier = Modifier.fillMaxWidth(), //Added this line
                user = users[index]
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
    onSaveUser: () -> Unit,
    onCancel: () -> Unit,
    ) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        TextField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            value = userName,
            onValueChange = { onUserNameChange(it) },
            placeholder = { Text("Enter user Name") },
            singleLine = true

        )
        TextField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            value = email,
            onValueChange = { onEmailChange(it) },
            placeholder = { Text("Enter email") },
            singleLine = true
        )
        TextField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            value = fullName,
            onValueChange = { onFullNameChange(it) },
            placeholder = { Text("Enter full Name") },
            singleLine = true
        )
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
fun UserCard(
    modifier: Modifier = Modifier,
    user: User
) {
    Card (
        modifier = modifier
    ){
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
          Text(text = "User Id: ${user.id}")
          Text(text = "User Name: ${user.userName}")

        }
    }

}

