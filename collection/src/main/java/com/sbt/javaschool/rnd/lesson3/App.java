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

        System.out.println("Task 3");
        System.out.println(getFreqOfWords(str).toString());

        System.out.println("Task 4");
        System.out.println(getStrLineInReverse(str));

        System.out.println("Task 5");
        MyList list = new MyList();
        list.addAll(getWords(str));
        System.out.println("Normal:");
        list.forEach((String word) -> System.out.print(String.format("%s ", word)));
        System.out.println();
        System.out.println("Reverse:");
        for (String word : list) {
            System.out.print(String.format("%s ", word));
        }
        System.out.println();


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
        TreeSet<String> sortedWords = new TreeSet<String>(new StringLengthComparator());
        sortedWords.addAll(getWords(str));
        return sortedWords;
    }

    private static Map<String, Integer> getFreqOfWords(String str) {
        Map<String, Integer> freqOccurrenceOfWords = new HashMap<String, Integer>();
        for (String word : getWords(str)) {
            freqOccurrenceOfWords.put(word, (freqOccurrenceOfWords.containsKey(word)) ? freqOccurrenceOfWords.get(word) + 1 : 1);
        }
        return freqOccurrenceOfWords;
    }

    private static String getStrLineInReverse(String str) {
        StringBuilder reverseStr = new StringBuilder();
        Deque<String> stack = new LinkedList<String>();
        for (String strLine : str.split("\n")) {
            stack.push(String.format("%s\n", strLine));
        }
        stack.forEach((String strLine) -> reverseStr.append(strLine));
        return reverseStr.toString();
    }
}