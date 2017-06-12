package com.aconex.io;

import java.io.*;

public class FileHelper {
    public static Reader getReaderFromFile(String path){
        try {
            return new FileReader(path);
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file : " + path);
        }
        return null;
    }

    public static void writeStringToFile(String filePath, String content){
        File file = new File(filePath);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            if (!file.exists()) {
                file.createNewFile();
            }
            byte[] contentInBytes = content.getBytes();
            fos.write(contentInBytes);
            fos.flush();
            fos.close();

        } catch (IOException e) {
            System.out.println("Unable to write to file : " + filePath + e);
        }
    }

    public static void createFolderIfNotExist(String name){
        File folder = new File(name);

        if (!folder.exists()) {
            try{
                folder.mkdir();
            }
            catch(SecurityException e){
                System.out.println("Unable to create folder : " + name);
            }
        }
    }
}
