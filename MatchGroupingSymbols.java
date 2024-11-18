import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class MatchGroupingSymbols {

    public static void main(String[] args) {
        // Ensure a filename is passed as a command-line argument
        if (args.length != 1) {
            System.out.println("Usage: java MatchGroupingSymbols <filename>");
            System.exit(1);
        }

        String filename = args[0];

        try (Scanner scanner = new Scanner(new File(filename))) {
            // Call the method to check grouping symbols
            if (checkGroupingSymbols(scanner)) {
                System.out.println("The file contains properly matched grouping symbols.");
            } else {
                System.out.println("The file contains mismatched grouping symbols.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }

    public static boolean checkGroupingSymbols(Scanner scanner) {
        Stack<Character> stack = new Stack<>();

        // Read file line by line
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            // Iterate through each character in the line
            for (char ch : line.toCharArray()) {
                if (ch == '(' || ch == '{' || ch == '[') {
                    stack.push(ch); // Push opening symbols onto the stack
                } else if (ch == ')' || ch == '}' || ch == ']') {
                    // Check if the stack is empty or symbols don't match
                    if (stack.isEmpty() || !isMatchingPair(stack.pop(), ch)) {
                        return false; // Mismatched symbol
                    }
                }
            }
        }

        // Check if any unmatched symbols remain in the stack
        return stack.isEmpty();
    }

    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '{' && close == '}') ||
               (open == '[' && close == ']');
    }
}
