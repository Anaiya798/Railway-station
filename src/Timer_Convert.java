import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class Timer_Convert
{
    private static final Logger logger_timer = Logger.getLogger(Timer_Convert.class.getName());
    Handler fileHandler_time = new FileHandler("%h/working_process.log");

    public Timer_Convert() throws IOException {
        logger_timer.setUseParentHandlers(false);
        logger_timer.addHandler(fileHandler_time);
    }


    public static String get_time(int time)
    {
        logger_timer.info("Method print_time in class Timer called");
        String s = "";
        int h = time / 60;
        int m = time % 60;
        logger_timer.info("Hours:" + h);
        logger_timer.info("Minutes:" + m);
        if(h < 10)
        {
            s =s +  "0";
        }
        s = s + h + ":";
        if(m < 10)
        {
            s = s+ "0";
        }
        s = s + m;
        return s;
    }

    public static int trans_time(String Time)
    {
        logger_timer.info("Method trans_time in class Timer called");
        logger_timer.info("Parametres:"+ Time );
        String [] T_Time = Time.split(":");
        int h = Integer.parseInt(T_Time[0]);
        int m = Integer.parseInt(T_Time[1]);
        return h * 60 + m;
    }
}