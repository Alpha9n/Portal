package pro.freeserver.alphakun.plugin.portal.handlers;

import pro.freeserver.alphakun.plugin.portal.models.TeleportSet;

import java.util.ArrayList;

public class JsonHandler<T> {
    public JsonHandler() {
    }

    public void saveJsonFile(String fileName, T targetData) {

    }

    public T readJsonFile(String fileName) {
        return null;
    }

    public static void saveTeleportSet(ArrayList<TeleportSet> teleportSet) {
        var jsonHandler = new JsonHandler<ArrayList<TeleportSet>>();
        jsonHandler.saveJsonFile("teleportSpot", teleportSet);
    }
}
