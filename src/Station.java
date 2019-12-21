package com.spbu.mcs.ppl.ppl;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;


public class Station
{
    private static final Logger logger_station = Logger.getLogger(Station.class.getName())
    Handler fileHandler_st = new FileHandler("%h/working_process.log");
    logger_station.setUseParentHandlers(false);
    logger_station.addHandler(fileHandler_st);

    Platforms[] platforms;
    public Station()
    {
        platforms = new Platforms[10];
        for (int i = 0; i < 10; i++) {
            platforms[i] = new Platforms();
        }
    }
    public boolean cur_train(String type, int arrive_time, int leave_time, String way, int count_carriage, String d_number)
    {
        logger_station.info("Method cur_train in class Station called");
        int platform_number = 0;
        while(!platforms[platform_number].cur_train(type, arrive_time, leave_time, way, count_carriage, d_number))
        {
            platform_number++;
            if(platform_number== 10)
            {
                logger_station.info("No free platform");
                return false;
            }
        }
        System.out.println("Платформа" + platform_number);
        logger_station.info("Platform number for current train is:"+platform_number);
        return true;
    }
    public void leave_train(int leave_time, int tracks_number, int platform_number)
    {
        logger_station.info("Method leave_train in class Station called");
        logger_station.info("Parametres"+ leave_time + tracks_number + platform_number);
        platforms[platform_number].leave_train(leave_time, count_tracks);
    }
    public void arrive_train(String type, int arrive_time, String way, int count_carriage, String d_number, int count_tracks, int platform_number)
    {
        logger_station.info("Method arrive_train in class Station called");
        logger_station.info("Parametres:"+ type + arrive_time + way + count_carriage + d_number + count_tracks + platform_number);
        platforms[platform_number].arrive_train(type, arrive_time, way, count_carriage, d_number, count_tracks);
    }
    public void adding_carriage(int count_carriage, int tracks_number, int platform_number)
    {
        logger_station.info("Method adding_carriage in class Station called");
        logger_station.info("Parametres"+ count_carriage + tracks_number + platform_number);
        platforms[platform_number].adding_carriage(count_carriage, tracks_number);
    }
}
