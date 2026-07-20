package com.sergey.weatherforecast.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    searchText: String,
    onTextChange: (String) -> Unit,
    onSearch: () -> Unit
) {

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = searchText,
        onValueChange = onTextChange,
        label = {
            Text("City")
        }
    )

    Spacer(modifier = Modifier.height(16.dp))

    Button(
        onClick = onSearch
    ) {
        Text("Search")
    }
}