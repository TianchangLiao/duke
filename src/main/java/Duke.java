import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<Task>();

    static void addToList(String input) throws DukeException {
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
            } else {
                String[] input3 = second.split("/", 2);
                String task = input3[0];
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

    public static void receiveInputs() {
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                String input = sc.nextLine();
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equals("list")) {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + 1 + "." + tasks.get(i).toString());
                    }
                } else {
                    addToList(input);
                }
            } catch (DukeException e) {
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
