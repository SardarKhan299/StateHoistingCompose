package androidx.compose.samples.crane.sideEffects

import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import android.view.ViewTreeObserver
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.samples.crane.R
import androidx.compose.samples.crane.home.LandingScreen
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

    KeyBoardComposable()
    TextField(value = "", onValueChange = {})

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
        val mediaPlayer = MediaPlayer.create(context, Uri.EMPTY)
        mediaPlayer.start()

        onDispose {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }
}


@Composable
fun KeyBoardComposable() {
    val view = LocalView.current
    DisposableEffect(key1 = Unit){
        Log.d(SideEffectExample::class.simpleName, "KeyBoardComposable: Started")
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val insects = ViewCompat.getRootWindowInsets(view)
            val isKeyboardVisible = insects?.isVisible(WindowInsetsCompat.Type.ime())
            Log.d(SideEffectExample::class.simpleName, "KeyBoardComposable: ${isKeyboardVisible.toString()}")
        }
        view.viewTreeObserver.addOnGlobalLayoutListener { listener }

        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener { listener }
        }
    }
}

@Composable
fun ProduceStateExample() {
    Log.d(SideEffectExample::class.simpleName, "ProduceStateExample: ")
    val state = produceState(initialValue = 0){
        for(i in 1..10){
            delay(1000)
            value +=1
        }
    }

    Text(text = state.value.toString(),
        style = MaterialTheme.typography.h2)

}

@Preview
@Composable
fun LoaderComposable() {

    val degree = produceState(initialValue = 0){
        while (true){
            delay(50)
            value = (value+30) % 360
        }
    }

    Box (contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize(1f)){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(imageVector = Icons.Default.Refresh,
                contentDescription = "Refresh Icon",
                modifier = Modifier.size(60.dp).rotate(degree.value.toFloat()))
            Text(text = "Loading...")

        }
    }
}

class SideEffectExample {

}
