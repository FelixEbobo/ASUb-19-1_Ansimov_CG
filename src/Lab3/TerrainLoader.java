package Lab3;

import java.io.*;

public class TerrainLoader {
    private static String path = System.getProperty("user.dir") + "/Media/";

    public static byte[] load(String fileName, int mapSize) {
        byte pHeightMap[] = new byte[mapSize * mapSize];
        FileInputStream input = null;
        try {
            input = new FileInputStream(path + fileName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            input.read(pHeightMap, 0, mapSize * mapSize);
            input.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        for (int i = 0; i < pHeightMap.length; i++)
            pHeightMap[i] &= 0xFF;
        return pHeightMap;
    }
}
