# News App

A modern Android news application built with Jetpack Compose, MVVM architecture, and the News API.

## Features

- **News List**: Displays a list of news articles showing source name, author, and title
- **News Details**: Shows complete article details including description, content, and metadata
- **Modern UI**: Built with Material Design 3 and Jetpack Compose
- **Clean Architecture**: Follows MVVM pattern with proper separation of concerns
- **Dependency Injection**: Uses Hilt for dependency management

## Setup

1. **Get News API Key**: 
   - Sign up at [News API](https://newsapi.org/)
   - Get your API key from the dashboard

2. **Configure API Key**:
   - Open `app/src/main/java/com/example/crypto/common/Constants.kt`
   - Replace `YOUR_API_KEY_HERE` with your actual News API key

3. **Build and Run**:
   - Open the project in Android Studio
   - Sync Gradle files
   - Run the app on an emulator or device

## Architecture

The app follows Clean Architecture principles:

- **Data Layer**: API calls, DTOs, and Repository implementations
- **Domain Layer**: Use cases and domain models
- **Presentation Layer**: ViewModels, UI states, and Compose screens

## Key Components

### Data Layer
- `NewsApi`: Retrofit interface for News API calls
- `NewsDto`: Data transfer objects for API responses
- `NewsRepository`: Repository interface and implementation

### Domain Layer
- `News`: Domain model for news articles
- `NewsDetail`: Domain model for detailed news information
- `GetNewsUseCase`: Use case for fetching news list
- `GetNewsDetailUseCase`: Use case for fetching news details

### Presentation Layer
- `NewsListScreen`: Main screen showing news list
- `NewsDetailScreen`: Screen showing detailed news information
- `NewsListViewModel` & `NewsDetailViewModel`: ViewModels managing UI state

## API Integration

The app uses the News API's `/v2/everything` endpoint to fetch news articles. The default query is set to "technology" but can be easily modified in the `NewsApi` interface.

## Navigation

- Uses Jetpack Navigation Compose
- List screen → Detail screen navigation with article ID parameter
- Proper back navigation support

## Dependencies

- **Jetpack Compose**: Modern UI toolkit
- **Hilt**: Dependency injection
- **Retrofit**: HTTP client for API calls
- **Coroutines**: Asynchronous programming
- **Navigation Compose**: Navigation between screens
- **Material Design 3**: Modern design system

##Preview

![image](https://github.com/user-attachments/assets/3f4f08e4-867e-4d69-8222-a127567725f1)

![image](https://github.com/user-attachments/assets/5bd660fe-bf15-44b7-a256-f4ef6325abd3)


## Note

This application was transformed from a crypto application to a news application. All crypto-related functionality has been replaced with news functionality while maintaining the same architectural patterns and code structure. 
