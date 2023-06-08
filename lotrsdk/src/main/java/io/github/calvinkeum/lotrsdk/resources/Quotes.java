package io.github.calvinkeum.lotrsdk.resources;

import io.github.calvinkeum.lotrsdk.LordOfTheRingsSDK;

public class Quotes {
    private LordOfTheRingsSDK lotrSDK;

    public Quotes(LordOfTheRingsSDK lotrSDK) {
        this.lotrSDK = lotrSDK;
    }

    public String getAllQuotes() throws Exception {
        String url = API_QUOTE_URL;
        return lotrSDK.sendGetRequest(url);
    }

    public String getQuoteById(String quoteId) throws Exception {
        String url = API_QUOTE_URL + "/" + quoteId;
        return lotrSDK.sendGetRequest(url);
    }

    private static final String API_QUOTE_URL = "https://the-one-api.dev/v2/quote";
}