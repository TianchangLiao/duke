import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] todo = new String[100];
        int counter = 0;
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        while (true) {
            String task = sc.nextLine();
            if (task.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (task.equals("list")) {
                for (int i = 0; i < todo.length; i++) {
                    if (todo[i] != null) {
                        System.out.println(i + 1 + ". " + todo[i]);
                    }
                }
            } else {
                todo[counter] = task;
                System.out.println("added: " + task);
                counter++;
            }
        }
    }
}
