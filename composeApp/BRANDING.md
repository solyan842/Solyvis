# Solyvis Branding Contract

Solyvis is a controlled rebrand of the upstream Nuvio Mobile codebase.

For user-facing product identity:

- Product name: `Solyvis`
- iOS bundle identifier: `com.solyan.solyvis`
- Primary URL callback: `solyvis://`
- Active UI branding and localized product references should use `Solyvis`.

Compatibility and provenance must remain intact:

- `nuvio://` remains a compatibility callback alias where required.
- Internal `com.nuvio.*` package names remain unchanged unless a separately validated migration is performed.
- `Nuvio Mobile` remains in upstream/GPL attribution where it identifies the original project.
- GPL-3.0 licensing and upstream provenance must be preserved.

Visual branding for v0.1.4 uses the official Solyvis Light and Dark artwork. Legacy icon aliases may remain internally for persisted-data compatibility, but they must not be presented as active Solyvis branding choices.
