package pro.freeserver.alphakun.plugin.portal.handlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static pro.freeserver.alphakun.plugin.portal.Portal.portalInstance;

public class JsonHandler {

    public static boolean saveJsonFile(String fileName, TeleportHandler teleportHandler) {
        if (Objects.isNull(teleportHandler)) return false;
        if (teleportHandler.teleportSetList.isEmpty()) return false;
        File file = new File(portalInstance.getDataFolder(), fileName + ".json");
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(teleportHandler, writer);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static TeleportHandler readJsonFile(String fileName) {
        File file = new File(portalInstance.getDataFolder(),fileName + ".json");
        try (JsonReader reader = new JsonReader(new FileReader(file))) {
            Gson gson = new Gson();
            return gson.fromJson(reader, TeleportHandler.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}