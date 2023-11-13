package androidx.compose.samples.crane.sideEffects

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun ListComposable() {
    val categoryState = rememberSaveable {
        mutableStateOf(emptyList<String>())
    }

    // it will execute just one time.
    // This code will excute on the basis of key. If key changes then it will
    // execute again.
    LaunchedEffect(key1 = Unit){
        categoryState.value = fetchList()
    }
    

    LazyColumn(modifier = Modifier.fillMaxSize(1f)){
        items(categoryState.value){
            Text(text = it)
        }
    }
    
}

fun fetchList(): List<String> {
    return listOf("One","Two","Three","Four")
}
