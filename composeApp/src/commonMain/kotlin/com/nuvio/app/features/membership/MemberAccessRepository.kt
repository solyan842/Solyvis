package com.nuvio.app.features.membership

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Membership is intentionally offline in the Solyvis local-only build.
 *
 * Keep the repository API so the existing UI/theme code remains source-compatible,
 * but never hydrate account entitlements or contact the membership backend.
 */
object MemberAccessRepository {
    private val _access = MutableStateFlow(MemberAccess.None)
    val access: StateFlow<MemberAccess> = _access.asStateFlow()

    fun ensureStarted() {
        _access.value = MemberAccess.None
    }

    fun refresh() {
        _access.value = MemberAccess.None
    }

    fun refreshIfStale() {
        _access.value = MemberAccess.None
    }

    fun clearLocalState() {
        _access.value = MemberAccess.None
        MemberAssetStorage.clearAccess()
        ProfileBackgroundRepository.invalidate()
    }
}
