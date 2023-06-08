package com.github.calvinkeum.lotrsdk.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import com.github.calvinkeum.lotrsdk.LordOfTheRingsSDK;

public class MoviesTest {
    
    @Before
    public void setUp() {
        mockSDK = mock(LordOfTheRingsSDK.class);
        movies = new Movies(mockSDK);
    }

    @Test
    public void testGetAllMovies_Success() throws Exception {
        when(mockSDK.sendGetRequest(anyString())).thenReturn(MOCK_MOVIES_RESULTS);

        String results = movies.getAllMovies();
        assertNotNull(results);

        JSONObject moviesJSONObject = new JSONObject(results);
        JSONArray moviesJSONArray = moviesJSONObject.getJSONArray(DOCS);

        assertFalse(moviesJSONArray.isEmpty());

        HashSet<String> movieTitles = new HashSet<>();

        for (int i = 0; i < moviesJSONArray.length(); i++) {
            JSONObject movieJSONObject = moviesJSONArray.getJSONObject(i);
            String title = movieJSONObject.getString("name");

            movieTitles.add(title);
        }

        assertTrue(movieTitles.contains("The Two Towers"));
        assertTrue(movieTitles.contains("The Fellowship of the Ring"));
        assertTrue(movieTitles.contains("The Return of the King"));
    }

    @Test
    public void testGetMovieById_ValidMovieId() throws Exception {
        when(mockSDK.sendGetRequest(anyString())).thenReturn(MOCK_MOVIE_RESULT);

        String movieId = "5cd95395de30eff6ebccde5b";
        String result = movies.getMovieById(movieId);

        assertNotNull(result);

        JSONObject moviesJSONObject = new JSONObject(result);
        JSONArray moviesJSONArray = moviesJSONObject.getJSONArray(DOCS);

        assertTrue(moviesJSONArray.length() == 1);

        JSONObject movieJSONObject = moviesJSONArray.getJSONObject(0);
        String title = movieJSONObject.getString("name");
        assertEquals("The Two Towers", title);
    }

    @Test(expected = Exception.class)
    public void testGetMovieById_InvalidMovieId() throws Exception {
        when(mockSDK.sendGetRequest(anyString())).thenThrow(new Exception());

        String movieId = "000000000000000000";
        movies.getMovieById(movieId);
    }

    @Test
    public void testGetMovieQuotes_Success() throws Exception {
        when(mockSDK.sendGetRequest(anyString())).thenReturn(MOCK_MOVIE_QUOTE_RESULT);

        String movieId = "5cd95395de30eff6ebccde5d";
        String results = movies.getMovieQuotes(movieId);

        assertNotNull(results);

        JSONObject movieQuotesJSONObject = new JSONObject(results);
        JSONArray moviesQuotesJSONArray = movieQuotesJSONObject.getJSONArray(DOCS);

        assertFalse(moviesQuotesJSONArray.isEmpty());

        int total = movieQuotesJSONObject.getInt("total");

        assertEquals(1, total);
    }

    @Test(expected = Exception.class)
    public void testGetMovieQuotes_InvalidMovieId() throws Exception {
        when(mockSDK.sendGetRequest(anyString())).thenThrow(new Exception());

        String movieId = "000000000000000000";
        movies.getMovieQuotes(movieId);
    }

    private static final String DOCS = "docs";

    private static final String MOCK_MOVIES_RESULTS = 
        "{\"docs\":[{\"_id\":\"5cd95395de30eff6ebccde5b\",\"name\":\"The Two" +
        " Towers\",\"runtimeInMinutes\":179,\"budgetInMillions\":94,\"boxOff" +
        "iceRevenueInMillions\":926,\"academyAwardNominations\":6,\"academyA" +
        "wardWins\":2,\"rottenTomatoesScore\":96},{\"_id\":\"5cd95395de30eff" +
        "6ebccde5c\",\"name\":\"The Fellowship of the Ring\",\"runtimeInMinu" +
        "tes\":178,\"budgetInMillions\":93,\"boxOfficeRevenueInMillions\":87" + 
        "1.5,\"academyAwardNominations\":13,\"academyAwardWins\":4,\"rottenT" +
        "omatoesScore\":91},{\"_id\":\"5cd95395de30eff6ebccde5d\",\"name\":" +
        "\"The Return of the King\",\"runtimeInMinutes\":201,\"budgetInMilli" +
        "ons\":94,\"boxOfficeRevenueInMillions\":1120,\"academyAwardNominati" +
        "ons\":11,\"academyAwardWins\":11,\"rottenTomatoesScore\":95}],\"tot" +
        "al\":3,\"limit\":1000,\"offset\":0,\"page\":1,\"pages\":1}";

    private static final String MOCK_MOVIE_RESULT =
        "{\"docs\":[{\"_id\":\"5cd95395de30eff6ebccde5b\",\"name\":\"The Two" +
        " Towers\",\"runtimeInMinutes\":179,\"budgetInMillions\":94,\"boxOff" +
        "iceRevenueInMillions\":926,\"academyAwardNominations\":6,\"academyA" +
        "wardWins\":2,\"rottenTomatoesScore\":96}],\"total\":1,\"limit\":100" +
        "0,\"offset\":0,\"page\":1,\"pages\":1}";

    private static final String MOCK_MOVIE_QUOTE_RESULT =
        "{\"docs\":[{\"_id\":\"5cd96e05de30eff6ebcce7e9\",\"dialog\":\"Deago" +
        "l!\",\"movie\":\"5cd95395de30eff6ebccde5d\",\"character\":\"5cd99d4" +
        "bde30eff6ebccfe9e\",\"id\":\"5cd96e05de30eff6ebcce7e9\"}],\"total\"" +
        ":1,\"limit\":1000,\"offset\":0,\"page\":1,\"pages\":1}";

    private LordOfTheRingsSDK mockSDK;

    private Movies movies;
}