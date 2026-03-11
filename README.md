# SkPNews

Android news application for the province of Sidi Kacem, built with Java and XML.

## Overview

SkPNews is an Android app that displays local news content with multiple browsing options such as home feed, categories, search, and detailed article views.

## Features

- Home screen with news list and slider content
- Category-based news browsing
- Search screen for finding articles
- News details screen
- Loading states using shimmer placeholders

## Tech Stack

- Java
- XML layouts
- Android Fragments + Navigation Component
- Firebase (Google Services integration)
- RecyclerView
- ViewBinding
- Glide (image loading)
- Shimmer effects

## What I Learned

- Building multi-screen apps with Fragments
- Working with Firebase in Android projects
- Implementing RecyclerView-based lists

## Project Structure

- `app/src/main/java/com/example/skpnewsmvvm` - app source code
- `app/src/main/res` - layouts, drawables, navigation, fonts, and other resources
- `app/src/main/AndroidManifest.xml` - app manifest and activities

## Getting Started

1. Open the project in Android Studio.
2. Let Gradle sync all dependencies.
3. Make sure `app/google-services.json` is valid for your Firebase project.
4. Build and run the `app` module on an emulator or Android device.

## Screenshots

| Home 1 | Home 2 |
| --- | --- |
| <img src="https://github.com/user-attachments/assets/0bef9dda-f64f-4838-b4a7-e4d642ea5aa6" alt="Home 1" width="300" /> | <img src="https://github.com/user-attachments/assets/455c6496-69fc-4a6b-96f9-892300151c87" alt="Home 2" width="300" /> |

| Details | Categories |
| --- | --- |
| <img src="https://github.com/user-attachments/assets/c289d31b-fa81-406c-8521-c7a8e8626f9f" alt="Details" width="300" /> | <img src="https://github.com/user-attachments/assets/a7baf469-f958-4217-b32d-c2999e50782f" alt="Categories" width="300" /> |
