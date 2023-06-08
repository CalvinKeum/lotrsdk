# Lord of the Rings SDK Design
## Overview
The Lord of the Rings SDK is a Java library that provides a convenient interface to interact with The One API, which offers data related to The Lord of the Rings movies and quotes. The SDK encapsulates the API calls and provides a simplified and intuitive way to retrieve movie and quote data.

## Architecture
The SDK follows a simple and modular architecture with the following key components:

- LordOfTheRingsSDK: This class is responsible for making HTTP requests to The One API and handling the authentication using an API key. It provides a sendGetRequest method to send GET requests and retrieve the response.

- Movies: This class acts as a wrapper around the LordOfTheRingsSDK and provides methods to retrieve movie data. It exposes functions like getAllMovies, getMovieById, and getMovieQuotes to fetch movie-related information.

- Quotes: This class also wraps the LordOfTheRingsSDK and offers methods to retrieve quote data. It includes functions such as getAllQuotes and getQuoteById to retrieve quotes.

- Exception Handling: The SDK includes exception classes to handle errors and provide meaningful error messages to the SDK users.

## Testing
The SDK includes a set of JUnit test classes to ensure the correctness and reliability of the SDK functionalities. The test classes cover various scenarios, including successful API calls, error handling, and edge cases. These tests can be executed using tools like Maven or IDEs with built-in JUnit support.