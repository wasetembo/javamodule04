import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CountKeywords {

    public static void main(String[] args) {
        // Ensure a filename is passed as a command-line argument
        if (args.length != 1) {
            System.out.println("Usage: java CountKeywords <filename>");
            System.exit(1);
        }

        String filename = args[0];

        // Set of Java keywords
        Set<String> keywords = new HashSet<>(Arrays.asList(
            "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", 
            "const", "continue", "default", "do", "double", "else", "enum", "extends", "final", 
            "finally", "float", "for", "goto", "if", "implements", "import", "instanceof", 
            "int", "interface", "long", "native", "new", "package", "private", "protected", 
            "public", "return", "short", "static", "strictfp", "super", "switch", "synchronized", 
            "this", "throw", "throws", "transient", "try", "void", "volatile", "while"
        ));

        int keywordCount = 0;

        try (Scanner scanner = new Scanner(new File(filename))) {
            boolean inBlockComment = false; // Track multi-line comments

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                // Skip single-line comments
                if (line.startsWith("//")) {
                    continue;
                }

                // Handle multi-line comments
                if (inBlockComment) {
                    if (line.contains("*/")) {
                        inBlockComment = false; // End of multi-line comment
                    }
                    continue;
                }

                if (line.contains("/*")) {
                    inBlockComment = true; // Start of multi-line comment
                    continue;
                }

                // Remove strings from the line
                line = removeStrings(line);

                // Split the line into words and check for keywords
                String[] tokens = line.split("\\W+");
                for (String token : tokens) {
                    if (keywords.contains(token)) {
                        keywordCount++;
                    }
                }
            }

            System.out.println("The number of keywords in the file is: " + keywordCount);

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }

    /**
     * Removes all strings enclosed in double quotes from the input line.
     */
    private static String removeStrings(String line) {
        StringBuilder result = new StringBuilder();
        boolean inString = false;

        for (char c : line.toCharArray()) {
            if (c == '"') {
                inString = !inString; // Toggle inString state
            } else if (!inString) {
                result.append(c);
            }
        }

        return result.toString();
    }
}
