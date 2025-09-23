# Teddy Buddy IoT Android App

A Jetpack Compose Android application that turns the provided front-end mockups into an interactive control center for the Teddy Buddy AI companion toy. The app demonstrates an onboarding flow, a connected home dashboard, and an emotional analytics report for parents and caregivers.

## Features

- **Welcome flow** inspired by the provided mockups, including call-to-action buttons for new users and SMS login.
- **Home dashboard** that visualizes connection status, battery/temperature/storage metrics, and exposes AI abilities such as long-term memory, emotion analysis, and themed conversations.
- **Smart routines** with toggleable IoT automations (nap mode, homework focus, good morning routine) to illustrate how the toy could integrate into daily life.
- **Emotion analytics** screen that renders custom Compose charts to summarise weekly emotional trends and distribution across six sentiment dimensions.
- **Design system** with Material 3, custom typography, and color palette to keep the experience soft and family friendly.

## Project structure

```
app/
├── src/main/java/com/teddybuddyaio/
│   ├── MainActivity.kt          # Navigation host and top-level state handling
│   ├── data/                    # Domain models and a fake repository for sample data
│   └── ui/                      # Composable screens, reusable components, and theming
└── src/main/res/                # Strings, colors, themes, and backup configurations
```

All UI is written in Jetpack Compose, so there are no XML layouts. The charts on the emotion analysis page are custom Canvas-based implementations to keep the dependency footprint light.

## Getting started

1. Open the project with Android Studio Hedgehog (or newer) and let Gradle sync the dependencies. The project targets **compileSdk 34** and requires **JDK 17**.
2. Connect an Android device or start an emulator that runs Android 8.0 (API 26) or newer.
3. Press **Run** to install the Teddy Buddy IoT app. The fake repository (`FakeTeddyBuddyRepository`) seeds the UI with data equivalent to the front-end screenshots.

> ℹ️  The project currently mocks the IoT back-end interactions. You can replace the repository with real Bluetooth, Wi-Fi, or cloud APIs when integrating with the physical toy.

## Customisation ideas

- Hook the device status and routines to real connectivity layers (Bluetooth Low Energy, MQTT, WebSockets, etc.).
- Swap the fake emotion analytics data with actual logs from your AI inference services.
- Localise the strings using Android's multi-language resources to better serve different regions.
- Extend the feature grid with additional skill modules such as "远程哄睡" or "课堂互动".

## License

This project is released under the [MIT License](LICENSE).
