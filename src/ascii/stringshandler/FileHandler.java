package ascii.stringshandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileHandler {

    /**
     * @param list1 string list where is needed to find unique to list2 values
     * @param list2 string list which values are needed to exclude from list1
     * @return strings which were found in list1 but not in list2
     */
    public static List<String> uniqueFirst(List<String> list1, List<String> list2) {
        return list1 == null || list2 == null ? new ArrayList<>() :
                list1.stream()
                        .filter(elem -> !list2.contains(elem))
                        .toList();
    }

    /**
     * @param list strings list that are needed to write in file
     * @param path path to file for writing
     */
    private static void writeOutput(List<String> list, String path) {
        try {
            FileWriter myWriter = new FileWriter(path);
            myWriter.write(String.join(System.lineSeparator(), list));
            myWriter.close();
            System.out.println(MessageFormat.format("Successfully wrote to the file {0}.", path));
        } catch (IOException e) {
            System.out.println("The file error occurred. Please, ensure that file {0} is not in use by another service.");
        }
    }

    /**
     * @param input ASCII string from a file
     * @return strings list from ASCII codes
     */
    public static List<String> readASCII(String input) {
        if (input == null || input.isEmpty() || input.isBlank()) return List.of();
        var codes = Arrays.stream(input.split("\s+")).map(Integer::parseInt).toList();
        var str = new StringBuilder();
        for (int i : codes) {
            str.append((char) i);
        }
        // There is only one line feed in ASCII (code 10) so there is no need for considering platform-dependent separators
        return Arrays.stream(str.toString().split("\n")).toList();
    }

    /**
     * @param path path to a file with ASCII strings
     * @return ASCII strings list
     */
    private static List<String> readInput(String path) {
        var list = new ArrayList<String>();
        try {
            var file = new File(path);
            var scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                var data = readASCII(scanner.nextLine());
                list.addAll(data);
            }
            System.out.println("File data: ");
            show(list);
            System.out.println("Successfully read from the file.");
        } catch (IOException e) {
            System.out.println(MessageFormat.format(
                    "The file error occurred. Please, ensure that file {0} exists and is not in use by another service.",
                    path));
        }
        return list;
    }


    /**
     * @param list strings list
     */
    public static void show(List<String> list) {
        list.forEach(System.out::println);
    }

    /**
     * @param args args from the command line
     * @return True if there are 4 args (paths to files) and False otherwise
     */
    private static boolean isCorrectInput(String[] args) {
        return args.length == 4;
    }

    /**
     * @param args Console input args
     */
    private static void run(String[] args) {
        if (isCorrectInput(args)) {
            var list1 = readInput(args[0]);
            var list2 = readInput(args[1]);
            var result1 = uniqueFirst(list1, list2);
            System.out.println("Strings that are contained in 1st file but not in 2nd file:");
            show(result1);
            var result2 = uniqueFirst(list2, list1);
            System.out.println("Strings that are contained in 2nd file but not in 1st file:");
            show(result2);
            writeOutput(result1, args[2]);
            writeOutput(result2, args[3]);
            System.out.println("Process has finished.");
        } else System.out.println("There is incorrect input: you should pass two input path for files with" +
                " ASCII strings and two output path for outputs. Also, please, ensure there is no spaces in paths you have passed.");
    }

    public static void main(String[] args) {
        run(args);
    }
}
