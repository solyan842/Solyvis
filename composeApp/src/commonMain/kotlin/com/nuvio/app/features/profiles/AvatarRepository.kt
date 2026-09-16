package com.nuvio.app.features.profiles

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Serializable
private data class StoredAvatarCatalogPayload(
    val items: List<AvatarCatalogItem> = emptyList(),
)

internal fun availableAvatarCatalog(
    standardCatalog: List<AvatarCatalogItem>,
    memberCatalog: List<AvatarCatalogItem>,
    hasMemberAccess: Boolean,
): List<AvatarCatalogItem> = standardCatalog + if (hasMemberAccess) memberCatalog else emptyList()

/**
 * Local-only avatar catalog.
 *
 * Existing cached standard avatars remain available after upgrading, but this
 * repository no longer calls Supabase, membership RPCs, or authenticated storage.
 * New local profiles can always use initials/colors or a user-supplied avatar URL.
 */
object AvatarRepository {
    private val json = Json { ignoreUnknownKeys = true; encodeDefaults = true }
    private val _avatars = MutableStateFlow<List<AvatarCatalogItem>>(emptyList())
    val avatars: StateFlow<List<AvatarCatalogItem>> = _avatars.asStateFlow()

    private var cacheHydrated = false

    suspend fun fetchAvatars() {
        hydrateFromCacheIfNeeded()
    }

    suspend fun refreshAvatars(force: Boolean = false) {
        hydrateFromCacheIfNeeded()
    }

    private fun hydrateFromCacheIfNeeded() {
        if (cacheHydrated) return
        cacheHydrated = true

        val payload = AvatarStorage.loadPayload().orEmpty().trim()
        if (payload.isEmpty()) return

        val stored = runCatching {
            json.decodeFromString<StoredAvatarCatalogPayload>(payload)
        }.getOrNull() ?: return

        _avatars.value = stored.items
            .filter { it.isActive }
            .sortedWith(compareBy({ it.category }, { it.sortOrder }))
    }
}
