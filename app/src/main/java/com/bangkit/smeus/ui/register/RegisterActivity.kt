package com.bangkit.smeus.ui.register

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit.smeus.ui.components.ButtonForm
import com.bangkit.smeus.ui.components.ButtonFormWithLoading
import com.bangkit.smeus.ui.components.InputForm

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Register()
                }
            }
        }
    }
}

@Composable
fun Register(
    viewModel: RegisterViewModel = RegisterViewModel(),
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val isLoading by viewModel.loading.observeAsState(false)
    val registerMessage by viewModel.responseMessage.observeAsState("")

    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    var nameErrorText by rememberSaveable { mutableStateOf("") }
    var emailErrorText by rememberSaveable { mutableStateOf("") }
    var phoneErrorText by rememberSaveable { mutableStateOf("") }
    var passwordErrorText by rememberSaveable { mutableStateOf("") }
    var confirmPasswordErrorText by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
    ) {
        Text(
            text = "Create Account",
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = modifier.fillMaxWidth()
        )
        Text(
            text = "Create a New Account",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.DarkGray,
            textAlign = TextAlign.Center,
            modifier = modifier.fillMaxWidth()
        )
        Spacer(modifier = modifier.padding(16.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier.padding(bottom = 8.dp)
        ) {
            InputForm(
                text = name,
                label = "Name",
                errorText = nameErrorText,
                onValueChange = {
                    name = it
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "")
                }
            )
            InputForm(
                text = email,
                label = "Email",
                errorText = emailErrorText,
                onValueChange = {
                    email = it
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Email, contentDescription = "")
                }
            )
            InputForm(
                text = phone,
                label = "Phone",
                errorText = phoneErrorText,
                onValueChange = {
                    phone = it
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Phone, contentDescription = "")
                }
            )
            InputForm(
                text = password,
                label = "Password",
                errorText = passwordErrorText,
                onValueChange = {
                    password = it
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = "")
                }
            )
            InputForm(
                text = confirmPassword,
                label = "Confirm Password",
                errorText = confirmPasswordErrorText,
                onValueChange = {
                    confirmPassword = it
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = "")
                }
            )
        }
        Text(
            text = registerMessage,
            color = Color.Red
        )
        ButtonFormWithLoading(
            isLoading = isLoading,
            text = "Sign Up",
            color = Color.Blue,
            onClick = {
                var valid = true

                nameErrorText = if (name == "") {
                    "Name cannot be null"
                } else {
                    ""
                }

                emailErrorText = if (email == ""){
                    "Email cannot be null"
                }else{
                    ""
                }

                phoneErrorText = if (phone == ""){
                    "Phone cannot be null"
                }else{
                    ""
                }

                passwordErrorText = if (password == ""){
                    "Password cannot be null"
                }else{
                    ""
                }

                confirmPasswordErrorText = if (confirmPassword == ""){
                    "Confirm Password cannot be null"
                }else{
                    ""
                }

                if (confirmPassword != password){
                    confirmPasswordErrorText = "Password must be the same with Confirmation Password"
                }

                if (name == "" || email == "" || phone == "" || password == "" || confirmPassword == "" || password != confirmPassword) {
                    valid = false
                }

                if (valid){
                    viewModel.register(name, email, phone, password)
//                    Toast.makeText(context, "Registered Successfully", Toast.LENGTH_SHORT).show()
//                    val activity = (context as Activity)
//                    activity.finish()
                }else{
                    Toast.makeText(context, "Registered Unsuccessful", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(top = 8.dp, bottom = 16.dp)
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                text = "Already have an account? ",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Sign In",
                fontSize = 12.sp,
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                modifier = modifier.clickable {
                    val activity = (context as Activity)
                    activity.finish()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    MaterialTheme {
        Register()
    }
}