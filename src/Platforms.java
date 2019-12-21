package com.spbu.mcs.ppl.ppl;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class Platforms
{
    private static final Logger logger_platforms = Logger.getLogger(Platforms.class.getName());
    Handler fileHandler = new FileHandler("%h/working_process.log");
    logger_platforms.setUseParentHandlers(false);
    logger_platforms.addHandler(fileHandler);

    Track[] tracks;
    public Platforms()
    {
        tracks = new Track[2];
        for (int i = 0; i < 2; i++) {
            tracks[i] = new Track();
        }
    }
    public boolean cur_train(String type, int arrive_time, int leave_time, String way, int count_carriage, String d_number)
    {
        logger_platforms.info("Method cur_train in class Platforms called");
        logger_platforms.info("Parametres:"+ type + arrive_time + leave_time + way + count_carriage + d_number );
        int tracks_count = 0;
        while(!tracks[tracks_count].cur_train(type, arrive_time, leave_time, way, count_carriage, d_number))
        {
            if(tracks_count >= 2)
            {
                return false;
            }
        }
        return true;
    }
    public void leave_train(int leave_time,int count_tracks)
    {
        logger_platforms.info("Method leave_train in class Platforms called");
        logger_platforms.info("Parametres:"+ count_tracks  + leave_time  );
        tracks[tracks_count]. leave_train(leave_time);
    }
    public void arrive_train(String type, int arrive_time, String way, int count_carriage, String d_number,int tracks_count)
    {
        logger_platforms.info("Method arrive_train in class Platforms called");
        logger_platforms.info("Parametres:"+ type  + arrive_time + way + count_carriage + d_number +  tracks_count );
        tracks[tracks_number].arrive_train(type, arrive_time, way, count_carriage, d_number);
    }
    public void adding_carriage(int count_carriage,int tracks_number)
    {
        logger_platforms.info("Method adding_carriage in class Platforms called");
        logger_platforms.info("Parametres:"+ count_carriage  + tracks_number);
        tracks[tracks_number].adding_carriage(count_carriage);
    }
}
