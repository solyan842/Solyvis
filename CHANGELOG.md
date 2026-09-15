# Changelog

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
