# Experiment with Intents
A laboratory project exploring Implicit and Explicit Intents in Android.

### Topics Practiced

#### Round 1 of commits
- Modern Splash Screen: Implemented androidx.core.core-splashscreen to align with current Android standards (avoiding the outdated SplashActivity + Intent pattern).
- AndroidManifest Management: Registered Activities and handled Intent filters.
- Gradle & Version Catalog (TOML): Managed dependencies and complex compiler plugins (kotlin-parcelize) using a centralized libs.versions.toml.
- Explicit Intents: Navigated between Activities while passing complex data.
- Data Serialization: Leveraged @Parcelize for efficient, boilerplate-free Parcelable implementation.
- Type-Safe Constants: Used companion object constants for Intent keys to prevent string-mismatch bugs and improve maintainability.

#### Round 2 of commits
- Result-Back Pattern: Implemented the modern ActivityResultLauncher API to handle one-way and two-way communication between Activities, replacing the deprecated onActivityResult.
- State Hoisting & Callbacks: Practiced decoupling UI from business logic by hoisting intent-launching events to the Activity level via functional callbacks.
- Compose Recomposition Nuance: Explored the mechanics of localized vs. global recomposition by intentionally stress-testing un-remembered state against state-triggered updates.
- Tiered State Management: Experimented with three levels of state persistence:
- Disposable State: Logic-holder classes outside of Compose memory.
- Lifecycle-Aware State: Using remember to persist data across recompositions.
- Persistent UI State: Utilizing rememberSaveable to ensure data integrity through configuration changes (like screen rotation).
- Plain State Holder Pattern: Implemented a dedicated HomeScreenState class to manage complex UI logic, demonstrating an alternative to the "ViewModel-for-everything" approach.
