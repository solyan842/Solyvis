# iOS 15 Player EDR audit

`CAMetalLayer.wantsExtendedDynamicRangeContent` is iOS 16.0+ on iOS. Solyvis therefore keeps access to the underlying EDR API behind `#available(iOS 16.0, *)` and falls back to SDR on iOS 15.

## v0.1.2 compatibility design

`iosApp/iosApp/Player/MetalLayer.swift` provides an iOS 15-safe compatibility override:

- On iOS 16+, reads and writes are forwarded to `CAMetalLayer.wantsExtendedDynamicRangeContent`, preserving the existing main-thread handoff used to avoid an mpv vo-thread deadlock.
- On iOS 15, the getter returns `false` and the setter is a no-op.

Because `MPVPlayerBridge.swift` accesses the property through the concrete `MetalLayer` type, its existing EDR assignments remain unchanged and route through this compatibility shim. This keeps the player source stable while allowing the iOS 15 build to use SDR safely.

## Static audit status

The remaining main-app Swift files were reviewed for iOS 16+ API use. Live Activity code is guarded by iOS 16.1 availability, orientation geometry updates are guarded by iOS 16 availability with an iOS 15 rotation fallback, and the app entry/Now Playing paths use APIs available on iOS 15.

A real Xcode iOS 15 build is still required before v0.1.2 can be merged. GitHub Actions hosted runners are currently not being assigned to the workflow jobs, so CI has not yet executed this final source state.
