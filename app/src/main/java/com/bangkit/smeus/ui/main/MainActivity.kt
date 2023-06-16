package com.bangkit.smeus.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bangkit.smeus.R
import com.bangkit.smeus.ui.components.ButtonFormWithLoading
import com.bangkit.smeus.ui.components.InputForm
import com.bangkit.smeus.ui.register.RegisterActivity
import com.bangkit.smeus.ui.theme.SMEUsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SMEUsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Main()
                }
            }
        }
    }
}

@Composable
fun Main(
    viewModel: MainViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    viewModel.checkUserSaved(context)

    val isLoading by viewModel.loading.observeAsState(false)

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var emailErrorText by rememberSaveable { mutableStateOf("") }
    var passwordErrorText by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
//        Row(
//            horizontalArrangement = Arrangement.Center,
//            modifier = modifier.fillMaxWidth()
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.teammin),
//                contentDescription = "",
//                modifier = modifier
//                    .fillMaxWidth(0.8F)
//            )
//        }
        Spacer(modifier = modifier.height(50.dp))
        Text(
            text = "Sign in to start your",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 35.sp,
        )
        Text(
            text = "journey",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 35.sp,
        )
        Spacer(modifier = modifier.height(35.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(
                space = 8.dp
            ),
            modifier = modifier.padding(bottom = 8.dp)
        ) {
            InputForm(
                text = email,
                label = "Email",
                onValueChange = {
                    email = it
                },
                errorText = emailErrorText,
                leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "") }
            )
            InputForm(
                text = password,
                label = "Password",
                onValueChange = {
                    password = it
                },
                errorText = passwordErrorText,
                leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "") }
            )
        }
        ButtonFormWithLoading(
            isLoading = isLoading,
            text = "Sign In",
            color = MaterialTheme.colorScheme.primary,
            onClick = {
                var valid = true

                emailErrorText = if (email == ""){
                    "Username cannot be null"
                }else{
                    ""
                }

                passwordErrorText = if (password == ""){
                    "Password cannot be null"
                }else{
                    ""
                }

                if (email != "" && password != ""){
                    viewModel.login(email, password, context)
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
                text = "Don't have an account ",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Create an Account",
                fontSize = 12.sp,
                color = Color.Blue,
                fontWeight = FontWeight.Bold,
                modifier = modifier.clickable {
                    context.startActivity(Intent(context, RegisterActivity::class.java))
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    SMEUsTheme {
        Main()
    }
}