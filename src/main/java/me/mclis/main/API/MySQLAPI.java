package me.mclis.main.API;

import me.mclis.main.Main;
import me.mclis.main.Util.MySQLFileManager;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.Random;

public class MySQLAPI {
    Main main = Main.getInstance();
    PteroAPI papi = new PteroAPI();
    MySQLFileManager msqlf = new MySQLFileManager("mysql.yml", main.getDataFolder());

    String user = msqlf.toFileConfigurtaion().getString("user");
    String port = msqlf.toFileConfigurtaion().getString("port");
    String pw = msqlf.toFileConfigurtaion().getString("pw");
    String database = msqlf.toFileConfigurtaion().getString("database");
    String host = msqlf.toFileConfigurtaion().getString("host");


    private Connection connection;

    public Connection getConnection() throws SQLException {

        if(connection != null){
            return connection;
        }

        //Try to connect to my MySQL database running locally
        String url = "jdbc:mysql://"+host+"/" + database;


        Connection connection = DriverManager.getConnection(url, user, pw);

        this.connection = connection;

        System.out.println("Connected to database.");

        return connection;
    }


    public void createTables() throws SQLException{
        Statement st = getConnection().createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS users(uuid varchar(36) primary key, name varchar(255), balance int, premium boolean)";
        String sql2 = "CREATE TABLE IF NOT EXISTS servers(uuid varchar(36) primary key, name varchar(255), id int, identifier varchar(255), running boolean)";

        st.execute(sql);
        st.execute(sql2);

        st.close();
    }


    public void CreateUser(Player p) throws SQLException{


        PreparedStatement statement = getConnection()
                .prepareStatement("INSERT INTO users(uuid, name, balance, premium) VALUES (?, ?, ?, ?)");
        statement.setString(1, p.getUniqueId().toString());
        statement.setString(2, p.getName());
        statement.setInt(3, 1000);
        statement.setBoolean(4, false);

        statement.executeUpdate();

        statement.close();



            if (!isSUserPremium(p)) {
                main.getLogger().info("test2");
                papi.CreateUser(p.getName(), RandomPW(), "de");

            } else {
                main.getLogger().info("test2");
                papi.CreateUser(p.getName(), "test1234", "de");
            }

    }

    public String RandomPW(){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();


    }
    public void UpdateUser(){

    }
    public void CreateServer(String uuid, String name, int id, String identifier, boolean running) throws SQLException{
        PreparedStatement statement = getConnection()
                .prepareStatement("INSERT INTO Servers(uuid, name, id, identifier, running) VALUES (?, ?, ?, ?, ?)");
        statement.setString(1, uuid);
        statement.setString(2, name);
        statement.setInt(3, id);
        statement.setString(4, identifier);
        statement.setBoolean(5, running);

        statement.executeUpdate();

        statement.close();
    }
    public void StartServer(){

    }
    public void StopServer(){

    }
    public void UpdateServer(){

    }
    public boolean isUserregistered(Player p) throws SQLException{
        createTables();
        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM users WHERE uuid = ?");
        statement.setString(1, p.getUniqueId().toString());

        ResultSet resultSet = statement.executeQuery();
        if(!resultSet.next()){
            CreateUser(p);
            main.getLogger().info("User: " + p.getName() + " not found creating!");
            return false;
        }else{
            main.getLogger().info("User: " + p.getName() + " found");
            return true;
        }




    }
    public boolean isServerRunning(){
        return false;
    }

    public boolean isSUserPremium(Player p) throws  SQLException{

        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM users WHERE uuid = ?");
        statement.setString(1, p.getUniqueId().toString());

        ResultSet resultSet = statement.executeQuery();
        if(!resultSet.next()){

            main.getLogger().info("User: " + p.getName() + " not found creating!");
            CreateUser(p);
            return false;
        }else{
            return resultSet.getBoolean("premium");

        }

    }




}
