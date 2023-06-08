package io.github.calvinkeum.lotrsdk.resources;

import io.github.calvinkeum.lotrsdk.LordOfTheRingsSDK;

public class Movies {
    private LordOfTheRingsSDK lotrSDK;

    public Movies(LordOfTheRingsSDK lotrSDK) {
        this.lotrSDK = lotrSDK;
    }

    public String getAllMovies() throws Exception {
        String url = API_MOVIE_URL;
        return lotrSDK.sendGetRequest(url);
    }

    public String getMovieById(String movieId) throws Exception {
        String url = API_MOVIE_URL + "/" + movieId;
        return lotrSDK.sendGetRequest(url);
    }

    public String getMovieQuotes(String movieId) throws Exception {
        String url = API_MOVIE_URL + "/" + movieId + "/quote";
        return lotrSDK.sendGetRequest(url);
    }

    private static final String API_MOVIE_URL = "https://the-one-api.dev/v2/movie";
}
