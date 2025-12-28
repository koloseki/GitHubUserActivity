import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import models.InputHandler;

public class Main {
    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        String username;

        try{
            HttpClient client = HttpClient.newHttpClient();

            if (args.length < 1) {
                System.err.println("You dint't provide the username you want to search history off.");
                username = inputHandler.getUsername();
            }else {
                username = args[0];
            }

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.github.com/users/" + username + "/events"))
                    .header("User-Agent", "GitHubUserActivity/1.0")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                if(response.body().equals("[]")){
                    System.out.println("No events found.");
                }else {
                    System.out.println(response.body());
                }
            }else {
                System.out.println("Something went wrong :(");
                System.out.println("Error: " + response.statusCode());
            }



        }catch (Exception e){
            e.printStackTrace();
        }

    }
}