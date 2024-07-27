package com.aherrera.fakelogin.ui.components.atoms

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aherrera.fakelogin.R
import com.aherrera.fakelogin.ui.theme.BaubapPrimaryPurlple
import com.aherrera.fakelogin.ui.theme.BaubapTextFieldBackground

@Composable
fun FormTextField(
    textFieldValue: TextFieldValue,
    @StringRes label: Int = R.string.sign_up_text_field_phone,
    onNewText: (TextFieldValue) -> Unit
) {
    TextField(
        shape = RoundedCornerShape(8.dp),
        label = {
            Text(text = stringResource(id = label))
        },
        value = textFieldValue,
        onValueChange = { newText ->
            onNewText(newText)
        },
        colors = TextFieldDefaults.colors().copy(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = BaubapPrimaryPurlple,
            unfocusedLabelColor = Color.Black,
            focusedLabelColor = BaubapPrimaryPurlple,
            focusedContainerColor = BaubapTextFieldBackground,
            unfocusedContainerColor = BaubapTextFieldBackground,
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
@Preview
private fun FormTextFieldPreview() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    FormTextField(
        text,
        label = R.string.sign_up_text_field_curp
    ) { newText ->
        text = newText
    }
}