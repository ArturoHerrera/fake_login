package com.aherrera.fakelogin.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.aherrera.fakelogin.R
import com.aherrera.fakelogin.domain.model.UserDetails
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
                modifier = Modifier.fillMaxWidth(),
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

@Composable
fun LoginSuccessAlert(
    userDetails: UserDetails,
    onDismissRequest: () -> Unit,
    confirmButtonAction: () -> Unit,
) {
    AlertDialog(
        containerColor = BaubapLightBackground,
        onDismissRequest = { onDismissRequest() },
        title = {
            Text(
                text = "${stringResource(id = R.string.login_alert_tittle)} \uD83D\uDE04",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        text = {
            Column {
                Text(
                    text = userDetails.fullName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(userDetails.avatarUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(300.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }
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
private fun LoginSuccessAlertPreview() {
    LoginSuccessAlert(
        userDetails = UserDetails(
            fullName = "Paco Flores",
            avatarUrl = "https://i.pravatar.cc/300"
        ),
        onDismissRequest = {},
        confirmButtonAction = {}
    )
}