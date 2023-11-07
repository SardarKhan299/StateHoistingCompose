package androidx.compose.samples.crane.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.samples.crane.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun NotificationScreen() {
    var count = rememberSaveable { mutableIntStateOf(0) }
    Column (verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(1f)){
        NotificationCounter(count.value) { count.intValue++ }
        MessageBar(count.value)
    }
}

@Composable
fun NotificationCounter(count:Int,valueIncremented:()->Unit) {

    Column {
        Text(text = "You have sent ${count} notifications",
            modifier = Modifier.background(Color.White))
        Button(onClick = { valueIncremented()}) {
            Text(text = "Send Notifications")
        }
    }
}

@Composable
fun MessageBar(count: Int) {
    Card(elevation = 4.dp) {

        Row (modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically){
            Image(imageVector = Icons.Outlined.Favorite, contentDescription ="",
                Modifier.padding(8.dp))

            Text(text = "Message send so far ${count}")

        }

    }
}