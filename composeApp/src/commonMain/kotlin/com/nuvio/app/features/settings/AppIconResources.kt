package com.nuvio.app.features.settings

import com.nuvio.app.core.ui.AppTheme
import nuvio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

internal val AppIconOption.labelResource: StringResource
    get() = when (this) {
        AppIconOption.ORIGINAL -> Res.string.settings_appearance_app_icon_original
        AppIconOption.ARCTIC_BLUE -> Res.string.settings_appearance_app_icon_arctic_blue
        AppIconOption.EMERALD -> Res.string.settings_appearance_app_icon_emerald
        AppIconOption.ROSE_GOLD -> Res.string.settings_appearance_app_icon_rose_gold
        AppIconOption.COPPER -> Res.string.settings_appearance_app_icon_copper
        AppIconOption.GRAPHITE -> Res.string.settings_appearance_app_icon_graphite
    }

internal val AppIconOption.previewResource: DrawableResource
    get() = if (this == AppIconOption.GRAPHITE) {
        Res.drawable.app_icon_graphite
    } else {
        Res.drawable.app_icon_original
    }

internal val AppIconOption.wordmarkResource: DrawableResource
    get() = if (this == AppIconOption.GRAPHITE) {
        Res.drawable.app_logo_wordmark_graphite
    } else {
        Res.drawable.app_logo_wordmark_original
    }

internal fun AppTheme.wordmarkResource(fallback: AppIconOption): DrawableResource =
    if (this == AppTheme.GRAPHITE) {
        AppIconOption.GRAPHITE.wordmarkResource
    } else {
        fallback.wordmarkResource
    }
