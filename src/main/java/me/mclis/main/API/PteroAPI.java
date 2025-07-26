package me.mclis.main.API;

import com.json.JSONObject;
import me.mclis.main.Main;
import me.mclis.main.Util.FileManager;


import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.sql.SQLException;


public class PteroAPI {

    Main main = Main.getInstance();
    String email = "test@test.de";

    FileManager fm = new FileManager("config.yml", main.getDataFolder());



    public void createServerVanilla(String name, int user, int egg, String dockerImage, int memory,
                                    int disk, int cpu, int database, int allocations,
                                    int backups, int port, String mcv){

        MySQLAPI mysql = new MySQLAPI();
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



            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://10.10.10.83/api/application/servers"))
                    .header("Authorization", "Bearer " + fm.toFileConfigurtaion().getString("Util.key"))
                    .header("Accept", "Application/vnd.pterodactyl.v1+json")
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(formjson))
                    .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("respose:");
            System.out.println(response.body().toString());
            JSONObject res = new JSONObject(response.body().toString());

            System.out.println(res.toString());
            String uuid = res.getJSONObject("attributes").getString("uuid");
            int id = res.getJSONObject("attributes").getInt("id");
            String sname = res.getJSONObject("attributes").getString("name");
            String identifier = res.getJSONObject("attributes").getString("identifier");
            boolean running = false;
            try {
                mysql.CreateServer(uuid, sname, id, identifier, running);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }




        }catch(IOException | InterruptedException e){
            System.out.println("Da hat was nicht geklappt!");
            System.out.println(e.getMessage());
        }


    }

    public void checkServerListfromUser(){

    }

    public void checkserverStatus(){

    }

    public void Startserver(){

    }
    public void StopServer(){

    }

    public void CreateUser(String name, String password, String lang){
        main.getLogger().info("test4");
        String jsonBody = """
        {
            "email": "%s",
            "username": "%s",
            "first_name": "%s",
            "last_name": "%s",
            "password": "%s",
            "language": "%s",
            "root_admin": false
        }
        """;
        String formjson = jsonBody.formatted(email, name, name, name, password, lang);
        main.getLogger().info(formjson);




    }







}
