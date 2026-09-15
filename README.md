# Solyvis

**Solyvis** is a cross-platform media-center project by SolYan.

Current development baseline: **v0.1.3**.

## Project identity

- Product name: `Solyvis`
- Primary iOS bundle namespace: `com.solyan.solyvis`
- Main iOS application deployment target: `15.0`
- Live Activity widget deployment target: `16.1+` (retained in source, not embedded in the iOS 15 app)
- Current migration target: iOS/iPadOS 15+ compatibility
- MPV playback baseline: Nuvio MPVKit branch retained during migration

## Upstream provenance

Solyvis is derived from GPL-3.0-licensed Nuvio Mobile source code. The initial migration baseline is:

- Source repository: `solyan842/Nuvio-Legacy-iOS15`
- Source branch: `legacy-ios15`
- Source commit: `011e824fcfe6c27f221ad81ecdc66b60be9fc283`
- MPVKit baseline: `d5cf091c80368bbbc1bbf2d195fbc55d926df888`

Solyvis keeps the applicable GPL-3.0 license and upstream attribution. Solyvis-specific branding and modifications are maintained separately from the reference repository.

## Migration policy

The migration is intentionally staged to keep build failures diagnosable:

1. Preserve upstream provenance and dependency baselines.
2. Establish Solyvis product identity and versioning.
3. Import the iOS/Kotlin Multiplatform build baseline.
4. Lower the iOS application deployment target to iOS 15.
5. Exclude the iOS 16.1+ Live Activity extension from the iOS 15 application target while retaining normal downloads.
6. Validate Kotlin/Native, MPVKit and the native Xcode target in CI.
7. Rename internal Kotlin packages only after the compatibility baseline is stable.

## Status

`v0.1.2` is the validated iOS 15 compatibility baseline. `v0.1.3` starts the next development stage from that green baseline without changing the established iOS 15 deployment floor.
