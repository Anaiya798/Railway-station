import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class Track
{
    private static final Logger logger_track = Logger.getLogger(Track.class.getName());
    Handler fileHandler = new FileHandler("%h/working_process.log");

    int id = 0;
    Platform platform;
    public Track(int id, Platform new_platform) throws IOException {
        this.id = id;
        platform = new_platform;
        logger_track.setUseParentHandlers(false);
        logger_track.addHandler(fileHandler);
    }

}
