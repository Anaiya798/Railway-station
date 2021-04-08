import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class Platform
{
    private static final Logger logger_platforms = Logger.getLogger(Platform.class.getName());
    Handler fileHandler = new FileHandler("%h/working_process.log");


    int id = 0;
    Track[] tracks;
    public Platform(int id) throws IOException {
        this.id = id;
        logger_platforms.setUseParentHandlers(false);
        logger_platforms.addHandler(fileHandler);
        tracks = new Track[2];
        for (int i = 0; i < 2; i++) {
            tracks[i] = new Track(i,this);
        }
    }
}