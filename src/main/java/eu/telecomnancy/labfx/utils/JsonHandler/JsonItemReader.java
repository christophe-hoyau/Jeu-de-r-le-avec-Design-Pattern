package eu.telecomnancy.labfx.utils.JsonHandler;

import eu.telecomnancy.labfx.MaterialService.Material;
import eu.telecomnancy.labfx.MaterialService.MaterialService;
import eu.telecomnancy.labfx.MaterialService.Service;
import eu.telecomnancy.labfx.user.AdminUser;
import eu.telecomnancy.labfx.user.ClassicUser;
import eu.telecomnancy.labfx.user.User;
import eu.telecomnancy.labfx.utils.ItemTuple;
import eu.telecomnancy.labfx.utils.Reservation;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class JsonItemReader implements JsonReader{

    public static ArrayList<MaterialService> read(String resourcePath) {
        ArrayList<MaterialService> items = new ArrayList<>();

        String jsonString = null;

        try {
            jsonString = Files.readString(Paths.get(resourcePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //reads json file and creates users
        System.out.println("Reading items from json: " + resourcePath);
        if (jsonString == null) {
            System.out.println("jsonString is null");
            return items;
        }
        JSONArray array = new JSONArray(jsonString);
        if (!jsonString.equals("[]")) {
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonItem = array.getJSONObject(i);
                MaterialService item = createItemFromJson(jsonItem);
                assert item != null;
                System.out.println(item.getName());
                items.add(item);
                System.out.println(item.getName());
            }
        }
        return items;
    }

    public static MaterialService createItemFromJson(JSONObject jsonItem){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //gets attributes from json

        int id = jsonItem.getInt("id");
        String name = jsonItem.getString("nom");
        String type = jsonItem.getString("type");
        int owner = jsonItem.getInt("owner");
        int price = jsonItem.getInt("prix");
        String description =  jsonItem.getString("description");
        LocalDateTime createdAt = LocalDateTime.parse(jsonItem.getString("createdAt"), formatter);
        LocalDateTime updateAt = LocalDateTime.parse(jsonItem.getString("updatedAt"), formatter);
        LocalDateTime startTime = LocalDateTime.parse(jsonItem.getString("startTime"), formatter);
        LocalDateTime endTime = LocalDateTime.parse(jsonItem.getString("endTime"), formatter);
        String image = jsonItem.getString("image");
        boolean isActive = jsonItem.getBoolean("active");
        JSONArray reserveJson = jsonItem.getJSONArray("reserve");
        ArrayList<Reservation> reserve = getReservations(reserveJson, formatter);
        //creates user
        Service service;
        Material material;
        if (type.equals("service")){
            service = new Service(id, name, owner, price, description, createdAt, updateAt, startTime, endTime, reserve, image, isActive);
            return service;
        }else if (type.equals("materiel")){
            material = new Material(id, name, owner, price, description, createdAt, updateAt, startTime, endTime, reserve, image, isActive);
            return material;
        }else{
            return null;
        }
    }

    private static ArrayList<Reservation> getReservations(JSONArray reserveJson, DateTimeFormatter formatter) {
        ArrayList<Reservation> reserve = new ArrayList<Reservation>();
        for (Object itemReserve : reserveJson) {
            JSONObject jsonItemReserve = (JSONObject) itemReserve;
            int idUser = jsonItemReserve.getInt("user");
            LocalDateTime startRes = LocalDateTime.parse(jsonItemReserve.getString("startRes"), formatter);
            LocalDateTime endRes = LocalDateTime.parse(jsonItemReserve.getString("endRes"), formatter);
            Reservation reservation = new Reservation(startRes, endRes, idUser);
            reserve.add(reservation);
        }
        return reserve;
    }
}
