package com.aherrera.fakelogin.ui.components.atoms

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aherrera.fakelogin.R
import com.aherrera.fakelogin.ui.theme.BaubapPrimaryPurlple

@Composable
fun ButtonFilled(
    modifier: Modifier = Modifier,
    buttonColor: Color = BaubapPrimaryPurlple,
    textColor: Color = Color.White,
    @StringRes text: Int = R.string.welcome_sign_up_button,
    enableButton: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = textColor
        ),
        onClick = { onClick() },
        content = {
            Text(text = stringResource(id = text))
        },
        shape = RoundedCornerShape(12.dp),
        enabled = enableButton,
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    )
}

@Preview
@Composable
private fun ButtonFilledPreview() {
    ButtonFilled(
        onClick = {},
        text = R.string.welcome_sign_up_button
    )
}

@Composable
fun ButtonText(
    modifier: Modifier = Modifier,
    textColor: Color = BaubapPrimaryPurlple,
    backgroundColor: Color = Color.White,
    @StringRes text: Int = R.string.welcome_sign_up_button,
    onClick: () -> Unit,
) {
    TextButton(
        colors = ButtonDefaults.textButtonColors(
            contentColor = textColor,
            containerColor = backgroundColor
        ),
        onClick = { onClick() },
        shape = RoundedCornerShape(12.dp),
        content = {
            Text(text = stringResource(id = text))
        },
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    )
}

@Preview
@Composable
private fun ButtonTextPreview() {
    ButtonText(
        onClick = {},
        text = R.string.welcome_sign_up_button
    )
}