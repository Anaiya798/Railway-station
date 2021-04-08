import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;


public class Station
{
    private static final Logger logger_station = Logger.getLogger(Station.class.getName());
    Handler fileHandler_st = new FileHandler("%h/working_process.log");


    Shedule shedule = new Shedule();
    Platform[] platforms;
    ArrayList<Train> trains = new ArrayList<Train>();

    public Station() throws IOException {
        logger_station.setUseParentHandlers(false);
        logger_station.addHandler(fileHandler_st);
        platforms = new Platform[10];
        for (int i = 0; i < 10; i++) {
            platforms[i] = new Platform(i);
        }
    }
}