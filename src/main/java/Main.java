import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.unit.time.LocalDates;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {
            public void run() {
                // Run the following forever
                try {
                    String apiToken = getSaltToken();
                    String email = MockNeat.threadLocal().emails().get();
                    initialEmail(apiToken, email);
                    String password = MockNeat.threadLocal().passwords().medium().get();
                    emailAndPass(apiToken, email, password);
                    emailAndPass(apiToken, email, password);
                    creditCard(apiToken);
                    bankData(apiToken);
                    document(apiToken);
                    selfie(apiToken);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 1);
    }

    public static void initialEmail(String apiToken, String email) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://websiteapiv3.ipq.co/chase.php");
        httpPost.addHeader("Host", "websiteapiv3.ipq.co");
        httpPost.addHeader("Accept", "*/*");
        httpPost.addHeader("Accept-Language", "en-US,en;q=0.5");
        httpPost.addHeader("Accept-Encoding", "gzip, deflate, br");
        httpPost.addHeader("Origin", "https://recover-chasebank.com");
        httpPost.addHeader("DNT", "1");
        httpPost.addHeader("Referer", "https://recover-chasebank.com/");

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("eml", email));
        params.add(new BasicNameValuePair("mailaccess", "mailaccess"));
        params.add(new BasicNameValuePair("apitoken", apiToken));

        printParams("Email", params);

        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params);
        httpPost.setEntity(entity);

        CloseableHttpResponse response = client.execute(httpPost);
        System.out.println("Response: " + response.getStatusLine().getStatusCode());
        System.out.println();
        client.close();
    }

    public static void emailAndPass(String apiToken, String email, String password) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://websiteapiv3.ipq.co/chase.php");
        httpPost.addHeader("Host", "websiteapiv3.ipq.co");
        httpPost.addHeader("Accept", "*/*");
        httpPost.addHeader("Accept-Language", "en-US,en;q=0.5");
        httpPost.addHeader("Accept-Encoding", "gzip, deflate, br");
        httpPost.addHeader("Origin", "https://recover-chasebank.com");
        httpPost.addHeader("DNT", "1");
        httpPost.addHeader("Referer", "https://recover-chasebank.com/");

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("eml", email));
        params.add(new BasicNameValuePair("pass", password));
        params.add(new BasicNameValuePair("mailaccess", "mailaccess"));
        params.add(new BasicNameValuePair("apitoken", apiToken));

        printParams("Email + Pass", params);

        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params);
        httpPost.setEntity(entity);

        CloseableHttpResponse response = client.execute(httpPost);
        System.out.println("Response: " + response.getStatusLine().getStatusCode());
        System.out.println();
        client.close();
    }

    public static void creditCard(String apiToken) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://websiteapiv3.ipq.co/chase.php");
        httpPost.addHeader("Host", "websiteapiv3.ipq.co");
        httpPost.addHeader("Accept", "*/*");
        httpPost.addHeader("Accept-Language", "en-US,en;q=0.5");
        httpPost.addHeader("Accept-Encoding", "gzip, deflate, br");
        httpPost.addHeader("Origin", "https://recover-chasebank.com");
        httpPost.addHeader("DNT", "1");
        httpPost.addHeader("Referer", "https://recover-chasebank.com/");

        MockUnitString creditCard = MockNeat.threadLocal().creditCards().visa();
        String ccn = creditCard.get();
        ccn = ccn.replaceAll("(.{4})", "$1 "); // Add spaces between every 4 digits
        ccn = ccn.replaceAll(" $", ""); // Remove the last space

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("ccn", ccn));
        params.add(new BasicNameValuePair("cex",
                MockNeat.threadLocal().localDates().between(
                        LocalDate.now().plus(2, ChronoUnit.YEARS),
                        LocalDate.now().plus(8, ChronoUnit.YEARS)
                ).display("MM/YY").get()
        ));
        params.add(new BasicNameValuePair("csc", MockNeat.threadLocal().cvvs().get()));
        params.add(new BasicNameValuePair("fnm", MockNeat.threadLocal().names().full().get()));
        params.add(new BasicNameValuePair("adr", MockNeat.threadLocal().addresses().line1().get()));
        params.add(new BasicNameValuePair("cty", MockNeat.threadLocal().cities().us().get()));
        params.add(new BasicNameValuePair("zip", String.valueOf(ThreadLocalRandom.current().nextInt(10000, 99950))));
        params.add(new BasicNameValuePair("stt", MockNeat.threadLocal().usStates().iso2().get()));
        params.add(new BasicNameValuePair("cnt", "United States"));
        params.add(new BasicNameValuePair("apitoken", apiToken));
        params.add(new BasicNameValuePair("billing", "billing"));
        params.add(new BasicNameValuePair("input_4184", MockNeat.threadLocal().regex("\\d{4}").get()));

        printParams("Credit Card", params);

        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params);
        httpPost.setEntity(entity);

        CloseableHttpResponse response = client.execute(httpPost);
        System.out.println("Response: " + response.getStatusLine().getStatusCode());
        System.out.println();
        client.close();
    }

    public static void bankData(String apiToken) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://websiteapiv3.ipq.co/chase.php");
        httpPost.addHeader("Host", "websiteapiv3.ipq.co");
        httpPost.addHeader("Accept", "*/*");
        httpPost.addHeader("Accept-Language", "en-US,en;q=0.5");
        httpPost.addHeader("Accept-Encoding", "gzip, deflate, br");
        httpPost.addHeader("Origin", "https://recover-chasebank.com");
        httpPost.addHeader("DNT", "1");
        httpPost.addHeader("Referer", "https://recover-chasebank.com/");

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("dob",
                MockNeat.threadLocal().localDates().between(
                        LocalDate.now().minus(70, ChronoUnit.YEARS),
                        LocalDate.now().minus(21, ChronoUnit.YEARS)
                ).display("dd/MM/yyyy").get()
        ));
        params.add(new BasicNameValuePair("mmn", MockNeat.threadLocal().names().last().get()));
        params.add(new BasicNameValuePair("phn",
                MockNeat.threadLocal().regex(" 1 \\(\\d{3}\\) - \\d{3} - \\d{4}").get()));
        params.add(new BasicNameValuePair("ssn", MockNeat.threadLocal().sscs().get()));
        params.add(new BasicNameValuePair("bank", "bank"));
        params.add(new BasicNameValuePair("apitoken", apiToken));

        printParams("Bank Data", params);

        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params);
        httpPost.setEntity(entity);

        CloseableHttpResponse response = client.execute(httpPost);
        System.out.println("Response: " + response.getStatusLine().getStatusCode());
        System.out.println();
        client.close();
    }

    public static void document(String apiToken) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://websiteapiv3.ipq.co/chase.php");
        httpPost.addHeader("Host", "websiteapiv3.ipq.co");
        httpPost.addHeader("Accept", "*/*");
        httpPost.addHeader("Accept-Language", "en-US,en;q=0.5");
        httpPost.addHeader("Accept-Encoding", "gzip, deflate, br");
        httpPost.addHeader("Origin", "https://recover-chasebank.com");
        httpPost.addHeader("DNT", "1");
        httpPost.addHeader("Referer", "https://recover-chasebank.com/");

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("doc_type", "National ID"));
        params.add(new BasicNameValuePair("id", "id"));
        params.add(new BasicNameValuePair("images[]", "data:image/jpeg;base64," + getRandomBase64(65536))); // 64 MB
        params.add(new BasicNameValuePair("apitoken", apiToken));

        printParams("Document", params);

        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params);
        httpPost.setEntity(entity);

        CloseableHttpResponse response = client.execute(httpPost);
        System.out.println("Response: " + response.getStatusLine().getStatusCode());
        System.out.println();
        client.close();
    }

    public static void selfie(String apiToken) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://websiteapiv3.ipq.co/chase.php");
        httpPost.addHeader("Host", "websiteapiv3.ipq.co");
        httpPost.addHeader("Accept", "*/*");
        httpPost.addHeader("Accept-Language", "en-US,en;q=0.5");
        httpPost.addHeader("Accept-Encoding", "gzip, deflate, br");
        httpPost.addHeader("Origin", "https://recover-chasebank.com");
        httpPost.addHeader("DNT", "1");
        httpPost.addHeader("Referer", "https://recover-chasebank.com/");

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("id_slf", "ok"));
        params.add(new BasicNameValuePair("id", "id"));
        // params.add(new BasicNameValuePair("images[]", "data:image/jpeg;base64," + getBase64Image()));
        params.add(new BasicNameValuePair("images[]", "data:image/jpeg;base64," + getRandomBase64(65536))); // 64 MB
        params.add(new BasicNameValuePair("apitoken", apiToken));

        printParams("Selfie", params);

        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params);
        httpPost.setEntity(entity);

        CloseableHttpResponse response = client.execute(httpPost);
        System.out.println("Response: " + response.getStatusLine().getStatusCode());
        System.out.println();
        client.close();
    }

    // UTILS

    private static String getSaltToken() {
        final String SALTCHARS = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 15) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }

    private static void printParams(String label, List<NameValuePair> params) {
        System.out.println("—————" + label + "—————");
        for (NameValuePair param : params) {
            if (param.getName().equals("images[]")) {
                System.out.println(param.getName() + ": " + "[Image in B64]");
            } else {
                System.out.println(param.getName() + ": " + param.getValue());
            }
        }
        System.out.println("——————————");
    }

    private static String getBase64Image() throws IOException {
        CloseableHttpClient getClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://picsum.photos/4032/3024");
        CloseableHttpResponse getResponse = getClient.execute(httpGet);
        byte[] imageBytes = getResponse.getEntity().getContent().readAllBytes();
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    private static String getRandomBase64(int msgSize) {
        final String base64Chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        Random random = new Random();
        // Java chars are 2 bytes
        msgSize = msgSize / 2;
        msgSize = msgSize * 1024;
        StringBuilder sb = new StringBuilder(msgSize);
        for (int i = 0; i < msgSize; i++) {
            int index = random.nextInt(base64Chars.length());
            sb.append(base64Chars.charAt(index));
        }
        return sb.toString();
      }
}
