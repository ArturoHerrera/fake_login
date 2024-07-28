package com.aherrera.fakelogin.ui.screen.signUp

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aherrera.fakelogin.R
import com.aherrera.fakelogin.ui.components.FormTopBar
import com.aherrera.fakelogin.ui.components.NativeAlertDialog
import com.aherrera.fakelogin.ui.components.atoms.ButtonFilled
import com.aherrera.fakelogin.ui.components.atoms.FormTextField
import com.aherrera.fakelogin.ui.theme.BaubapLightBackground
import com.aherrera.fakelogin.ui.theme.BaubapPrimaryPurlple

@Composable
fun SignUpScreen(
    goToWelcome: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    BackHandler(true) { goToWelcome() }

    SignUpContent(
        goToWelcome = goToWelcome,
        enableLoginButton = uiState.enableLoginButton,
        onWriteUser = { newUser ->
            viewModel.setUser(newUser)
            viewModel.checkInputsAndEnableButton()
        },
        onWritePass = { newPass ->
            viewModel.setPass(newPass)
            viewModel.checkInputsAndEnableButton()
        },
        onClickLoginButton = {
            viewModel.showLoginAlert()
        },
    )

    if (uiState.alertIsVisible) {
        NativeAlertDialog(
            onDismissRequest = { viewModel.hideLoginAlert() },
            message = R.string.sign_up_error_curp,
            confirmButtonAction = { viewModel.hideLoginAlert() }
        )
    }
}

@Composable
private fun SignUpContent(
    goToWelcome: () -> Unit = {},
    enableLoginButton: Boolean,
    onClickLoginButton: () -> Unit,
    onWriteUser: (String) -> Unit,
    onWritePass: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            FormTopBar(onClick = { goToWelcome() })
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(BaubapLightBackground)
                    .then(Modifier.padding(bottom = 24.dp, top = 64.dp, start = 8.dp, end = 8.dp))
            ) {
                Text(
                    text = stringResource(id = R.string.sign_up_tittle),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 40.dp),
                    textAlign = TextAlign.Start
                )

                var phoneText by remember { mutableStateOf("") }
                FormTextField(
                    textFieldValue = phoneText,
                    label = R.string.sign_up_text_field_phone
                ) { newText ->
                    phoneText = newText
                    onWriteUser(phoneText)
                }

                Spacer(modifier = Modifier.height(24.dp))

                var curpText by remember { mutableStateOf("") }
                FormTextField(
                    textFieldValue = curpText,
                    label = R.string.sign_up_text_field_curp
                ) { newText ->
                    curpText = newText
                    onWritePass(curpText)
                }

                Row(
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = stringResource(id = R.string.sign_up_not_remember_curp),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = stringResource(id = R.string.sign_up_not_check_here),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Start,
                        color = BaubapPrimaryPurlple,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    ButtonFilled(
                        text = R.string.sign_up_create_account_button,
                        enableButton = enableLoginButton,
                        onClick = { onClickLoginButton() }
                    )

                    Row(
                        modifier = Modifier
                            .padding(top = 16.dp, bottom = 8.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.sign_up_already_account),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = stringResource(id = R.string.sign_up_create_account_button),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Start,
                            color = BaubapPrimaryPurlple,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }
    )
}

@Composable
@Preview
private fun SignUpContentPreview() {
    SignUpContent(
        goToWelcome = {},
        enableLoginButton = true,
        onWritePass = { t -> },
        onWriteUser = { t -> },
        onClickLoginButton = {}
    )
}