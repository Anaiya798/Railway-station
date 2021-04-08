import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class Train
{
    private static final Logger logger_train = Logger.getLogger(Train.class.getName());
    Handler fileHandler_tr = new FileHandler("%h/working_process.log");


    int id = 0;
    String type= "unknown";
    String way = "unknown";
    String category = "unknown";
    int count_carriage = 0;

    public Train(int id) throws IOException {
        this.id = id;
        logger_train.setUseParentHandlers(false);
        logger_train.addHandler(fileHandler_tr);
    }
}
