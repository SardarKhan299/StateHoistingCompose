package androidx.compose.samples.crane.QuotesApp

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.google.gson.Gson
import java.nio.Buffer
import java.nio.charset.Charset

object DataManager{

    var data = emptyArray<Quote>()
    var isDataLoaded = mutableStateOf(false)
    fun loadAssetsFromFile(context: Context){
        val inputStream = context.assets.open("quotes.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        data = gson.fromJson(json,Array<Quote>::class.java)
        isDataLoaded.value = true

    }

}