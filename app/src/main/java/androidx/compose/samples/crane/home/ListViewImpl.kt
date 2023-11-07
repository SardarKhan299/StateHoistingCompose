package androidx.compose.samples.crane.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.samples.crane.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true, heightDp = 400, name = "My ListPreview")
@Composable
fun PreviewItem() {

    LazyColumn(content = {
        items(getCategoryList()){item->
            BlogCategory(img = item.image, title = item.title,
                subTitle = item.subTitle )
        }

    })
}

@Composable
fun BlogCategory(img:Int,title:String,subTitle:String) {
    Card(elevation = 8.dp, modifier = Modifier.padding(8.dp)) {
        Row {
            Image(painter = painterResource(id = img)
                , contentDescription = title,
                modifier = Modifier
                    .size(80.dp)
                    .padding(8.dp)
                    .background(Color.Red, RectangleShape)
                    .weight(0.2f)

            )
            ItemDescription(title,subTitle, Modifier.weight(0.8f))
        }
    }
}

@Composable
private fun ItemDescription(title:String,subTitle: String,modifier: Modifier) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.h6
        )
        Text(
            text = subTitle,
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Thin
        )

    }
}

data class Category(val image:Int,val title:String,val subTitle:String)

private fun getCategoryList(): MutableList<Category> {
    val list = mutableListOf<Category>()
    list.add(Category(R.drawable.ic_plane,"Kotlin","Learn Kotlin With Us"))
    list.add(Category(R.drawable.ic_location,"Java","Learn Java With Us"))
    list.add(Category(R.drawable.ic_person,"Python","Learn Pythin With Us"))
    list.add(Category(R.drawable.ic_crane_drawer,"C++","Learn C++ With Us"))
    list.add(Category(R.drawable.ic_plane,"Kotlin","Learn Kotlin With Us"))
    list.add(Category(R.drawable.ic_location,"Java","Learn Java With Us"))
    list.add(Category(R.drawable.ic_person,"Python","Learn Pythin With Us"))
    list.add(Category(R.drawable.ic_crane_drawer,"C++","Learn C++ With Us"))

    return list


}
@Preview(showBackground = true)
@Composable
fun Recomposable() {
    val state = remember { mutableStateOf(0.0) }
    Log.d(Log::class.simpleName, "Recomposable: Initial Composition")
    Button(onClick = {
        state.value = Math.random()
    }) {
        Log.d(Log::class.simpleName, "Recomposable: During Both Recomposition and Composition")
        Text(text = state.value.toString())
    }
}

