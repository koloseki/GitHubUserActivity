import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Main {
    public static void main(String[] args) {
        try{
            HttpClient client = HttpClient.newHttpClient();

            if (args.length < 1) {
                System.err.println("You dint't provide the username you want to search history off.");
                return;
            }
            String username = args[0];

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.github.com/users/" + username + "/events"))
                    .header("User-Agent", "GitHubUserActivity/1.0")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println(response.body());
            }else {
                System.out.println("Something went wrong :(");
                System.out.println("Error: " + response.statusCode());
            }



        }catch (Exception e){
            e.printStackTrace();
        }

    }
}