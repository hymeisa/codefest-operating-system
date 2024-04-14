package NETWORK;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestNetwork {

    public static void main(String[] args) {
        // Test fetching website information
        fetchWebsiteInfoTest();
    }

    public static void fetchWebsiteInfoTest() {
        try {
            String url = "https://www.example.com"; // Replace with the URL you want to test
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();

            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            System.out.println("Response code: " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String htmlContent = response.toString();
            String title = extractTitle(htmlContent);
            System.out.println("Title: " + title);

            String[] paragraphs = extractParagraphs(htmlContent);
            System.out.println("Paragraphs:");
            for (String paragraph : paragraphs) {
                System.out.println(paragraph);
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String extractTitle(String htmlContent) {
        Pattern titlePattern = Pattern.compile("<title>(.*?)</title>");
        Matcher matcher = titlePattern.matcher(htmlContent);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "Title not found";
        }
    }

    private static String[] extractParagraphs(String htmlContent) {
        Pattern paragraphPattern = Pattern.compile("<p>(.*?)</p>");
        Matcher matcher = paragraphPattern.matcher(htmlContent);
        StringBuilder paragraphs = new StringBuilder();
        while (matcher.find()) {
            paragraphs.append(matcher.group(1)).append("\n");
        }
        return paragraphs.toString().split("\n");
    }
}
