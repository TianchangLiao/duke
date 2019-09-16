import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.io.*;
import java.text.ParseException;
import java.util.*;

public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<Task>();

    private static void writeToFile() throws IOException {
        int l = tasks.size();
        String[] text = new String[l];
        for (int i = 0; i < l; i++) {
            text[i] = tasks.get(i).toString();
        }
        Path file = Paths.get("data/duke.txt");
        List<String> lines = Arrays.asList(text);
        Files.write(file, lines, StandardCharsets.UTF_8);
    }

    private static void readFile() throws IOException, ParseException {
        File file = new File("data/duke.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line, description;
        while ((line = br.readLine()) != null) {
            int startindex = line.lastIndexOf("]") + 2;
            int endindex = line.lastIndexOf("(") - 1;
            Task temp = null;
            if (line.contains("[T]")) {
                description = line.substring(startindex);
                temp = new ToDo(description);
            } else if (line.contains("[D]")) {
                description = line.substring(startindex, endindex);
                String by = line.substring(line.lastIndexOf("(by:") + 5, line.lastIndexOf(")"));
                temp = new Deadline(description, by);
            } else if (line.contains("[E]")) {
                description = line.substring(startindex, endindex);
                String at = line.substring(line.lastIndexOf("(at:") + 5, line.lastIndexOf(")"));
                temp = new Event(description, at);
            }
            if (temp != null && line.contains("\u2713")) {
                temp.markAsDone();
            }
            tasks.add(temp);
        }
    }

    private static void addToList(String input) throws DukeException {
        String[] input2 = input.split(" ", 2);
        String first = input2[0];
        String second = null;
        try {
            second = input2[1];
        } catch (Exception e) {
            throw new DukeException(input);
        }
        try {
            if (first.equals("todo")) {
                ToDo temp = new ToDo(second);
                tasks.add(temp);
                System.out.println("Got it. I've added this task:\n"
                        + temp.toString() + "\nNow you have " + tasks.size()
                        + " in the list.");
            } else if (first.equals("done")) {
                int temp = Integer.parseInt(second) - 1;
                tasks.get(temp).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n"
                        + tasks.get(temp).toString());
            } else if (first.equals("delete")) {
                int temp = Integer.parseInt(second) - 1;
                System.out.println("Noted. I've removed this task:\n"
                        + tasks.get(temp).toString() + "\nNow you have " + (tasks.size() - 1)
                        + " in the list.");
                tasks.remove(temp);
            } else {
                String[] input3 = second.split("/", 2);
                String task = input3[0].trim();
                String date = input3[1].substring(3);
                if (first.equals("deadline")) {
                    Deadline deadline = new Deadline(task, date);
                    tasks.add(deadline);
                    System.out.println("Got it. I've added this task:\n"
                            + deadline.toString() + "\nNow you have " + tasks.size()
                            + " in the list.");
                } else if (first.equals("event")) {
                    Event event = new Event(task, date);
                    tasks.add(event);
                    System.out.println("Got it. I've added this task:\n"
                            + event.toString() + "\nNow you have " + tasks.size()
                            + " in the list.");
                }
            }
        } catch (Exception e) {
            throw new DukeException(input);
        }
    }

    private static void receiveInputs() {
        try {
            readFile();
        } catch (FileNotFoundException e) {
            System.out.println("\nOOPS!!! No save file found. One will be created after you go!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("OOPS!!! The data in the save file is formatted incorrectly!");
        }
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                String input = sc.nextLine();
                if (input.equals("bye")) {
                    writeToFile();
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equals("list")) {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + 1 + "." + tasks.get(i).toString());
                    }
                } else {
                    addToList(input);
                }
            } catch (DukeException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        receiveInputs();
    }
}
