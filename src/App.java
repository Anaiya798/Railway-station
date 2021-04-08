import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class App
{
    private static final Logger logger_app = Logger.getLogger(App.class.getName());
    Handler fileHandler_ap = new FileHandler("%h/working_process.log");

    public App() throws IOException {
        logger_app.setUseParentHandlers(false);
        logger_app.addHandler(fileHandler_ap);
    }


    public static void main(String[] args) throws IOException {

        logger_app.info("Method main in class App called");
        Inits cmd = new Inits();
        Station station = new Station();
        File f = new File("src/timetable.txt");
        logger_app.info("File timetable.txt opened");
        try {
            Scanner in = new Scanner(f);
            while (true) {
                String new_cmd = in.next();
                logger_app.info("Command is" + new_cmd);
                if (new_cmd.compareTo("end") == 0) {
                    logger_app.info("End command list");
                    break;

                }
                cmd.Init(new_cmd, in, station);
            }
        }
        catch(FileNotFoundException ex) {
            System.out.println(ex);
        }

        station.shedule.execute();
    }
}
