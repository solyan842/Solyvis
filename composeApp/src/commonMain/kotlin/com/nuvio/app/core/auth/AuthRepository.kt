package com.nuvio.app.core.auth

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Stable local installation identity used by repositories that still key data by user ID.
 *
 * Solyvis v0.1.5 keeps a stable installation identity so existing profile/settings
 * persistence can continue to use the same repository contracts, but it no longer
 * starts a Supabase auth session, observes remote session state, or exposes an
 * account lifecycle at runtime.
 */
object AuthRepository {
    private val _state = MutableStateFlow<AuthState>(AuthState.Loading)
    val state: StateFlow<AuthState> = _state.asStateFlow()

    private var initialized = false

    fun initialize() {
        if (initialized) return
        initialized = true
        establishLocalSession()
    }

    @OptIn(ExperimentalUuidApi::class)
    private fun establishLocalSession() {
        val userId = AuthStorage.loadAnonymousUserId()
            ?.takeIf { it.isNotBlank() }
            ?: Uuid.random().toString().also(AuthStorage::saveAnonymousUserId)

        _state.value = AuthState.Authenticated(
            userId = userId,
            email = null,
            isAnonymous = true,
        )
    }

    fun reinitialize() {
        initialized = false
        _state.value = AuthState.Loading
        initialize()
    }

    suspend fun signOutIfSessionInvalid(error: Throwable, source: String): Boolean = false

}
