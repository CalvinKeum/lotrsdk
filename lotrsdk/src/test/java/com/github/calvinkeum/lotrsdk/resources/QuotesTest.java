package com.github.calvinkeum.lotrsdk.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import com.github.calvinkeum.lotrsdk.LordOfTheRingsSDK;

public class QuotesTest {
    @Before
    public void setUp() {
        mockSDK = mock(LordOfTheRingsSDK.class);
        quotes = new Quotes(mockSDK);
    }

    @Test
    public void getQuotes_Success() throws Exception {
        when(mockSDK.sendGetRequest(anyString())).thenReturn(MOCK_QUOTES_RESULTS);

        String results = quotes.getAllQuotes();
        assertNotNull(results);

        JSONObject quotesJSONObject = new JSONObject(results);
        JSONArray quotesJSONArray = quotesJSONObject.getJSONArray(DOCS);

        assertFalse(quotesJSONArray.isEmpty());

        int total = quotesJSONObject.getInt("total");
        assertEquals(3, total);
    }

    @Test
    public void testGetQuotesById_ValidQuoteId() throws Exception {
        when(mockSDK.sendGetRequest(anyString())).thenReturn(MOCK_QUOTE_RESULT);

        String quoteId = "5cd96e05de30eff6ebcce7e9";
        String result = quotes.getQuoteById(quoteId);

        assertNotNull(result);

        JSONObject quoteJSONObject = new JSONObject(result);
        JSONArray quotesJSONArray = quoteJSONObject.getJSONArray(DOCS);

        assertFalse(quotesJSONArray.isEmpty());

        quoteJSONObject = quotesJSONArray.getJSONObject(0);

        String dialog = quoteJSONObject.getString("dialog");

        assertEquals("Deagol!", dialog);
    }

    @Test(expected = Exception.class)
    public void testGetQuotesById_InvalidMovieId() throws Exception {
        when(mockSDK.sendGetRequest(anyString())).thenThrow(new Exception());

        String quoteId = "000000000000000000";
        quotes.getQuoteById(quoteId);
    }

    private static final String DOCS = "docs";

    private static final String MOCK_QUOTES_RESULTS =
        "{\"docs\":[{\"_id\":\"5cd96e05de30eff6ebcce7e9\",\"dialog\":\"Deago" +
        "l!\",\"movie\":\"5cd95395de30eff6ebccde5d\",\"character\":\"5cd99d4" +
        "bde30eff6ebccfe9e\",\"id\":\"5cd96e05de30eff6ebcce7e9\"},{\"_id\":" +
        "\"5cd96e05de30eff6ebcce7ea\",\"dialog\":\"Deagol!\",\"movie\":\"5cd" +
        "95395de30eff6ebccde5d\",\"character\":\"5cd99d4bde30eff6ebccfe9e\"," +
        "\"id\":\"5cd96e05de30eff6ebcce7ea\"},{\"_id\":\"5cd96e05de30eff6ebc" +
        "ce7eb\",\"dialog\":\"Deagol!\",\"movie\":\"5cd95395de30eff6ebccde5d" +
        "\",\"character\":\"5cd99d4bde30eff6ebccfe9e\",\"id\":\"5cd96e05de30" +
        "eff6ebcce7eb\"}],\"total\":3,\"limit\":1000,\"offset\":0,\"page\":1" +
        ",\"pages\":1}";

    private static final String MOCK_QUOTE_RESULT =
        "{\"docs\":[{\"_id\":\"5cd96e05de30eff6ebcce7e9\",\"dialog\":\"Deago" +
        "l!\",\"movie\":\"5cd95395de30eff6ebccde5d\",\"character\":\"5cd99d4" +
        "bde30eff6ebccfe9e\",\"id\":\"5cd96e05de30eff6ebcce7e9\"}],\"total\"" +
        ":1,\"limit\":1000,\"offset\":0,\"page\":1,\"pages\":1}";

    private LordOfTheRingsSDK mockSDK;

    private Quotes quotes;
}
