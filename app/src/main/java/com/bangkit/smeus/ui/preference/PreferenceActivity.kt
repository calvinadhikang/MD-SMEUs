package com.bangkit.smeus.ui.preference

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bangkit.smeus.R
import com.bangkit.smeus.ui.UserPreference
import com.bangkit.smeus.ui.model.Category
import com.bangkit.smeus.ui.model.City
import com.bangkit.smeus.ui.model.PriceRange
import com.bangkit.smeus.ui.theme.SMEUsTheme
import com.bangkit.smeus.ui.user.UserActivity

class PreferenceActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SMEUsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PreferenceScreen()
                }
            }
        }
    }
}

@Composable
fun PreferenceScreen(
    modifier: Modifier = Modifier,
    viewModel: PreferenceViewModel = viewModel()
) {
    val context = LocalContext.current
    val preference = UserPreference(context)

    var location = viewModel.location.collectAsState()
    var category = viewModel.category.collectAsState()
    var priceRange = viewModel.priceRange.collectAsState()
    var rating = viewModel.rating.collectAsState()

    var state by rememberSaveable { mutableStateOf(0)}

    Column {
        when (state) {
            0 -> {
                LocationScreen(
                    city = location.value,
                    onClick = { state += it },
                    onChange = { viewModel.changeLocation(it) }
                )
            }
            1 -> {
                CategoryScreen(
                    category = category.value,
                    onClick = { state += it },
                    onChange = { viewModel.changeCategory(it) }
                )
            }
            2 -> {
                PriceRangeScreen(
                    priceRange = priceRange.value,
                    onClick = { state += it },
                    onChange = { viewModel.changePriceRange(it) }
                )
            }
            3 -> {
                RatingScreen(
                    rating = rating.value,
                    onClick = { state += it },
                    onChange = { viewModel.changeRating(it) },
                )
            }
            4 -> {
                PreferenceResultScreen(
                    city = location.value.id,
                    category = category.value.id,
                    price = priceRange.value.id,
                    rating = rating.value,
                    onFinish = {
                        preference.updateUserPreference(category.value.id, location.value.id, rating.value, priceRange.value)
                        val activity = context as Activity
                        activity.startActivity(Intent(context, UserActivity::class.java))
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun LocationScreen(
    modifier: Modifier = Modifier,
    city: City,
    onClick: (it: Int) -> Unit,
    onChange: (city: City) -> Unit,
){
    val cityList = City.listCity
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Choose Your Location",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 32.sp,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
//            Row(
//                horizontalArrangement = Arrangement.Center,
//                modifier = modifier.fillMaxWidth()
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.location),
//                    contentDescription = "",
//                    modifier = modifier
//                        .fillMaxWidth(0.8F)
//                )
//            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
            ) {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = !expanded
                    }
                ) {
                    TextField(
                        value = city.text,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = modifier.fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        cityList.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(text = item.text) },
                                onClick = {
                                    onChange(item)
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        }
        Button(
            onClick = { onClick(1) },
            content = {
                Text(
                    text = "Next",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            },
            modifier = modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(bottom = 24.dp, start = 24.dp, end = 24.dp)
        )
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    category: Category,
    onClick: (it: Int) -> Unit,
    onChange: (it: Category) -> Unit,
){
    val categoryList = Category.listCategory
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Category of SME",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 32.sp,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
//            Row(
//                horizontalArrangement = Arrangement.Center,
//                modifier = modifier.fillMaxWidth()
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.category),
//                    contentDescription = "",
//                    modifier = modifier
//                        .fillMaxWidth(0.8F)
//                )
//            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
            ) {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = !expanded
                    }
                ) {
                    TextField(
                        value = category.text,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = modifier.fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        categoryList.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(text = item.text) },
                                onClick = {
                                    onChange(item)
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        }
        Row(
            modifier =  modifier.align(Alignment.BottomCenter)
        ) {
            Button(
                onClick = { onClick(-1) },
                content = {
                    Text(
                        text = "Back",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                ),
                modifier = modifier
                    .weight(1F)
                    .padding(bottom = 24.dp, start = 24.dp, end = 12.dp)
            )
            Button(
                onClick = { onClick(1) },
                content = {
                    Text(
                        text = "Next",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                },
                modifier = modifier
                    .weight(1F)
                    .padding(bottom = 24.dp, start = 12.dp, end = 24.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PriceRangeScreen(
    modifier: Modifier = Modifier,
    priceRange: PriceRange,
    onClick: (it: Int) -> Unit,
    onChange: (it: PriceRange) -> Unit,
){
    val priceRangeList = PriceRange.listPriceRange
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Price Range",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 32.sp,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
//            Row(
//                horizontalArrangement = Arrangement.Center,
//                modifier = modifier.fillMaxWidth()
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.price),
//                    contentDescription = "",
//                    modifier = modifier
//                        .fillMaxWidth(0.8F)
//                )
//            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
            ) {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = !expanded
                    }
                ) {
                    TextField(
                        value = priceRange.text,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = modifier.fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        priceRangeList.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(text = item.text) },
                                onClick = {
                                    onChange(item)
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        }
        Row(
            modifier =  modifier.align(Alignment.BottomCenter)
        ) {
            Button(
                onClick = { onClick(-1) },
                content = {
                    Text(
                        text = "Back",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                ),
                modifier = modifier
                    .weight(1F)
                    .padding(bottom = 24.dp, start = 24.dp, end = 12.dp)
            )
            Button(
                onClick = { onClick(1) },
                content = {
                    Text(
                        text = "Next",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                },
                modifier = modifier
                    .weight(1F)
                    .padding(bottom = 24.dp, start = 12.dp, end = 24.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RatingScreen(
    modifier: Modifier = Modifier,
    rating: Int,
    onClick: (it: Int) -> Unit,
    onChange: (it: Int) -> Unit,
){
    val ratingList = listOf<String>("1","2","3","4","5")
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Choose Rating",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 32.sp,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
//            Row(
//                horizontalArrangement = Arrangement.Center,
//                modifier = modifier.fillMaxWidth()
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.rating),
//                    contentDescription = "",
//                    modifier = modifier
//                        .fillMaxWidth(0.8F)
//                )
//            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
            ) {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = !expanded
                    }
                ) {
                    TextField(
                        value = rating.toString(),
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = modifier.fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        ratingList.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(text = item) },
                                onClick = {
                                    onChange(Integer.parseInt(item))
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        }
        Row(
            modifier =  modifier.align(Alignment.BottomCenter)
        ) {
            Button(
                onClick = { onClick(-1) },
                content = {
                    Text(
                        text = "Back",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                ),
                modifier = modifier
                    .weight(1F)
                    .padding(bottom = 24.dp, start = 24.dp, end = 12.dp)
            )
            Button(
                onClick = { onClick(1) },
                content = {
                    Text(
                        text = "Next",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                },
                modifier = modifier
                    .weight(1F)
                    .padding(bottom = 24.dp, start = 12.dp, end = 24.dp)
            )
        }
    }
}

@Composable
fun PreferenceResultScreen(
    city: Int,
    category: Int,
    price: Int,
    rating: Int,
    modifier: Modifier = Modifier,
    onFinish: () -> Unit
){
    val context = LocalContext.current
    val preference = UserPreference(context)
    val good by rememberSaveable { mutableStateOf(preference.predictGoods(
        city,
        category,
        price,
        rating,
        context
    ).text) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxSize()
    ) {
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = "",
            tint = Color.White,
            modifier = modifier
                .fillMaxWidth(0.8F)
                .fillMaxHeight(0.5F)
        )
        Text(text = "Your Preference", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = modifier.padding(top = 8.dp, bottom = 8.dp))
        Text(text = good, color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.ExtraBold)
        Spacer(modifier = modifier.padding(top = 8.dp, bottom = 8.dp))
        Button(
            onClick = { onFinish() },
            content = {
                Text(
                    text = "Finish",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            },
            modifier = modifier
                .height(80.dp)
                .width(200.dp)
                .clip(RoundedCornerShape(8.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SMEUsTheme {
        LocationScreen(city = City.listCity[0], onClick = {}, onChange = {})
    }
}