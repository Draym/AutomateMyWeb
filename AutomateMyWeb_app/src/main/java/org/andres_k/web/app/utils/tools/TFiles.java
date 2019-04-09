package org.andres_k.web.app.utils.tools;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TFiles {

    /**
     * RESOURCES
     */
    public static URL getResource(String fileName) {
        return TFiles.class.getResource((fileName.indexOf("/") == 0 ? "" : "/") + fileName);
    }

    public static InputStream getResourceAsStream(String fileName) {
        return TFiles.class.getResourceAsStream((fileName.indexOf("/") == 0 ? "" : "/") + fileName);
    }

    /**
     * STREAM
     */
    public static OutputStream getFileOutput(String fileName) throws FileNotFoundException {
        return new FileOutputStream(fileName);
    }

    public static InputStream getFileInput(String fileName) throws FileNotFoundException {
        return new FileInputStream(fileName);
    }

    /**
     * READ
     */
    public static String readInput(InputStream inputStream) {
        if (inputStream == null)
            return null;
        Scanner scan = new Scanner(inputStream).useDelimiter("\\A");

        return (scan.hasNext() ? scan.next() : null);
    }


    public static String readOut(String filePath) {
        File file = new File(filePath);

        if (!file.exists()) {
            ClassLoader classLoader = TFiles.class.getClassLoader();
            return TFiles.readInput(classLoader.getResourceAsStream(filePath));
        } else {
            return TFiles.readFile(file);
        }
    }

    public static String readFile(File file) {
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

    /**
     * WRITE
     */
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

    /**
     * REMOVE
     */
    public static boolean remove(String path) {
        File dir = new File(path);

        if(!dir.isDirectory()) {
            return dir.delete();
        }
        File[] listFiles = dir.listFiles();
        if (listFiles != null) {
            for(File file : listFiles){
                file.delete();
            }
        }
        return dir.delete();
    }

    /**
     * UTILS
     */
    private static void createParents(String fileName) {
        fileName = fileName.substring(0, fileName.lastIndexOf("/"));
        File file = new File(fileName);

        file.mkdirs();
    }

    public static List<String> getFilesNameIn(String folderPath) throws FileNotFoundException {
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();
        List<String> filesName = new ArrayList<>();

        if (listOfFiles == null)
            throw new FileNotFoundException();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                filesName.add(listOfFiles[i].getName());
            }
        }
        return filesName;
    }

}
