package eu.telecomnancy.labfx.utils.JsonHandler;

import eu.telecomnancy.labfx.user.User;
import eu.telecomnancy.labfx.utils.DirectoryHandler;
import eu.telecomnancy.labfx.utils.Evaluation;
import eu.telecomnancy.labfx.utils.ItemTuple;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;

public class JsonUserWritter implements JsonWritter{
    public static void write(String resourcePath, ArrayList<User> users) {
        //writes users in json file
        users.sort(Comparator.comparing(User::getId));
        StringBuilder jsonBuilder = new StringBuilder("[\n");
        for (User user : users) {
            jsonBuilder.append("\t")
                    .append(createJsonFromUser(user))
                    .append("\t,\n");
        }
        jsonBuilder.deleteCharAt(jsonBuilder.lastIndexOf(","));
        jsonBuilder.append("]");
        String jsonString = jsonBuilder.toString();
        System.out.println("Writing users in json: " + resourcePath);
        try {
            Files.write(Paths.get(resourcePath), jsonString.getBytes());

            System.out.println("Content written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static String createJsonFromUser(User user) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("\t{\n")
                .append("\t\t\"id\": ").append(user.getId()).append(",\n")
                .append("\t\t\"identifiant\": \"").append(user.getIdentifiant()).append("\",\n")
                .append("\t\t\"password\": \"").append(user.getPassword()).append("\",\n")
                .append("\t\t\"firstName\": \"").append(user.getFirstName()).append("\",\n")
                .append("\t\t\"lastName\": \"").append(user.getLastName()).append("\",\n")
                .append("\t\t\"email\": \"").append(user.getEmail()).append("\",\n")
                .append("\t\t\"ville\": \"").append(user.getCity()).append("\",\n")
                .append("\t\t\"role\": \"").append(user.getRole()).append("\",\n")
                .append("\t\t\"florains\": ").append(user.getFlorains()).append(",\n")
                .append("\t\t\"createdAt\": \"").append(formatLocalTime(user.getCreatedAt().toString())).append("\",\n")
                .append("\t\t\"image\": \"").append(user.getImage()).append("\",\n")
                .append("\t\t\"description\": \"").append(user.getDescription()).append("\",\n")
                .append("\t\t\"itemsOwn\": ").append(formatIntListJson(user.getItemsOwned())).append(",\n")
                .append("\t\t\"favorite\": ").append(formatIntListJson(user.getFavouriteItems())).append(",\n")
                .append("\t\t\"ratings\": ").append(formatRatingJson(user.getEvaluations())).append(",\n")
                .append("\t\t\"itemsSell\": ").append(formatItemTupleListJson(user.getItemsSell())).append(",\n")
                .append("\t\t\"itemsBuy\": ").append(formatItemTupleListJson(user.getItemsBuy())).append("\n");
        jsonBuilder.append("\t}");
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

    private static String formatIntListJson(ArrayList<Integer> list) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");
        if (list == null) {
            jsonBuilder.append("]");
        } else if (list.isEmpty()) {
            jsonBuilder.append("]");
        } else {
            for (Integer integer : list) {
                jsonBuilder.append(integer).append(",");
            }
            jsonBuilder.deleteCharAt(jsonBuilder.lastIndexOf(","));
            jsonBuilder.append("]");
        }
        return jsonBuilder.toString();
    }
    private static String formatRatingJson(ArrayList<Evaluation> evaluations) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");
        if (evaluations == null) {
            jsonBuilder.append("]");
        } else if (evaluations.isEmpty()) {
            jsonBuilder.append("]");
        } else {
            jsonBuilder.append("\n");
            for (Evaluation evaluation : evaluations) {
                jsonBuilder.append("\t\t\t{")
                        .append("\"id\": ").append(evaluation.getId()).append(", ")
                        .append("\"rating\": ").append(evaluation.getRating()).append(", ")
                        .append("\"comment\": \"").append(evaluation.getComment()).append("\", ")
                        .append("\"createdAt\": \"").append(formatLocalTime(evaluation.getCreatedAt().toString())).append("\", ")
                        .append("\"user\": ").append(evaluation.getIdUser()).append("},\n");

            }
            jsonBuilder.deleteCharAt(jsonBuilder.lastIndexOf(","));
            jsonBuilder.append("\t\t]");
        }
        return jsonBuilder.toString();
    }

    private static String formatItemTupleListJson(ArrayList<ItemTuple> itemTuples) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");
        if (itemTuples == null) {
            jsonBuilder.append("]");
        } else if (itemTuples.isEmpty()) {
            jsonBuilder.append("]");
        } else {
            jsonBuilder.append("\n");
            for (ItemTuple itemTuple : itemTuples) {
                jsonBuilder.append("\t\t\t{")
                        .append("\"id\": ").append(itemTuple.getId()).append(", ")
                        .append("\"transactionTime\": \"").append(formatLocalTime(itemTuple.getTansactionTime().toString())).append("\"")
                        .append("},\n");
            }
            jsonBuilder.deleteCharAt(jsonBuilder.lastIndexOf(","));
            jsonBuilder.append("\t\t]");
        }
        return jsonBuilder.toString();
    }
}

