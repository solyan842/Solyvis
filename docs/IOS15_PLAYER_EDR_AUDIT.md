# iOS 15 Player EDR audit

`CAMetalLayer.wantsExtendedDynamicRangeContent` is iOS 16.0+ on iOS. The iOS 15 compatibility branch must therefore keep EDR access behind `#available(iOS 16.0, *)` and fall back to SDR on iOS 15.

Affected source locations identified during the v0.1.2 audit:

- `iosApp/iosApp/Player/MetalLayer.swift`: the override must carry iOS 16 availability.
- `iosApp/iosApp/Player/MPVPlayerBridge.swift`: both direct assignments to `metalLayer.wantsExtendedDynamicRangeContent` must be availability-guarded.

The `MetalLayer.swift` override has been guarded. The two bridge assignments are the remaining source edits before the next real iOS 15 Xcode verification run.
