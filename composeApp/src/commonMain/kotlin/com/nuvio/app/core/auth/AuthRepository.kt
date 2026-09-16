package com.nuvio.app.core.auth

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

private const val LOCAL_ONLY_ACCOUNT_MESSAGE = "Account services are disabled in Solyvis local-only mode"

/**
 * Local-only authentication facade.
 *
 * Solyvis v0.1.5 keeps a stable installation identity so existing profile/settings
 * persistence can continue to use the same repository contracts, but it no longer
 * starts a Supabase auth session, observes remote session state, or exposes an
 * account lifecycle at runtime.
 */
object AuthRepository {
    private val _state = MutableStateFlow<AuthState>(AuthState.Loading)
    val state: StateFlow<AuthState> = _state.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

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

        _error.value = null
        _state.value = AuthState.Authenticated(
            userId = userId,
            email = null,
            isAnonymous = true,
        )
    }

    /** Compatibility entry point retained for callers that already request guest mode. */
    fun signInAnonymously() {
        establishLocalSession()
    }

    suspend fun signUpWithEmail(email: String, password: String): Result<Unit> =
        accountServicesDisabled()

    suspend fun signInWithEmail(email: String, password: String): Result<Unit> =
        accountServicesDisabled()

    /**
     * There is no remote account to sign out from in local-only mode. Keeping the
     * local installation identity avoids accidentally sending the app back to the
     * legacy login gate or orphaning locally persisted profile data.
     */
    suspend fun signOut(): Result<Unit> {
        establishLocalSession()
        return Result.success(Unit)
    }

    suspend fun prepareForServerSwitch(): Result<Unit> = accountServicesDisabled()

    fun reinitialize() {
        initialized = false
        _state.value = AuthState.Loading
        initialize()
    }

    suspend fun signOutIfSessionInvalid(error: Throwable, source: String): Boolean = false

    suspend fun deleteAccount(): Result<Unit> = accountServicesDisabled()

    fun clearError() {
        _error.value = null
    }

    private fun accountServicesDisabled(): Result<Unit> {
        _error.value = LOCAL_ONLY_ACCOUNT_MESSAGE
        return Result.failure(IllegalStateException(LOCAL_ONLY_ACCOUNT_MESSAGE))
    }
}
