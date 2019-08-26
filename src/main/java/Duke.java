import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int counter = 0;
        ArrayList<Task> tasks = new ArrayList<Task>();
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    String icon = tasks.get(i).getStatusIcon();
                    String tempName = tasks.get(i).description;
                    System.out.println(i + 1 + ".[" + icon + "] " + tempName);
                }
            } else if (input.length() > 3 && input.substring(0, 4).equals("done")) {
                String temp = input.substring(5);
                int temp2 = Integer.parseInt(temp) - 1;
                tasks.get(temp2).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n[\u2713] " + tasks.get(temp2).description);
            } else {
                Task temp = new Task(input);
                tasks.add(temp);
                System.out.println("added: " + tasks.get(counter).description);
                counter++;
            }
        }
    }
}
