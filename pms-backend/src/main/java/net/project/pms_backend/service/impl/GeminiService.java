package net.project.pms_backend.service.impl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
public class GeminiService {

    private static final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=";
    private static final String API_KEY = "AIzaSyDNyi-bN8CY_31bMCGZrxR0IOXwkkgUAJA";
    private final HttpClient client;

    public GeminiService() {
        this.client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }

    public JSONObject extractInfoFromGemini(String extractedText) {
        try {
            JSONObject requestBody = new JSONObject();
            JSONObject contents = new JSONObject();
            JSONArray parts = new JSONArray();
            JSONObject textPart = new JSONObject();

            // Refined prompt requesting JSON format
            String prompt = "You are an AI assistant specialized in extracting information from text. " +
                    "Please extract the following information from the given text, if available:\n" +
                    "- Name\n- Block\n- Floor\n- Unit Number\n- Phone Number\n\n" +
                    "Return the information in a JSON format with the following structure:\n" +
                    "{\n" +
                    "  \"name\": \"[extracted name or empty string]\",\n" +
                    "  \"block\": \"[extracted block or empty string]\",\n" +
                    "  \"floor\": \"[extracted floor or empty string]\",\n" +
                    "  \"unitNumber\": \"[extracted unit number or empty string]\",\n" +
                    "  \"phoneNumber\": \"[extracted phone number or empty string]\"\n" +
                    "  \"barcode\": \"[extracted barcode or empty string]\"\n" +
                    "}\n\n" +
                    "If a piece of information is not found, use an empty string for that field. " +
                    "Ensure all fields are present in the JSON, even if empty.\n\n" +
                    "Here's the text to analyze:\n\n" + extractedText;

            textPart.put("text", prompt);
            parts.put(textPart);
            contents.put("parts", parts);
            JSONArray contentArray = new JSONArray();
            contentArray.put(contents);
            requestBody.put("contents", contentArray);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(GEMINI_API_URL + API_KEY))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String responseBody = response.body();
                JSONObject jsonResponse = new JSONObject(responseBody);

                JSONArray candidates = jsonResponse.getJSONArray("candidates");
                JSONObject firstCandidate = candidates.getJSONObject(0);
                JSONObject content = firstCandidate.getJSONObject("content");
                JSONArray partsResponse = content.getJSONArray("parts");

                if (partsResponse.length() > 0) {
                    String extractedTextResult = partsResponse.getJSONObject(0).getString("text");

                    // Extract the JSON part from the response
                    int startIndex = extractedTextResult.indexOf("{");
                    int endIndex = extractedTextResult.lastIndexOf("}") + 1;
                    String jsonPart = extractedTextResult.substring(startIndex, endIndex);

                    // Parse the JSON response
                    JSONObject parcelInfo = new JSONObject(jsonPart);

                    // Ensure all required fields are present
                    JSONObject finalParcelInfo = new JSONObject();
                    finalParcelInfo.put("name", parcelInfo.optString("name", ""));
                    finalParcelInfo.put("block", parcelInfo.optString("block", ""));
                    finalParcelInfo.put("floor", parcelInfo.optString("floor", ""));
                    finalParcelInfo.put("unitNumber", parcelInfo.optString("unitNumber", ""));
                    finalParcelInfo.put("phoneNumber", parcelInfo.optString("phoneNumber", ""));
                    finalParcelInfo.put("barcode", parcelInfo.optString("barcode", ""));

                    return finalParcelInfo;
                } else {
                    System.out.println("Error: No parts in the response from Gemini.");
                    return createEmptyParcelInfo("No response from Gemini API.");
                }
            } else {
                System.out.println("Error: " + response.statusCode());
                System.out.println("Response Body: " + response.body());
                return createEmptyParcelInfo("Error response from Gemini API: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return createEmptyParcelInfo("Exception occurred: " + e.getMessage());
        }
    }

    private JSONObject createEmptyParcelInfo(String errorMessage) {
        JSONObject parcelInfo = new JSONObject();
        parcelInfo.put("name", "");
        parcelInfo.put("block", "");
        parcelInfo.put("floor", "");
        parcelInfo.put("unitNumber", "");
        parcelInfo.put("phoneNumber", "");
        parcelInfo.put("barcode", "");
        parcelInfo.put("error", errorMessage);
        return parcelInfo;
    }
}
