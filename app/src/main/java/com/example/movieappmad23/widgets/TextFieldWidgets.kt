package com.example.movieappmad23.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SimpleTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    errMsg: String = "",
    isError: Boolean,
    singleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    onDone: () -> Unit = {},
    onChange: (String) -> Unit
){
    OutlinedTextField(
        value = value,
        singleLine = singleLine,
        modifier = modifier.fillMaxWidth(),
        onValueChange = {
            onChange(it)
        },
        label = { Text(text = label) },
        isError = isError,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = keyboardType
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onDone()
            }
        ),

    )
    if (isError){
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = errMsg,
            fontSize = 14.sp,
            color = MaterialTheme.colors.error
        )
    }
}