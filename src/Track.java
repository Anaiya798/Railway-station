package com.spbu.mcs.ppl.ppl;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class Track
{
    private static final Logger logger_track = Logger.getLogger(Track.class.getName());
    Handler fileHandler = new FileHandler("%h/working_process.log");
    logger_track.setUseParentHandlers(false);
    logger_track.addHandler(fileHandler);

    Train train;
    public Track() {

        train = new Train();
    }
    public boolean cur_train(String type, int arrive_time, int leave_time, String way, int count_carriage, String d_number)
    {
        logger_track.info("Method cur_train in class Track called");
        logger_track.info("Parametres:"+ type + arrive_time + leave_time + way + count_carriage + d_number );
        if(train.get_leave_time() >= arrive_time)
        {
            return false;
            logger_track.info("Incorrect data")
        }
        train.cur_train(type, arrive_time, leave_time, way, count_carriage, d_number);
        return true;
    }
    public void leave_train(int leave_time)
    {
        logger_track.info("Method leave_train in class Track called");
        logger_track.info("Parametres:" + leave_time );
        train.leave_train(leave_time);
    }
    public void arrive_train(String type, int arrive_time, String way, int count_carriage, String d_number)
    {
        logger_track.info("Method arrive_train in class Track called");
        logger_track.info("Parametres:"+ type + arrive_time + way + count_carriage + d_number );
        train.arrive_train(type, arrive_time, way, count_carriage, d_number);
    }
    public void adding_carriage(int count_carriage)
    {
        logger_track.info("Method adding_carriage in class Track called");
        logger_track.info("Parametres:" + count_carriage );
        train.adding_carriage(count_carriage);
    }
}
