package androidx.compose.samples.crane.QuotesApp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp



@Composable
fun QuotesListItem(quote: Quote,onclick:(quote:Quote)->Unit) {
    Card(elevation = 4.dp, modifier = Modifier.padding(8.dp)
        .clickable { onclick(quote) }) {
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
                Text(text = quote.text,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(0.dp,0.dp,0.dp,8.dp)
                    )
                Box(modifier = Modifier
                    .background(Color.Black)
                    .fillMaxWidth(.4f)
                    .height(1.dp)
                )
                Text(text = quote.author,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Thin,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

