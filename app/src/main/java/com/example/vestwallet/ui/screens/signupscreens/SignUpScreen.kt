package com.example.vestwallet.ui.screens.signupscreens

import android.app.Application
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.NavigateBefore
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vestwallet.R
import com.example.vestwallet.models.AuthState
import com.example.vestwallet.models.UserDetails
import com.example.vestwallet.ui.viewmodel.AuthViewModel


@Composable
fun SignUpScreen(
    viewModel: AuthViewModel = viewModel(),
    selectCountryOnClick: () -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
//    val windowInsets = WindowInsets.systemBars
//    val density = LocalDensity.current
//    val statusBarHeight = with(density) { windowInsets.getTop(density).toDp() }
    Scaffold(
        topBar = {
            SignUpTopAppBar(
                onClick = onNavigateBack,
                modifier = Modifier.statusBarsPadding()
            )
        },
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) { innerPadding ->
        SignUpMergeScreen(
            viewModel = viewModel,
            userDetails = UserDetails(),
            selectCountryOnClick = selectCountryOnClick,
            modifier = Modifier.padding(innerPadding)
        )
    }
}


@Composable
fun SignUpMergeScreen(
    viewModel: AuthViewModel,
    userDetails: UserDetails,
    selectCountryOnClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var firstName by remember { mutableStateOf("") }
    var middleName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }

    var dateOfBirth by remember { mutableStateOf("") }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val viewAuthState by viewModel.authState.collectAsState()

    LaunchedEffect(viewAuthState) {
        when (viewAuthState) {
            is AuthState.Authenticated -> selectCountryOnClick()
            is AuthState.Error -> {}
            else -> {}
        }
    }

    Column(
        modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            SignUpForm(
                firstName = firstName,
                onFirstNameChange = { firstName = it },
                middleName = middleName,
                onMiddleNameChange = { middleName = it },
                lastName = lastName,
                onLastNameChange = { lastName = it },
                dateOfBirth = dateOfBirth,
                onDateOfBirthChange = { dateOfBirth = it },
                email = email,
                onEmailChange = { email = it },
                password = password,
                onPasswordChange = { password = it },
                confirmPassword = confirmPassword,
                onConfirmPasswordChange = { confirmPassword = it },
            )
        }
        SignUpContinueButton(
            selectCountryOnClick = {
                if (password == confirmPassword) {
                    viewModel.updateSignUpPage(
                        firstName, middleName, lastName, email, dateOfBirth, password
                    )
                    viewModel.updateAuthState(AuthState.Authenticated(email))
                }
            }
        )
    }
}

@Composable
fun SignUpContinueButton(
    selectCountryOnClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = selectCountryOnClick,
        modifier = modifier
            .height(42.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(50.dp)
    ) {
        Text(
            text = stringResource(R.string.sign_up_continue_button),
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xFFFFFFFF)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpTopAppBar(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    //val backStackEntry by navController.currentBackStackEntryAsState()
    //val currentScreen = backStackEntry?.destination?.route ?: AuthRoutes.SignUpScreen.route
    TopAppBar(
        title = { /*TODO*/ },
        navigationIcon = {
            IconButton(
                onClick = onClick
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.NavigateBefore,
                    contentDescription = stringResource(R.string.navigate_back),
                    tint = MaterialTheme.colorScheme.secondary,
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = modifier
    )
}

@Composable
fun SignUpForm(
    firstName: String,
    onFirstNameChange: (String) -> Unit,
    middleName: String,
    onMiddleNameChange: (String) -> Unit,
    lastName: String,
    onLastNameChange: (String) -> Unit,
    dateOfBirth: String,
    onDateOfBirthChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    confirmPassword: String,
    onConfirmPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
    ) {
        Text(
            text = stringResource(R.string.sign_up_title),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.sign_up_subtitle),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.secondary
        )
        // Text fields
        Spacer(modifier = Modifier.height(40.dp))
        PersonalForm(
            firstName= firstName,
            onFirstNameChange = onFirstNameChange,
            middleName= middleName,
            onMiddleNameChange = onMiddleNameChange,
            lastName= lastName,
            onLastNameChange = onLastNameChange
        )
        // Date of birth
        Spacer(modifier = Modifier.height(20.dp))
        DateOfBirthForm(
            dateOfBirth = dateOfBirth,
            onDateOfBirthChange = onDateOfBirthChange
        )
        //Details
        Spacer(modifier = Modifier.height(20.dp))
        DetailsForm(
            email = email,
            onEmailChange = onEmailChange,
            password = password,
            onPasswordChange = onPasswordChange,
            confirmPassword = confirmPassword,
            onConfirmPasswordChange = onConfirmPasswordChange
        )
    }
}

@Composable
fun PersonalForm(
    modifier: Modifier = Modifier,
    firstName: String,
    onFirstNameChange: (String) -> Unit,
    middleName: String,
    onMiddleNameChange: (String) -> Unit,
    lastName: String,
    onLastNameChange: (String) -> Unit
) {
    Column(
        modifier
    ) {
        Text(
            text = stringResource(R.string.sign_up_personal),
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(15.dp))
        OutlinedTextField(
            textStyle = MaterialTheme.typography.bodyMedium,
            placeholder = {
                Text(
                    text= stringResource(R.string.sign_up_personal_first_name),
                    color = Color.Gray
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                focusedBorderColor = MaterialTheme.colorScheme.primary
            ),
            singleLine = true,
            value = firstName,
            onValueChange = onFirstNameChange,
            modifier = modifier
                .fillMaxWidth()
                .height(52.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            textStyle = MaterialTheme.typography.bodyMedium,
            placeholder = {
                Text(
                    text= stringResource(R.string.sign_up_personal_middle_name),
                    color = Color.Gray
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                focusedBorderColor = MaterialTheme.colorScheme.primary
            ),
            singleLine = true,
            value = middleName,
            onValueChange = onMiddleNameChange,
            modifier = modifier
                .fillMaxWidth()
                .height(52.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            textStyle = MaterialTheme.typography.bodyMedium,
            placeholder = {
                Text(
                    text= stringResource(R.string.sign_up_personal_last_name),
                    color = Color.Gray
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                focusedBorderColor = MaterialTheme.colorScheme.primary
            ),
            singleLine = true,
            value = lastName,
            onValueChange = onLastNameChange,
            modifier = modifier
                .fillMaxWidth()
                .height(52.dp)
        )
    }
}

@Composable
fun DateOfBirthForm(
    dateOfBirth: String,
    onDateOfBirthChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
    ) {
        Text(
            text = stringResource(R.string.sign_up_Date_of_birth),
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(15.dp))
        OutlinedTextField(
            textStyle = MaterialTheme.typography.bodyMedium,
            placeholder = {
                Text(
                    text= stringResource(R.string.sign_up_Date_of_birth_placeholder),
                    color = Color.Gray
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                focusedBorderColor = MaterialTheme.colorScheme.primary
            ),
            singleLine = true,
            value = dateOfBirth,
            onValueChange = onDateOfBirthChange,
            modifier = modifier
                .fillMaxWidth()
                .height(52.dp)
        )
    }
}

@Composable
fun DetailsForm(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    confirmPassword: String,
    onConfirmPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
    ) {
        Text(
            text = stringResource(R.string.sign_up_details),
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(15.dp))
        OutlinedTextField(
            textStyle = MaterialTheme.typography.bodyMedium,
            placeholder = {
                Text(
                    text= stringResource(R.string.sign_up_details_email),
                    color = Color.Gray
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                focusedBorderColor = MaterialTheme.colorScheme.primary
            ),
            singleLine = true,
            value = email,
            onValueChange = onEmailChange,
            modifier = modifier
                .fillMaxWidth()
                .height(52.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            textStyle = MaterialTheme.typography.bodyMedium,
            placeholder = {
                Text(
                    text= stringResource(R.string.sign_up_details_password),
                    color = Color.Gray
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                focusedBorderColor = MaterialTheme.colorScheme.primary
            ),
            singleLine = true,
            value = password,
            onValueChange = onPasswordChange,
            modifier = modifier
                .fillMaxWidth()
                .height(52.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            textStyle = MaterialTheme.typography.bodyMedium,
            placeholder = {
                Text(
                    text= stringResource(R.string.sign_up_details_confirm_password),
                    color = Color.Gray
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                focusedBorderColor = MaterialTheme.colorScheme.primary
            ),
            singleLine = true,
            value = confirmPassword,
            onValueChange = onConfirmPasswordChange,
            modifier = modifier
                .fillMaxWidth()
                .height(52.dp)
        )
    }
}