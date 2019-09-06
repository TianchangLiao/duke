import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    protected String at;
    protected Date from;
    protected Date to;

    public Event (String description, String at) throws ParseException {
        super(description);
        this.at = at;
        this.from = stringToDate1(at);
        this.to = stringToDate2(at);
    }

    public Date stringToDate1 (String at) throws ParseException {
        String s1 = at.substring(0, at.indexOf("-") - 1);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        return formatter.parse(s1);
    }

    public Date stringToDate2 (String at) throws ParseException {
        String s1 = at.substring(0, at.indexOf("-") - 5);
        String s2 = at.substring(at.indexOf("-") + 2);
        String s3 = s1 + s2;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        return formatter.parse(s3);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
