package me.mclis.main.Util;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MySQLFileManager {

    private FileConfiguration fileConfiguration;
    private File file;
    public MySQLFileManager(String name, File path){
        file = new File(path, name);

        if(!file.exists()){
            path.mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        fileConfiguration = new YamlConfiguration();
        try {
            fileConfiguration.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public File getFile(){
        return file;
    }

    public FileConfiguration toFileConfigurtaion(){
        return fileConfiguration;
    }

    public void save(){
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void reload(){
        try {
            fileConfiguration.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}
