package com.example.vestwallet.ui.viewmodel

import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.realm.kotlin.migration.AutomaticSchemaMigration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vestwallet.models.AuthState
import com.example.vestwallet.models.UserCurrency
import com.example.vestwallet.models.UserDetails
import com.example.vestwallet.models.did.DidClass
import com.example.vestwallet.networkrequests.createUserDid
import com.example.vestwallet.networkrequests.getUserDidDocument
import com.example.vestwallet.utils.PasswordHasher
import com.example.vestwallet.utils.SharedPreferencesHelper
import io.ktor.server.application.Application
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.mongodb.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import web5.sdk.dids.didcore.DidDocument

class AuthViewModel() : ViewModel() {

    var userEmail = MutableLiveData<String>()


    private val _authState = MutableStateFlow<AuthState>(AuthState.Unauthenticated)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    private val _authUiState = MutableStateFlow<UserDetails>(UserDetails())
    val authUiState: StateFlow<UserDetails> = _authUiState.asStateFlow()
//    private val _userBearerDidState = MutableStateFlow<DidClass>(DidClass(userBearerDid = DidDocument(id = "")))
//    val userBearerDidState: StateFlow<DidClass> = _userBearerDidState.asStateFlow()

    var userDetailsState: UserDetails = UserDetails()


    private lateinit var realm: Realm

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val config = RealmConfiguration.Builder(schema = setOf(UserDetails::class, UserCurrency::class))
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .name("user_database.realm")
                .build()
            realm = Realm.open(config)

            if (realm.query<UserDetails>().first().find() == null) {
                realm.write {
                    copyToRealm(UserDetails()) // Create the UserDetails object
                    copyToRealm(UserCurrency())
                }
            }
        }
    }

    fun updateSignUpPage(
        firstName: String,
        middleName:String,
        lastName: String,
        email: String,
        dateOfBirth: String,
        password: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            realm.write {
                val userDetails = query<UserDetails>().first().find() ?: UserDetails().also { copyToRealm(it) }
                userDetails.firstName = firstName
                userDetails.lastName = lastName
                userDetails.middleName = middleName
                userDetails.dateOfBirth = dateOfBirth
                userDetails.email = email
                userDetails.password = PasswordHasher.hashPassword(password)
            }
        }
    }

    fun updateCountryPage(
        countryName: String,
        countryCode: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            realm.write{
                val userDetails = query<UserDetails>().first().find() ?: UserDetails().also { copyToRealm(it) }
                userDetails.country = countryName
                userDetails.countryCode = countryCode
            }
        }
    }

    fun updateAddressPage(
        phoneNumber: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            realm.write{
                val userDetails = query<UserDetails>().first().find() ?: UserDetails().also { copyToRealm(it) }
                userDetails.phoneNumber = phoneNumber
            }
        }
    }

//    suspend fun checkAndGetUserDetails(realm: Realm): UserDetails? {
//        return realm.query<UserDetails>().first().find()
//    }

    fun updateAuthState(newState: AuthState) {
        viewModelScope.launch {
            _authState.emit(newState)
        }
    }

    fun SignUp() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Here we create DID as well and send to the realm db user instance
//                val hashedPassword = PasswordHasher.hashPassword(userDetails.password)
//                userDetails.password = hashedPassword

                val userDidPair = createUserDid()
                Log.d("createdid", userDidPair.second)
                var userDetails = UserDetails()
                realm.write {
                    userDetails = query<UserDetails>().first().find() ?: UserDetails().also { copyToRealm(it) }
                    userDetails.userDid = userDidPair.second
                    var bearerDidAsString: String? = ""
                    val userDidDocument = getUserDidDocument(userDetails.userDid)
                    if (userDidDocument != null) {
                        DidClass().userBearerDid = userDidPair.first
                        bearerDidAsString = DidClass().userBearerDid?.let {
                            DidClass().changetoString(
                                it
                            )
                        }
                    }
                    if (bearerDidAsString != null) {
                        userDetails.didDocument = bearerDidAsString
                    }
                }

                _authState.value = AuthState.Authenticated(userDetails.email)

                userEmail = MutableLiveData(userDetails.email)
            } catch (e: Exception) {
                _authState.value = AuthState.Error("Sign up failed: ${e.message}")
            }
        }
    }

    fun SignIn(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = realm.query<UserDetails>("email == $0", email)
                .first()
                .find()
            if (user != null && PasswordHasher.verifyHashPassword(password, user.password)) {
                withContext(Dispatchers.Main) {
                    _authState.value = AuthState.Authenticated(email)
                    userEmail = MutableLiveData(email)
                }
            } else {
                withContext(Dispatchers.Main) {
                    _authState.value = AuthState.Error("Invalid Credentials")
                }
            }
        }
    }

    fun SignOut() {
        _authState.value = AuthState.Unauthenticated
    }

    override fun onCleared() {
        super.onCleared()
        realm.close()
    }
}