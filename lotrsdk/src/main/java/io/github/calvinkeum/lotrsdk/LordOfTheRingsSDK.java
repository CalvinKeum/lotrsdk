package io.github.calvinkeum.lotrsdk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.log4j.Logger;

public class LordOfTheRingsSDK {
    public LordOfTheRingsSDK() {
        this.apiKey = getAPIKey();
    }

    public LordOfTheRingsSDK(String apiKey) {
        this.apiKey = apiKey;
    }

    public String sendGetRequest(String url) throws Exception {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
    
        try {
            URL apiURL = new URL(url);
    
            connection = (HttpURLConnection) apiURL.openConnection();
            connection.setRequestMethod(HTTP_GET);
            connection.setRequestProperty(AUTH_HEADER, BEARER + apiKey);
    
            int responseCode = connection.getResponseCode();
    
            if (responseCode >= 200 && responseCode < 300) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
    
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
    
                return response.toString();
            }
            else {
                throw new Exception("API request failed for \"" + url + "\" with response code: " + responseCode);
            }
        }
        catch (IOException ioe) {
            logger.error(ioe);
            throw ioe;
        }
        finally {
            if (reader != null) {
                reader.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }    

    private String getAPIKey() {
        try {
            return Files.readString(Paths.get(SECRET_FILE_PATH)).trim();
        }
        catch (IOException ioe) {
            logger.error(ioe);
            return null;
        }
    }

    private static final Logger logger = Logger.getLogger(LordOfTheRingsSDK.class);

    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER = "Bearer ";
    private static final String HTTP_GET = "GET";
    private static final String SECRET_FILE_PATH = "lotrsdk/resources/secret";

    private String apiKey;
}