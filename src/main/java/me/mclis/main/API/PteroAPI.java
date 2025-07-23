package me.mclis.main.API;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

public class PteroAPI {





    public void createServerVanilla(String name, int user, int egg, String dockerImage, int memory,
                                    int disk, int cpu, int database, int allocations,
                                    int backups, int port, String mcv){


        String jsonBody = """
{
  "name": "%s",
  "user": %d,
  "egg": %d,
  "docker_image": "%s",
  "startup": "java -Xms128M -Xmx{{SERVER_MEMORY}}M -jar {{SERVER_JARFILE}}",
  "environment": {
    "VANILLA_VERSION": "%s",
    "SERVER_JARFILE": "server.jar"
  },
  "limits": {
    "memory": %d,
    "swap": 0,
    "disk": %d,
    "io": 500,
    "cpu": %d,
    "oom_disabled": false
  },
  "feature_limits": {
    "databases": %d,
    "allocations": %d,
    "backups": %d
  },
  "allocation": {
    "default": %d
  }
}
""";

    String formjson = jsonBody.formatted(name, user, egg, dockerImage,mcv, memory, disk, cpu,database,allocations, backups, port);
        System.out.println(formjson);


            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://10.10.10.83/api/application/servers"))
                    .header("Authorization", "Bearer API_KEY")
                    .header("Accept", "Application/vnd.pterodactyl.v1+json")
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(formjson))
                    .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        }catch(IOException | InterruptedException e){
            System.out.println("Da hat was nicht geklappt!");
            System.out.println(e.getMessage());
        }


    }





}
