package com.aherrera.fakelogin.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aherrera.fakelogin.R
import com.aherrera.fakelogin.ui.components.atoms.ButtonFilled
import com.aherrera.fakelogin.ui.theme.BaubapLightBackground

@Composable
internal fun NativeAlertDialog(
    @StringRes message: Int,
    onDismissRequest: () -> Unit,
    confirmButtonAction: () -> Unit,
) {
    AlertDialog(
        containerColor = BaubapLightBackground,
        onDismissRequest = { onDismissRequest() },
        text = {
            Text(
                text = stringResource(id = message),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                textAlign = TextAlign.Justify
            )
        },
        confirmButton = {
            ButtonFilled(
                onClick = { confirmButtonAction() },
                text = R.string.accept_button
            )
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
@Preview
private fun NativeAlertDialogPreview() {
    NativeAlertDialog(
        onDismissRequest = {},
        message = R.string.login_error_curp,
        confirmButtonAction = {}
    )
}