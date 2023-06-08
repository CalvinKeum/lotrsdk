# Lord of the Rings SDK

The Lord of the Rings SDK is a Java library that provides convenient access to The One API for retrieving information about Lord of the Rings movies and quotes.

## Installation

To use the Lord of the Rings SDK, you can include it as a dependency using a build tool like Maven or Gradle. Add the following dependency to your project configuration:

```xml
<dependency>
    <groupId>io.github.calvinkeum</groupId>
    <artifactId>lotrsdk</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

## Usage
1. Create an instance of LordOfTheRingsSDK by providing your API key:
    ```java
    LordOfTheRingsSDK lotrSDK = new LordOfTheRingsSDK("your-api-key");
    ```

    You can also utilize and add "your-api-key" in the "secret" file located "/lotrsdk/resources/"
    ```java
    // This constructor will utilize the api key located in the secret file.
    LordOfTheRingsSDK lotrSDK = new LordOfTheRingsSDK();
    ```

2. Use the Movies and Quotes classes to interact with the API endpoints:
    ### Movies

    - To get all movies:
        ```java
        Movies movies = new Movies(lotrSDK);
        String allMoviesResponse = movies.getAllMovies();
        ```

    - To get a specific movie by ID:
        ```java
        String movieId = "1234"; // Replace with the actual movie ID
        String movieResponse = movies.getMovieById(movieId);
        ```

    ### Quotes

    - To get quotes for a specific movie:
        ```java
        String movieId = "1234"; // Replace with the actual movie ID
        String quotesResponse = movies.getMovieQuotes(movieId);
        ```

    - To retrieve all quotes:
        ```java
        Quotes quotes = new Quotes(lotrSDK);
        String allQuotesResponse = quotes.getAllQuotes();
        ```

    - To get a specific quote by ID:
        ```java
        String quoteId = "1234"; // Replace with the actual quote ID
        String quoteResponse = quotes.getQuoteById(quoteId);
        ```

## Testing
The Lord of the Rings SDK provides JUnit test cases. You can run the tests using a test runner or your preferred IDE. The test cases are located in the src/test directory.

To run the tests using Maven, execute the following command in your project's root directory:

<code>mvn test</code>