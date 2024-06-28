package eu.telecomnancy.labfx.utils.JsonHandler;

import eu.telecomnancy.labfx.MaterialService.Material;
import eu.telecomnancy.labfx.MaterialService.MaterialService;
import eu.telecomnancy.labfx.MaterialService.Service;
import eu.telecomnancy.labfx.user.User;
import eu.telecomnancy.labfx.utils.ItemTuple;
import eu.telecomnancy.labfx.utils.Reservation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;

public class JsonItemWritter implements JsonWritter{
    public static void write(String resourcePath, ArrayList<Service> services, ArrayList<Material> materials) {
        //writes items in json file
        System.out.println("Writing items in json: " + resourcePath);
        ArrayList<MaterialService> items = new ArrayList<MaterialService>();
        items.addAll(services);
        items.addAll(materials);
        items.sort(Comparator.comparing(MaterialService::getId));


        StringBuilder jsonBuilder = new StringBuilder("[\n");
        for (MaterialService item1 : items) {
            System.out.println(item1.getId());

            if (item1 instanceof Material){
                jsonBuilder.append(createOneItemJson((Material) item1));
            }else{
                Service item = (Service)item1;
                jsonBuilder.append(createOneItemJson(item));
            }
            jsonBuilder.append(",\n");

        }
        jsonBuilder.deleteCharAt(jsonBuilder.lastIndexOf(","));
        jsonBuilder.append("]");
        String jsonString = jsonBuilder.toString();
        System.out.println("Writing items in json: " + resourcePath);
        try {
            Files.write(Paths.get(resourcePath), jsonString.getBytes());

            System.out.println("Content written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String createOneItemJson(Material item){
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("\t{\n")
                .append("\t\t\"id\": ").append(item.getId()).append(",\n")
                .append("\t\t\"nom\": \"").append(item.getName()).append("\",\n")
                .append("\t\t\"type\": \"").append(item.getType()).append("\",\n")
                .append("\t\t\"owner\": ").append(item.getOwner()).append(",\n")
                .append("\t\t\"prix\": \"").append(item.getCost()).append("\",\n")
                .append("\t\t\"description\": \"").append(item.getDescription()).append("\",\n");

        String createdAt = formatLocalTime(item.getCreatedAt().toString());
        String updatedAt = formatLocalTime(item.getUpdatedAt().toString());
        String startTime = formatLocalTime(item.getStartTime().toString());
        String endTime = formatLocalTime(item.getEndTime().toString());
        jsonBuilder.append("\t\t\"createdAt\": \"").append(createdAt).append("\",\n")
                .append("\t\t\"updatedAt\": \"").append(updatedAt).append("\",\n")
                .append("\t\t\"startTime\": \"").append(startTime).append("\",\n")
                .append("\t\t\"endTime\": \"").append(endTime).append("\",\n");
        if (item.getReservationDelays() == null) {
            jsonBuilder.append("\t\t\"reserve\": [],\n");
        } else {
            jsonBuilder.append("\t\t\"reserve\": ").append(formatReserve(item.getReservationDelays())).append(",\n");
        }
        jsonBuilder.append("\t\t\"image\": \"").append(item.getImage()).append("\",\n")
                .append("\t\t\"active\": ").append(item.isActive()).append("\n")
                .append("\t}\n");
        return jsonBuilder.toString();
    }

    private static String createOneItemJson(Service item){
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("\t{\n")
                .append("\t\t\"id\": ").append(item.getId()).append(",\n")
                .append("\t\t\"nom\": \"").append(item.getName()).append("\",\n")
                .append("\t\t\"type\": \"").append(item.getType()).append("\",\n")
                .append("\t\t\"owner\": ").append(item.getOwner()).append(",\n")
                .append("\t\t\"prix\": \"").append(item.getCost()).append("\",\n")
                .append("\t\t\"description\": \"").append(item.getDescription()).append("\",\n");

        String createdAt = formatLocalTime(item.getCreatedAt().toString());
        String updatedAt = formatLocalTime(item.getUpdatedAt().toString());
        String startTime = formatLocalTime(item.getStartTime().toString());
        String endTime = formatLocalTime(item.getEndTime().toString());
        jsonBuilder.append("\t\t\"createdAt\": \"").append(createdAt).append("\",\n")
                .append("\t\t\"updatedAt\": \"").append(updatedAt).append("\",\n")
                .append("\t\t\"startTime\": \"").append(startTime).append("\",\n")
                .append("\t\t\"endTime\": \"").append(endTime).append("\",\n");
        if (item.getReservationDelays() == null) {
            jsonBuilder.append("\t\t\"reserve\": [],\n");
        } else {
            jsonBuilder.append("\t\t\"reserve\": ").append(formatReserve(item.getReservationDelays())).append(",\n");
        }
        jsonBuilder.append("\t\t\"image\": \"").append(item.getImage()).append("\",\n")
                .append("\t\t\"active\": ").append(item.isActive()).append("\n")
                .append("\t}\n");
        return jsonBuilder.toString();
    }

    private static String formatLocalTime(String time){
        time = time.replace("T", " ");
        if (time.contains(".")) {
            time = time.substring(0, time.lastIndexOf("."));
        }
        else if (time.indexOf(":") == time.lastIndexOf(":")){
            time = time.concat(":00");
        }
        return time;
    }

    private static String formatReserve(ArrayList<Reservation> reservations){
        StringBuilder reserveBuilder = new StringBuilder("[");
        for (Reservation reservation : reservations) {
            reserveBuilder.append("{");
            reserveBuilder.append("\"startRes\": \"").append(formatLocalTime(reservation.getStartRes().toString()))
                    .append("\", \"endRes\": \"").append(formatLocalTime(reservation.getEndRes().toString()))
                    .append("\", \"user\": ").append(reservation.getUserId())
                    .append("},\n");
        }
        if (reserveBuilder.length() > 1) {
            reserveBuilder.deleteCharAt(reserveBuilder.lastIndexOf(","));
            reserveBuilder.deleteCharAt(reserveBuilder.lastIndexOf("\n"));
        }
        reserveBuilder.append("]");
        return reserveBuilder.toString();
    }
}

