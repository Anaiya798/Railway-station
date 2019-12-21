package com.spbu.mcs.ppl.ppl;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class Timer
{
    private static final Logger logger_timer = Logger.getLogger(Timer.class.getName());
    Handler fileHandler_time = new FileHandler("%h/working_process.log");
    logger_timer.setUseParentHandlers(false);
    logger_timer.addHandler(fileHandler_time);

    public static int time;
    public static void print_time()
    {
        logger_timer.info("Method print_time in class Timer called");
        int h = time / 60;
        int m = time % 60;
        logger_timer.info("Hours:" + h);
        logger_timer.info("Minutes:" + m);
        if(h < 10)
        {
            System.out.print(0);
        }
        System.out.print(h + ":");
        if(m < 10)
        {
            System.out.print(0);
        }
        System.out.print(m + "  |  ");
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
