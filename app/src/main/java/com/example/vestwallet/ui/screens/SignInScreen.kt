package com.example.vestwallet.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vestwallet.R
import com.example.vestwallet.models.AuthState
import com.example.vestwallet.ui.viewmodel.AuthViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(toMainPage: () -> Unit, toSignUpPage: () -> Unit, modifier: Modifier = Modifier) {
    val windowInsets = WindowInsets.systemBars
    val density = LocalDensity.current
    val statusBarHeight = with(density) { windowInsets.getTop(density).toDp() }
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier
            .padding(top = (statusBarHeight))
    ) {
        SignInMergeScreen(
            toMainPage = toMainPage,
            toSignUpPage = toSignUpPage
        )
    }
}

@Composable
fun SignInMergeScreen(
    toMainPage: () -> Unit,
    toSignUpPage: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        modifier
            .padding(16.dp)
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Column {
            SignInForm(
                viewModel = viewModel(),
                toMainPage = toMainPage,
                toSignUpPage = toSignUpPage
            )
        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SignInTopAppBar(navController: NavHostController = rememberNavController(), modifier: Modifier = Modifier) {
//    val backStackEntry by navController.currentBackStackEntryAsState()
//    TopAppBar(
//        title = { /*TODO*/ },
//        navigationIcon = {
//            IconButton(
//                onClick = {
//                    navController.navigateUp()
//                }
//            ) {
//                Icon(
//                    imageVector = Icons.AutoMirrored.Filled.NavigateBefore,
//                    contentDescription = stringResource(R.string.navigate_back),
//                    tint = MaterialTheme.colorScheme.secondary,
//                )
//            }
//        },
//        colors = TopAppBarDefaults.mediumTopAppBarColors(
//            containerColor = MaterialTheme.colorScheme.background
//        ),
//    )
//}

@Composable
// I know the code here is bad practice, will refactor later
fun SignInForm(
    viewModel: AuthViewModel,
    toMainPage: () -> Unit,
    toSignUpPage: () -> Unit,
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val viewAuthState by viewModel.authState.collectAsState()

    LaunchedEffect(viewAuthState) {
        when (viewAuthState) {
            is AuthState.Authenticated -> toMainPage()
            is AuthState.Error -> {}
            else -> {}
        }
    }

    Column(
        modifier.background(color= MaterialTheme.colorScheme.background)
    ) {
        Text(
            text = stringResource(R.string.sign_in_title),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = R.string.sign_in_subtitle),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = stringResource(R.string.sign_in_email),
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            textStyle = MaterialTheme.typography.bodyMedium,
            placeholder = {
                Text(
                    text= stringResource(R.string.sign_in_email),
                    color = Color.Gray
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                focusedBorderColor = MaterialTheme.colorScheme.primary
            ),
            singleLine = true,
            value = email,
            onValueChange = { email = it },
            modifier = modifier
                .fillMaxWidth()
                .height(52.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.sign_in_password),
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            textStyle = MaterialTheme.typography.bodyMedium,
            placeholder = {
                Text(
                    text= stringResource(R.string.sign_in_password),
                    color = Color.Gray
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                focusedBorderColor = MaterialTheme.colorScheme.primary
            ),
            singleLine = true,
            value = password,
            onValueChange = { password = it },
            modifier = modifier
                .fillMaxWidth()
                .height(52.dp)
        )
        Spacer(modifier = modifier.height(5.dp))
        //Note don't forget to fix padding issues on forgot password
        ForgotPassword()
        Spacer(modifier = modifier.height(15.dp))
        Button(
            onClick = {
                viewModel.SignIn(email, password)
            },
            modifier = Modifier
                .height(42.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(50.dp)
        ) {
            Text(
                text = stringResource(R.string.sign_in_text),
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFFFFFFFF)
            )
        }
        Spacer(modifier = modifier.height(10.dp))
        Row (
            modifier.align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.sign_in_sign_up_text),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            TextButton(
                onClick = toSignUpPage
            ) {
                Text(
                    text = stringResource(R.string.sign_in_sign_up),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Composable
fun ForgotPassword(modifier: Modifier = Modifier) {
    Row (
        modifier
            .wrapContentWidth(Alignment.End)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ){
        TextButton(
            onClick = { /*TODO*/ },
            contentPadding = PaddingValues(end = 0.dp),
            modifier = modifier
                .wrapContentWidth(Alignment.End)
        ) {
            Text(
                text = stringResource(R.string.forgot_password),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.End,
            )
        }
    }
}

//@Composable
//fun SignUpNavigation(modifier: Modifier = Modifier) {}