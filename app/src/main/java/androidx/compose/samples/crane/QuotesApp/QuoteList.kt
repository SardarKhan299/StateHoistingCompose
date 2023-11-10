package androidx.compose.samples.crane.QuotesApp

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun QuoteList(data:Array<Quote>,onClick:(quote:Quote)->Unit) {
    LazyColumn(content = {
        items(data){
            QuotesListItem(quote = it,onClick)
        }
    })
}