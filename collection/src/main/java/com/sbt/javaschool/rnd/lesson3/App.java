package com.sbt.javaschool.rnd.lesson3;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {

    public static void main(String[] args) throws IOException {
        Path resourcesPath = Paths.get("collection/resources");
        File file = new File(resourcesPath.toAbsolutePath().toString() + "/text.txt");
        String str = fileToStr(file);

        System.out.println("Task 1");
        System.out.println(String.format("Count of unique words - %d\n", countOfUniqueWords(str)));

        System.out.println("Task 2");
        System.out.println(getSortedWords(str).toString());
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

    private static Collection<String> getWords(String str) {
        List<String> words = new LinkedList<String>();
        Pattern patternWord = Pattern.compile("\\w+");
        Matcher matcher = patternWord.matcher(str);
        while (matcher.find()) {
            words.add(matcher.group());
        }
        return words;
    }

    private static int countOfUniqueWords(String str){
        Set<String> uniqueWords = new HashSet<String>(getWords(str));
        return uniqueWords.size();
    }

    private static TreeSet<String> getSortedWords(String str) {
        TreeSet<String> sortedWords = new TreeSet<String>(new StringthLengthComparator());
        sortedWords.addAll(getWords(str));
        return sortedWords;
    }
}
