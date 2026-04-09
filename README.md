# Experiment with Intents
A laboratory project exploring Implicit and Explicit Intents in Android.

### Topics Practiced
- Modern Splash Screen: Implemented androidx.core.core-splashscreen to align with current Android standards (avoiding the outdated SplashActivity + Intent pattern).
- AndroidManifest Management: Registered Activities and handled Intent filters.
- Gradle & Version Catalog (TOML): Managed dependencies and complex compiler plugins (kotlin-parcelize) using a centralized libs.versions.toml.
- Explicit Intents: Navigated between Activities while passing complex data.
- Data Serialization: Leveraged @Parcelize for efficient, boilerplate-free Parcelable implementation.
- Type-Safe Constants: Used companion object constants for Intent keys to prevent string-mismatch bugs and improve maintainability.
