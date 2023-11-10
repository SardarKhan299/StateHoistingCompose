package androidx.compose.samples.crane.QuotesApp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.samples.crane.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp



@Composable
fun QuotesListItem() {
    Card(elevation = 4.dp, modifier = Modifier.padding(8.dp)) {
        Row (modifier = Modifier.padding(16.dp)){
            Image(imageVector = Icons.Filled.Close, contentDescription ="",
                colorFilter = ColorFilter.tint(Color.White),
                alignment = Alignment.TopStart,
                modifier = Modifier
                    .size(40.dp)
                    .rotate(180f)
                    .background(Color.Black)
                )
            Spacer(modifier = Modifier.padding(4.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "This is the QUote APp to save and retreive quotes from the database.",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(0.dp,0.dp,0.dp,8.dp)
                    )
                Box(modifier = Modifier
                    .background(Color.Black)
                    .fillMaxWidth(.4f)
                    .height(1.dp)
                )
                Text(text = "By Ali Khan",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Thin,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun QuoteDetail() {
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize(1f)
            .background(Brush.sweepGradient(colors = listOf(Color.Red, Color.Green)))
        ){

        Card(elevation = 4.dp,
            modifier = Modifier.padding(32.dp)
            ) {

            Column(verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp, 24.dp)) {
                Image(imageVector = Icons.Filled.ExitToApp, contentDescription = "Quote",
                    modifier = Modifier
                        .size(80.dp)
                        .rotate(180F)
                    )
                Text(text = "Kotlin By Tutorials. Learn With Us",
                    fontFamily = FontFamily(Font(R.font.raleway_semibold)),
                        style = MaterialTheme.typography.h2
                    )

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Quote By Ali Khan",
                    fontFamily = FontFamily(Font(R.font.raleway_light)),
                        style = MaterialTheme.typography.body1
                        )


            }

        }
    }
}