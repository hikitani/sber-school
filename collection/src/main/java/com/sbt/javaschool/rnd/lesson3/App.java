package com.sbt.javaschool.rnd.lesson3;

import org.omg.CORBA.Environment;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {

    public static void main(String[] args) throws IOException {
        Path resourcesPath = Paths.get("collection/resources");
        File file = new File(resourcesPath.toAbsolutePath().toString() + "/text.txt");
        String str = fileToStr(file);

    }

    private static String fileToStr(File file) throws IOException{
        StringBuilder res = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            int i = reader.read();
            while (i != -1) {
                res.append((char)i);
                i = reader.read();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        return res.toString();
    }

    private static int countOfUniqueWords(String str){
        throw new NotImplementedException();
    }
}
