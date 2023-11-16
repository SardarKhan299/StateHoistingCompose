package androidx.compose.samples.crane.sideEffects

import android.media.MediaPlayer
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.samples.crane.R
import androidx.compose.samples.crane.home.LandingScreen
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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


@Composable
fun LaunchEffectComposable() {
    val counter = rememberSaveable { mutableIntStateOf(0) }

    // it gave us the scope to launch coroutine..//
    // no effect of recomposition.//
    // all side-effects runs in controlled environment.//
    var scope = rememberCoroutineScope()


    LaunchedEffect(key1 = Unit){
        Log.d("Launched Effect", "LaunchEffectComposable: Stared")
        try {
            for (i in 1..10) {
                counter.intValue++
                delay(1000)
            }
        }catch (e:Exception){
            Log.d("Launched Effect", "LaunchEffectComposable: Exception ${e.message} ")
        }
    }
    
    var text = "Counter is running ${counter.value}"
    if(counter.value==10){
        text = "Counter Stopped"
    }

    Text(text = text)

}

@Preview
@Composable
fun RememberCoroutineExample() {

    val counter = rememberSaveable { mutableIntStateOf(0) }

    // it gave us the scope to launch coroutine..//
    // no effect of recomposition.//
    // all side-effects runs in controlled environment.//
    var scope = rememberCoroutineScope()



    var text = "Counter is running ${counter.value}"
    if(counter.value==10){
        text = "Counter Stopped"
    }

    Column {
        Text(text = text)
        Button(onClick = {
            scope.launch {
                Log.d(SideEffectExample::class.simpleName, "RememberCoroutineExample: ")
                try {
                    for (i in 1..10) {
                        counter.intValue++
                        delay(1000)
                    }
                }catch (e:Exception){
                    Log.d("Launched Effect", "LaunchEffectComposable: Exception ${e.message} ")
                }
            }
        }) {
            Text(text = "Start Counter...")
        }
    }

    
}

@Preview
@Composable
fun RememberUpdatedState() {
    var counter = rememberSaveable {
        mutableIntStateOf(0)
    }

    LaunchedEffect(key1 = Unit){
        delay(2000)
        counter.value = 10
    }

    Counter(counter.value)

}

@Composable
fun Counter(value: Int) {
    val state = rememberUpdatedState(newValue = value)
    LaunchedEffect(key1 = Unit){
        delay(5000)
        Log.d(SideEffectExample::class.simpleName, "Counter: value is ${state.value}")
    }
    Text(text = value.toString())
}

fun a(){
    Log.d(SideEffectExample::class.simpleName, "a: I am function a")
}

fun b(){
    Log.d(SideEffectExample::class.simpleName, "b: I am function b")
}

@Preview
@Composable
fun RememberUpdatedStateExample() {
    var state = rememberSaveable{
        mutableStateOf(::a)
    }
    Button(onClick = { state.value = ::b }) {
        Text(text = "Click to change State")
    }
    LandingScreen(onTimeout = { state.value})
}


@Preview
@Composable
fun DisposableEffectExample() {
    var state = rememberSaveable {
        mutableStateOf(false)
    }

    DisposableEffect(key1 = state.value) {
        Log.d(SideEffectExample::class.simpleName, "DisposableEffectExample: Dispose Effect Started...")
        onDispose {
            // clean up code will go here.///
            Log.d(SideEffectExample::class.simpleName, "DisposableEffectExample: Clean Up")
        }
    }
    
    Button(onClick = { state.value = !state.value }) {
        Text(text = "Change State..")
        
    }
}

@Composable
fun MediaComposable() {
    val context = LocalContext.current
    DisposableEffect(key1 = Unit){
        val mediaPlayer = MediaPlayer.create(context, com.google.maps.android.v3.ktx.R.raw.ic_mic)
        mediaPlayer.start()

        onDispose {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }
}

class SideEffectExample {

}
