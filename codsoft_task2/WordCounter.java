import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class WordCounter {
    private static final Set<String> STOP_WORDS = new HashSet<>(Arrays.asList("a", "an", "the", "is", "of", "and", "in", "to", "on", "for", "with", "it", "this", "that"));

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Word Counter");

        String inputText;
        do {
            System.out.print("Enter text or provide the file path: ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Invalid input. Please try again.");
                continue;
            }

            if (input.startsWith("file:")) {
                String filePath = input.substring(5).trim();
                try {
                    inputText = readFromFile(filePath);
                } catch (FileNotFoundException e) {
                    System.out.println("File not found. Please try again.");
                    continue;
                }
            } else {
                inputText = input;
            }

            break;
        } while (true);

        int wordCount = countWords(inputText);
        System.out.println("Total word count: " + wordCount);

        Map<String, Integer> wordFrequency = countWordFrequency(inputText);
        System.out.println("Unique word count: " + wordFrequency.size());

        // Displaying the frequency of each word
        System.out.println("Word frequency:");
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        scanner.close();
    }

    private static String readFromFile(String filePath) throws FileNotFoundException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                contentBuilder.append(scanner.nextLine());
            }
        }
        return contentBuilder.toString();
    }

    private static int countWords(String inputText) {
        String[] words = inputText.split("\\s+|\\p{Punct}+");
        return words.length;
    }

    private static Map<String, Integer> countWordFrequency(String inputText) {
        String[] words = inputText.split("\\s+|\\p{Punct}+");
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            String cleanedWord = word.toLowerCase();
            if (!STOP_WORDS.contains(cleanedWord)) {
                wordFrequency.put(cleanedWord, wordFrequency.getOrDefault(cleanedWord, 0) + 1);
            }
        }
        return wordFrequency;
    }
}
