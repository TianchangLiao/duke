public class DukeException extends Exception {

    private String input;

    DukeException (String str) {
        this.input = str;
    }

    public String getMessage() {
        String message;
        if (this.input.trim().equals("todo")) {
            message = "OOPS!!! The description of a todo cannot be empty.";
        } else if (this.input.trim().equals("deadline")) {
            message = "OOPS!!! The description of a deadline cannot be empty.";
        } else if (this.input.trim().equals("event")) {
            message = "OOPS!!! The description of a event cannot be empty.";
        } else {
            message = "OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
        return message;
    }
}
