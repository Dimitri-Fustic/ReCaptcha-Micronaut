package me.dimitri.recaptcha.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.*;

import java.io.IOException;

public class VerifyCaptcha {

    /* This is just our utility class that will actually make sure the received site key is valid */

    private static final String url = "https://www.google.com/recaptcha/api/siteverify";

    // Enter your private key here, you should probably keep your keys somewhere else tho
    private static final String privateKey = "PRIVATE_KEY_HERE";
    private static final Gson gson = new Gson();

    public static boolean isValid(String siteKey) {
        if (siteKey == null || siteKey.isEmpty()) {
            return false;
        }

        /* Since the key is in json format we're extracting the captcha response value itself */
        siteKey = extractKey(siteKey);

        try {
            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            String postParams = "secret=" + privateKey + "&response=" + siteKey;
            RequestBody body = RequestBody.create(mediaType, postParams);

            Request request = new Request.Builder()
                    .url(url)
                    .header("User-Agent", "Mozilla/5.0")
                    .header("Accept-Language", "en-US,en;q=0.5")
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();

            if (response.body() != null) {
                String responseBody = response.body().string();
                JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);

                return jsonObject.get("success").getAsBoolean();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    private static String extractKey(String data) {
        JsonObject jsonObject = gson.fromJson(data, JsonObject.class);
        return jsonObject.get("captchaResponse").getAsString();
    }
}
