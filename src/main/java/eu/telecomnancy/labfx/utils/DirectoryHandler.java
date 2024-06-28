package eu.telecomnancy.labfx.utils;

import eu.telecomnancy.labfx.MaterialService.MaterialService;
import eu.telecomnancy.labfx.user.User;
import eu.telecomnancy.labfx.user.UserController;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class DirectoryHandler {
    private static String pathHead = System.getProperty("user.home") + "/MyResources";






    public static void setPathHead() {
        DirectoryHandler.pathHead = System.getProperty("user.home") + "/MyResources";
    }

    public static String getPathHead() {
        return pathHead;
    }

    public static String getPathResources(String path) {
        return pathHead + path;
    }
    public static void generateFolder() throws IOException {
        new File(pathHead).mkdir();
        new File(pathHead+"/data").mkdir();
        new File(pathHead+"/images").mkdir();
        new File(pathHead+"/images/item").mkdir();
        new File(pathHead+"/images/users").mkdir();
        importInitialData(pathHead);
    }

    public static void importInitialData(String destination)  {
        Path pathData = null;
        String json = null;
        try {
           InputStream is = DirectoryHandler.class.getResourceAsStream("/eu/telecomnancy/labfx/data/user.json");
           assert is != null;
           try (InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr)){
               StringBuilder sb = new StringBuilder();
               String line;
                while ((line =br.readLine())!= null) {
                     sb.append(line);
                }
                json = sb.toString();
           }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //save to destination/data/user.json
        try {
            pathData = Files.writeString(Paths.get(destination+"/data/user.json"), json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        pathData = null;
        json = null;
        try {
            InputStream is = DirectoryHandler.class.getResourceAsStream("/eu/telecomnancy/labfx/data/item.json");
            assert is != null;
            try (InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                 BufferedReader br = new BufferedReader(isr)){
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line =br.readLine())!= null) {
                    sb.append(line);
                }
                json = sb.toString();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //save to destination/data/user.json
        try {
            pathData = Files.writeString(Paths.get(destination+"/data/item.json"), json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        pathData = null;
        json = null;
        try {
            InputStream is = DirectoryHandler.class.getResourceAsStream("/eu/telecomnancy/labfx/data/chat.json");
            assert is != null;
            try (InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                 BufferedReader br = new BufferedReader(isr)){
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line =br.readLine())!= null) {
                    sb.append(line);
                }
                json = sb.toString();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //save to destination/data/user.json
        try {
            pathData = Files.writeString(Paths.get(destination+"/data/chat.json"), json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public static void saveNewUserImage(User user,String source){
        String extension = source.substring(source.lastIndexOf("."));
        //save image to path
        String destination = pathHead + "/images/users/" + user.getId() + extension;
        try {
            Files.copy(Paths.get(source), Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
            user.setImage(user.getId() + extension);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveNewMaterialImage(MaterialService material,String source){
        //save image to path
        String extension = source.substring(source.lastIndexOf("."));
        String destination = pathHead + "/images/item/" + material.getId() + extension;
        try {
            System.out.println("Source"+source);
            System.out.println("destination : "+destination);
            Files.copy(Paths.get(source), Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
            material.setImage(material.getId() + extension);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveNewServiceImage(MaterialService service,String source){
        //save image to path
        if (source == null){
            return;
        }
        String extension = source.substring(source.lastIndexOf("."));
        String tail = source.substring(source.lastIndexOf("/"));
        String destination = pathHead + "/images/item/" + service.getId() + extension;
        try {
            File file = new File(destination);
            System.out.println("file : "+file);
            if (!file.exists()) {
                file.createNewFile();
            }
            System.out.println("Source"+source);
            System.out.println("destination : "+destination);
            Files.copy(Paths.get(source), Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
            service.setImage(service.getId() + extension);
            System.out.println("service.getImage() : "+service.getImage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getUserImagePathStored(User user){
        return pathHead + "/images/users/" + user.getImage();
    }

    public static String getMaterialImagePathStored(MaterialService material){
        return pathHead + "/images/item/" + material.getImage();
    }

    public static String getServiceImagePathStored(MaterialService service){
        return pathHead + "/images/item/" + service.getImage();
    }




}
