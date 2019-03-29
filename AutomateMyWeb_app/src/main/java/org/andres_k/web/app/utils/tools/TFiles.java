package org.andres_k.web.app.utils.tools;

import java.io.*;
import java.net.URL;
import java.util.Scanner;

public class TFiles {
    public static URL getResource(String fileName) {
        return TFiles.class.getResource((fileName.indexOf("/") == 0 ? "" : "/") + fileName);
    }

    public static InputStream getResourceAsStream(String fileName) {
        return TFiles.class.getResourceAsStream((fileName.indexOf("/") == 0 ? "" : "/") + fileName);
    }

    public static String readInput(InputStream inputStream) {
        if (inputStream == null)
            return null;
        Scanner scan = new Scanner(inputStream).useDelimiter("\\A");

        return (scan.hasNext() ? scan.next() : "");
    }


    public static String readOut(String filePath) {
        File file = new File(filePath);

        if (!file.exists()) {
            ClassLoader classLoader = TFiles.class.getClassLoader();
            return TFiles.readInput(classLoader.getResourceAsStream(filePath));
        } else {
            return TFiles.parseFile(file);
        }
    }

    public static void writeIn(String filePath, String value) {
        Console.log("Write " + value + " in " + filePath);
        File file = new File(filePath);
        if (!file.exists())
            createParents(filePath);
        try {
            file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(value);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String parseFile(File file) {
        StringBuilder content = new StringBuilder();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    private static void createParents(String fileName) {
        fileName = fileName.substring(0, fileName.lastIndexOf("/"));
        File file = new File(fileName);

        file.mkdirs();
    }

}
