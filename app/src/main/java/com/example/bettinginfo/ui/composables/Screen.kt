package com.example.bettinginfo.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun <T> Screen(
    errorMessage: Int?,
    contentPadding: PaddingValues,
    items: List<T>,
    itemComposable: @Composable (T) -> Unit
) {
    errorMessage?.let{
        Box(
            Modifier.padding(contentPadding).fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(stringResource(it))
        }
    } ?:
    LazyColumn(contentPadding = contentPadding) {
        items(items){
            itemComposable(it)
        }
    }
}