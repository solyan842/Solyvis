# Changelog

## 0.1.8 — Dead backend cleanup

- Removed the obsolete generated `SupabaseConfig` and the unused `NUVIO_SUPABASE_URL`, `NUVIO_SUPABASE_ANON_KEY`, and `NUVIO_SUPABASE_FALLBACK_URL` build inputs.
- Removed unused Supabase Auth, PostgREST, Functions, and Storage dependencies from the shared runtime and version catalog.
- Renamed the remaining local build-properties holder so distribution settings no longer carry a misleading Supabase-specific name.
- Preserved local profiles, Tracking integrations, custom server switching, iOS 15 support, Solyvis branding, and GPL provenance.
- Bumped product version to 0.1.8 / build 9.

## 0.1.7 — Remove legacy account runtime

- Removed the Login/Create Account, Device Link, and Account Settings UI/source modules.
- Removed Account routes, saved-state registration, settings pages, navigation callbacks, and account search results.
- Simplified app startup to the stable local installation identity with no authentication screen state.
- Made profile load, create, edit, and delete paths local-only and removed their remote Supabase RPC branches.
- Kept cached verification for existing profile PINs, while removing unavailable remote PIN setup/reset code.
- Kept profile switching, Tracking integrations, custom server switching, iOS 15 support, and GPL provenance.
- Bumped product version to 0.1.7 / build 8.

## 0.1.6 — Local-only hardening

- Hide Account and its search results while keeping profile switching and Tracking available on phone and tablet.
- Reject Account navigation, including restored native routes and direct Settings page requests.
- Recover legacy authentication gates into the stable local session and disable device-link startup.
- Prevent remote profile/PIN RPCs in local-only mode; retain local profile persistence and cached PIN verification. Remote PIN setup/reset remains unavailable.
- Bumped product version to 0.1.6 / build 7.

## 0.1.5 — Local-only

- Switched startup authentication to a stable local installation identity and removed Supabase auth/session observation from the runtime path.
- Disabled email account sign-in, sign-up, account deletion, and server-switch auth operations in the local-only build while retaining source-compatible repository APIs.
- Disabled membership entitlement polling and remote membership verification.
- Changed the profile avatar catalog to cache-only operation so startup/profile editing no longer requests Supabase avatar or member-asset catalogs.
- Kept local profile/settings persistence and the existing iOS 15 unsigned Release IPA validation pipeline intact.
- Bumped product version to 0.1.5 / build 6.

## 0.1.4 — Branding

- Bumped product version to 0.1.4 / build 5.
- Added the official Solyvis light and dark branding master artwork to the iOS asset catalog.
- Wired `AppIcon.appiconset` to provide dedicated light and dark 1024×1024 App Store artwork.
- Added a shared-scheme pre-build action that center-crops the official Solyvis master artwork to exact 1024×1024 AppIcon assets before every Xcode build, avoiding a second manually maintained binary copy.
- Kept the existing iOS 15 Release/unsigned IPA audit pipeline unchanged for artifact validation.

## 0.1.3 — Development

- Started from the validated v0.1.2 iOS 15 compatibility baseline.
- Bumped product version to 0.1.3 / build 4 for the next development stage.
- Switched the sideload artifact pipeline to a clean unsigned iOS `Release` build.
- Package the device build deterministically as `Solyvis-v0.1.3-unsigned.ipa` with `Payload/Solyvis.app`.
- Normalize the embedded MPVKit/FFmpeg framework Mach-O deployment floor to iOS 15.0 after build, including FAT containers by extracting, rewriting and reassembling architecture slices.
- Keep framework `Info.plist` deployment floors synchronized at `MinimumOSVersion = 15.0`.
- Reject Release artifacts containing Debug/preview dylibs, embedded app extensions, code-signature directories or provisioning profiles.
- Audit the packaged IPA in CI before upload, including bundle identity/version and Mach-O minOS validation for the main executable and every embedded framework slice.
- Run #28 produced and independently validated a clean unsigned Release IPA with 28 audited Mach-O binaries, all at iOS 15.0.

## 0.1.2 — iOS 15 Baseline

- Set the main iOS application target deployment floor to iOS 15.0.
- Detached `DownloadsWidgetExtension` from the main app target dependency graph.
- Removed the app-extension embed phase from the iOS 15 application target.
- Removed Live Activity capability declarations from the main app while retaining normal download functionality.
- Kept the Live Activity widget source and its own iOS 16.1 deployment floor for future/newer builds.
- Updated the shared Xcode scheme to reference `Solyvis.app`.
- Added macOS CI that builds the Kotlin/Native framework and native iOS app against the iOS 15 baseline.

## 0.1.1 — Product Identity

- Switched Android application IDs to `com.solyan.solyvis` / `com.solyan.solyvis.debug`.
- Updated Android and iOS display identity to Solyvis.
- Added the `solyvis://` deep-link scheme while retaining `nuvio://` as a compatibility alias.
- Switched default Trakt and Simkl callback URIs to the Solyvis scheme.
- Updated native iOS application and widget bundle IDs to the Solyvis namespace.
- Updated the Kotlin iOS framework bundle ID to `com.solyan.solyvis`.
- Hardened `generateRuntimeConfigs` so a missing `local.properties` is truly optional in CI.
- Preserved internal `com.nuvio.*` Kotlin packages and theme identifiers for staged migration stability.

## 0.1.0 — Initial Baseline

- Created the independent Solyvis project identity.
- Recorded the exact Nuvio legacy migration source commit.
- Preserved the MPVKit upstream dependency definition.
- Renamed the Gradle root project to `Solyvis`.
- Established the iOS product name `Solyvis` and bundle namespace `com.solyan.solyvis`.
- Reset the Solyvis product version to `0.1.0` / build `1`.
- Retained GPL-3.0 licensing and upstream provenance notices.
- Began staged migration toward an iOS 15-compatible baseline.
