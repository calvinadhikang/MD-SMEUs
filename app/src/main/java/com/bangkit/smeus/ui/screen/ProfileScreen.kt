package com.bangkit.smeus.ui.screen

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.bangkit.smeus.ui.UserPreference
import com.bangkit.smeus.ui.components.ButtonForm
import com.bangkit.smeus.ui.components.IconText
import com.bangkit.smeus.ui.components.InputForm
import com.bangkit.smeus.ui.main.MainActivity
import com.bangkit.smeus.ui.model.City

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
    ) {
        val context = LocalContext.current
        val preference = UserPreference(context)
        val user = preference.getUser()

        Text(
            text = "Profile",
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = modifier.fillMaxWidth()
        )
        Text(
            text = "edit your profile",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.DarkGray,
            textAlign = TextAlign.Center,
            modifier = modifier.fillMaxWidth()
        )
        Spacer(modifier = modifier.padding(16.dp))

        //Info User Email
        Text(text = "Email :", fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.primary)
        IconText(text = user.email, icon = Icons.Default.Email, textColor = Color.Black, iconTint = MaterialTheme.colorScheme.primary, fontSize = 20.sp)
        //Info User Name
        Text(text = "Name :", fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.primary)
        IconText(text = user.name, icon = Icons.Default.AccountCircle, textColor = Color.Black, iconTint = MaterialTheme.colorScheme.primary, fontSize = 20.sp)
        //Info User Phone
        Text(text = "Phone :", fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.primary)
        IconText(text = user.phone, icon = Icons.Default.Phone, textColor = Color.Black, iconTint = MaterialTheme.colorScheme.primary, fontSize = 20.sp)

        Spacer(modifier = modifier.padding(16.dp))

        ButtonForm(
            text = "Save Profile",
            color = MaterialTheme.colorScheme.primary,
            onClick = {

            },
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 8.dp)
                .height(50.dp)
        )
        ButtonForm(
            text = "Logout",
            color = Color.Red,
            onClick = {
                preference.clearUser()
                val activity = context as Activity
                activity.startActivity(Intent(context, MainActivity::class.java))
            },
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp)
        )
    }
}
