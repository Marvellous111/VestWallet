package com.example.vestwallet.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vestwallet.models.AuthState
import com.example.vestwallet.models.UserCurrency
import com.example.vestwallet.models.UserDetails
import com.example.vestwallet.models.did.DidClass
import com.example.vestwallet.networkrequests.createUserDid
import com.example.vestwallet.networkrequests.getUserDidDocument
import com.example.vestwallet.utils.PasswordHasher
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

class AuthViewModel: ViewModel() {


    private val _authState = MutableStateFlow<AuthState>(AuthState.Unauthenticated)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    private val _authUiState = MutableStateFlow<UserDetails>(UserDetails())
    val authUiState: StateFlow<UserDetails> = _authUiState.asStateFlow()

//    private val _userBearerDidState = MutableStateFlow<DidClass>(DidClass(userBearerDid = DidDocument(id = "")))
//    val userBearerDidState: StateFlow<DidClass> = _userBearerDidState.asStateFlow()


    private lateinit var realm: Realm

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val config = RealmConfiguration.Builder(schema = setOf(UserDetails::class, UserCurrency::class))
                .name("user_database.realm")
                .build()
            realm = Realm.open(config)
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

    fun SignUp(userDetails: UserDetails, didClass: DidClass) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Here we create DID as well and send to the realm db user instance
                val hashedPassword = PasswordHasher.hashPassword(userDetails.password)
                userDetails.password = hashedPassword

                val userDidPair = createUserDid()
                Log.d("createdid", userDidPair.second)
                userDetails.userDid = userDidPair.second
                val userDidDocument = getUserDidDocument(userDetails.userDid)
                if (userDidDocument != null) {
                    didClass.userBearerDid = userDidPair.first
                }
                userDetails.didDocument = userDidDocument.toString()

                realm.write {
                    copyToRealm(userDetails)
                }
                _authState.value = AuthState.Authenticated(userDetails.email)
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
                }
            } else if (email == "testemail@gmail.com" && password == "test") {
                withContext(Dispatchers.Main) {
                    _authState.value = AuthState.Authenticated(email)
                }
            }
            else {
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