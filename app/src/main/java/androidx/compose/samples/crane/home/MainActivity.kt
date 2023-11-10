/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.compose.samples.crane.home

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.samples.crane.QuotesApp.DataManager
import androidx.compose.samples.crane.QuotesApp.QuoteListScreen
import androidx.compose.samples.crane.R
import androidx.compose.samples.crane.details.launchDetailsActivity
import androidx.compose.samples.crane.ui.CraneTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.internal.wait

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //WindowCompat.setDecorFitsSystemWindows(window, false)
        CoroutineScope(Dispatchers.IO).launch{
            DataManager.loadAssetsFromFile(this@MainActivity)
        }
        setContent {
            //PreviewFun1()
            App()
        }

//        setContent {
//            CraneTheme {
//                MainScreen(onExploreItemClicked = {
//                    launchDetailsActivity(
//                        context = this,
//                        item = it
//                    )
//                })
//            }
//        }
    }
}
@Preview(showBackground = true, name = "My Quotes App")
@Composable
fun App() {
    if(DataManager.isDataLoaded.value){
        QuoteListScreen(data = DataManager.data) {

        }
    }else{
        Log.d(MainActivity::class.simpleName, "App: Show Loader")
        Box (contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize(1f)){
            Text(text = "Loading...", style = MaterialTheme.typography.h6)

        }
    }
}


@Composable
fun MyComposeabel(name:String="Sardar") {
    Text(text = "Hello $name", fontStyle = FontStyle.Italic,
        fontWeight = FontWeight.W600,
        color = Color.Red,
        fontSize = 12.sp,
        textAlign = TextAlign.Start
    )

    Image(painter = painterResource(id = R.drawable.ic_location),
        contentDescription = "Profile Image",
        colorFilter = ColorFilter.tint(Color.Red)
        )

    Button(onClick = { }, colors = ButtonDefaults.buttonColors(
        contentColor = Color.Red,
        backgroundColor = Color.Blue
    ),
        enabled = false) {
        Image(painter = painterResource(id = R.drawable.ic_person),
            contentDescription = "Image")
        Text(text = "Get Started")
    }
}

@Preview(showBackground = true, name = "Second Preview", widthDp = 200,
    heightDp = 400)
@Composable
private fun PreviewFun(){
    MyComposeabel(name = "Sardar")
}

@Preview(showBackground = true, name = "Third Preview", widthDp = 200,
    heightDp = 400)
@Composable
private fun PreviewFun1(){
    TextFieldTest()
}
@Preview(showBackground = true, name = "Layout Preview", widthDp = 300,
    heightDp = 500)
@Composable
fun LayoutCompose() {

    Column(verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Image(painter = painterResource(id = R.drawable.ic_person)
            , contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(2.dp, Color.White, CircleShape)
            , contentDescription = "Person")
    }

    Row (horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){
        Text(text = "A", fontSize = 25.sp,
            modifier = Modifier
                .background(Color.White)
                .size(150.dp)
                .padding(2.dp)
                .border(4.dp, Color.Red)
                .clip(CircleShape)
                .background(Color.Yellow)
                .clickable { }
                .fillMaxWidth(0.5f)

        )
        Text(text = "B", fontSize = 25.sp)
    }

    Box(contentAlignment = Alignment.BottomCenter) {
        Image(painter = painterResource(id = R.drawable.ic_person), contentDescription = "Person")
        Image(painter = painterResource(id = R.drawable.ic_location), contentDescription = "Location")
    }

}

@Composable
fun TextFieldTest() {
    val state = rememberSaveable {
        mutableStateOf("")
    }
    TextField(value = state.value, onValueChange = {
        Log.d(MainActivity::class.simpleName, "TextFieldTest: $it")
        state.value = it
    }, label = {Text(text = "Enter Message")},
      placeholder = {
          Text(text = "Place Holder")
      })
}

@Composable
fun TextInput() {
    
}


@Composable
private fun MainScreen(onExploreItemClicked: OnExploreItemClicked) {
    Surface(color = MaterialTheme.colors.primary) {
        var showLandingScreen by remember { mutableStateOf(true) }
        if (showLandingScreen) {
            LandingScreen(onTimeout = { showLandingScreen = false })
        } else {
            CraneHome(onExploreItemClicked = onExploreItemClicked)
        }
    }
}
