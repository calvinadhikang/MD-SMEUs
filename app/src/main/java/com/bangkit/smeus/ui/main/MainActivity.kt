package com.bangkit.smeus.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit.smeus.R
import com.bangkit.smeus.ui.components.ButtonForm
import com.bangkit.smeus.ui.components.ButtonFormPreview
import com.bangkit.smeus.ui.components.InputForm
import com.bangkit.smeus.ui.theme.SMEUsTheme
import java.util.Objects

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
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
fun Main(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        val context = LocalContext.current
        var username by rememberSaveable { mutableStateOf("") }
        var usernameErrorText by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }
        var passwordErrorText by rememberSaveable { mutableStateOf("") }

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "",
            modifier = modifier
                .fillMaxWidth()
                .height(300.dp)
        )
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
                text = username,
                label = "Username",
                onValueChange = {
                    username = it
                },
                errorText = usernameErrorText,
                leadingIcon = { Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "") }
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
        ButtonForm(
            text = "Sign In",
            color = Color.Blue,
            onClick = {
                if (username == ""){
                    usernameErrorText = "Username cannot be null"
                }else{
                    usernameErrorText = ""
                }

                if (password == ""){
                    passwordErrorText = "Password cannot be null"
                }else{
                    passwordErrorText = ""
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
                    Toast.makeText( context,"clicked", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MaterialTheme {
        Main()
    }
}