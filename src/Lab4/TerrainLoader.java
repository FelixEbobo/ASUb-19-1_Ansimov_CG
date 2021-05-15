package Lab4;

import java.io.*;

public class TerrainLoader {
    private static String path = System.getProperty("user.dir") + "/Media/";

    public static byte[] load(String fileName, int mapSize) {
        byte pHeightMap[] = new byte[mapSize * mapSize];
        float heightMap[][] = new float[mapSize][mapSize];
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
        for (int i = 0; i < pHeightMap.length; i++) {
            pHeightMap[i] &= 0xFF;

        }
        return pHeightMap;
    }

    public static float[][] createForestMap(int count, int mapSize, byte[] heightMap) {
        float[][] t = new float[3][count];

        int i;
        int j;

        for (int k = 0; k < count; k++) {
            i = (int) (Math.random() * mapSize);
            j = (int) (Math.random() * mapSize);

            t[0][k] = (float) (i - 0.5);
            t[1][k] = (float) (heightMap[i]);
            t[2][k] = (float) (j - 0.5);
        }
        return t;
    }
}
