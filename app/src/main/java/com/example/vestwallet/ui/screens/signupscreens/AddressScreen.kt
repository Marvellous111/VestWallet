package com.example.vestwallet.ui.screens.signupscreens

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vestwallet.R
import com.example.vestwallet.models.AuthState
import com.example.vestwallet.models.UserDetails
import com.example.vestwallet.models.did.DidClass
import com.example.vestwallet.networkrequests.createUserDid
import com.example.vestwallet.ui.viewmodel.AuthViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import web5.sdk.dids.did.BearerDid

@Composable
fun AddressScreen(onNavigateBack: () -> Unit, mainPageOnClick: () -> Unit, modifier: Modifier = Modifier) {
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
        AddressmergeScreen(
            viewModel = viewModel(),
            userDetails = UserDetails(),
            mainPageOnClick = mainPageOnClick,
            modifier = Modifier.padding(innerPadding)
        )
    }
}


@Composable
fun AddressmergeScreen(
    viewModel: AuthViewModel,
    userDetails: UserDetails,
    mainPageOnClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    var phoneNumber by remember { mutableStateOf("") }

    val viewAuthState by viewModel.authState.collectAsState()

    LaunchedEffect(viewAuthState) {
        when (viewAuthState) {
            is AuthState.Authenticated -> mainPageOnClick()
            is AuthState.Error -> {}
            else -> {}
        }
    }

    Column(
        modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(16.dp)
            .padding(top = 50.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        AddressForm(
            phoneNumber = phoneNumber,
            onPhoneNumberChange = { phoneNumber = it }
        )
        AddressContinueButton(
            mainPageOnClick = {
                userDetails.phoneNumber = phoneNumber
                userDetails.transactionPin = (1234).toString()

                viewModel.SignUp(
                    userDetails = userDetails,
                    didClass = DidClass()
                )
            }
        )
    }
}

@Composable
fun AddressContinueButton(
    mainPageOnClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = mainPageOnClick,
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

@Composable
fun AddressForm(
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Column {
            Text(
                text = stringResource(R.string.address_title),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(R.string.address_subtitle),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.secondary
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
        PhoneNumberForm(
            phoneNumber = phoneNumber,
            onPhoneNumberChange = onPhoneNumberChange
        )
//        Spacer(modifier = Modifier.height(20.dp))
//        HomeAddressForm()
    }
}

@Composable
fun PhoneNumberForm(
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.address_phone_number),
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            singleLine = true,
            placeholder = {
                Text(
                    text = stringResource(R.string.address_phone_number),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                focusedBorderColor = MaterialTheme.colorScheme.primary
            ),
            textStyle = MaterialTheme.typography.bodyLarge,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            value = phoneNumber,
            onValueChange = onPhoneNumberChange,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

// Home address is not needed as i deemed because it has little to no impact on our wallet
// However due to future regulations, home address can be put in our app easily

//@Composable
//fun HomeAddressForm(modifier: Modifier = Modifier) {
//    Column(
//        modifier.fillMaxWidth()
//    ) {
//        Text(
//            text = stringResource(R.string.address_home_address),
//            color = MaterialTheme.colorScheme.secondary
//        )
//        Spacer(modifier = Modifier.height(5.dp))
//        OutlinedTextField(
//            placeholder = {
//                Text(
//                    text = stringResource(R.string.address_city_placeholder),
//                    color = Color.Gray
//                )
//            },
//            colors = OutlinedTextFieldDefaults.colors(
//                unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
//                focusedBorderColor = MaterialTheme.colorScheme.primary
//            ),
//            textStyle = MaterialTheme.typography.bodyLarge,
//            value = "",
//            onValueChange = {},
//            modifier = Modifier.fillMaxWidth()
//        )
//        Spacer(modifier = Modifier.height(10.dp))
//        OutlinedTextField(
//            placeholder = {
//                Text(
//                    text = stringResource(R.string.address_street_placeholder),
//                    color = Color.Gray
//                )
//            },
//            colors = OutlinedTextFieldDefaults.colors(
//                unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
//                focusedBorderColor = MaterialTheme.colorScheme.primary
//            ),
//            textStyle = MaterialTheme.typography.bodyLarge,
//            value = "",
//            onValueChange = {},
//            modifier = Modifier.fillMaxWidth()
//        )
//    }
//}