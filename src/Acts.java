package com.spbu.mcs.ppl.ppl;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

interface Act
{
    void execute(Scanner in, Station station);
}

class Undefined implements Aсt
{
    public void execute(Scanner in, Station station)
    {
        System.out.println("Данная команда недоступна");
    }
}

class arrive_train implements Act
{
    private static final Logger logger_arrive = Logger.getLogger(arrive_train.class.getName())
    Handler fileHandler_arr = new FileHandler("%h/working_process.log");
    logger_arrive.setUseParentHandlers(false);
    logger_arrive.addHandler(fileHandler_arr);
    public void execute(Scanner in, Station station)
    {
        logger_arrive.info("Method execute in class arrive_train(Acts) called");
        String type = in.next();
        String time_arrive= in.next();
        String way = in.next();
        int count_carriage = in.nextInt();
        String d_number = in.next();
        int platform = in.nextInt();
        int track = in.nextInt();
        int arrive_time = Timer.trans_time(time_arrive);
        Timer.time = arrive_time;
        Timer.print_time();
        station.arrive_train(type, arrive_time, way, count_carriage, d_number,  track, platform);
        logger_arrive.info("Taken data:" + type + time_arrive + way + count_carriage + d_number + platform+track + arrive_time);
        System.out.println("Поезд прибывает на платформу: " + platform + " Путь: " + track);
    }
}

class leave_train implements Act
{
    private static final Logger logger_leave = Logger.getLogger(leave_train.class.getName())
    Handler fileHandler_leave = new FileHandler("%h/working_process.log");
    logger_leave.setUseParentHandlers(false);
    logger_leave.addHandler(fileHandler_leave);
    public void execute(Scanner in, Station station)
    {
        logger_leave.info("Method execute in class leave_train(Acts) called");
        String time_leave = in.next();
        int platform = in.nextInt();
        int track = in.nextInt();
        int leave_time = Timer.trans_time(time_leave);
        Timer.time = leave_time;
        Timer.print_time();
        station.leave_train(leave_time,  track, platform);
        logger_leave.info("Taken data:"  + time_leave + platform+track + leave_time);
        System.out.println("Поезд отправляется с платформы: " + platform + " Путь: " + track);
    }
}

class hold_train implements Act
{
    private static final Logger logger_hold = Logger.getLogger(leave_train.class.getName())
    Handler fileHandler_hold = new FileHandler("%h/working_process.log");
    logger_hold.setUseParentHandlers(false);
    logger_hold.addHandler(fileHandler_hold);
    public void execute(Scanner in, Station station)
    {
        logger_hold.info("Method execute in class hold_train(Acts) called");
        String delay = in.next();
        int platform= in.nextInt();
        int track = in.nextInt();
        int wait_time = Timer.trans_time(delay);
        Timer.time = leave_time;
        Timer.print_time();
        station.leave_train(leave_time,  track, platform);
        logger_hold.info("Taken data:"  + delay + platform+track + wait_time);
        System.out.println("Поезд отправляется с платформы с задержкой: " + platform + " Путь: " + track);

    }
}

class cur_train implements Act
    {
        private static final Logger logger_cur = Logger.getLogger(cur_train.class.getName())
        Handler fileHandler_cur = new FileHandler("%h/working_process.log");
        logger_cur.setUseParentHandlers(false);
        logger_cur.addHandler(fileHandler_cur);
        public void execute(Scanner in, Station station)
        {
            logger_cur.info("Method execute in class cur_train(Acts) called");
            String type = in.next();
            String time_arrive = in.next();
            String  time_leave = in.next();
            String way = in.next();
            int count_carriage = in.nextInt();
            String d_number = in.next();
            int leave_time = Timer.trans_time(time_leave);
            int arrive_time = Timer.trans_time(time_arrive);
            Timer.time = arrive_time - 10;
            Timer.print_time();
            station.cur_train(type, arrive_time, leave_time, way, count_carriage, d_number);
            logger_cur.info("Taken data:"  + type + time_arrive +time_leave + way + count_carriage + d_number + leave_time +arrive_time);
            assertEquals(arrive_time - 10, Timer.time);
        }
    }

class adding_carriage implements Act
{
     private static final Logger logger_add = Logger.getLogger(adding_carriage.class.getName())
     Handler fileHandler_add = new FileHandler("%h/working_process.log");
     logger_add.setUseParentHandlers(false);
     logger_add.addHandler(fileHandler_add);
    public void execute(Scanner in, Station station)
    {
        logger_add.info("Method execute in class adding_carriage(Acts) called");
        int count_carriage = in.nextInt();
        int platform = in.nextInt();
        int track = in.nextInt();
        String Time = in.next();
        Timer.time = Timer.trans_time (Time);
        Timer.print_time();
        station.adding_carriage(count_carriage,  track, platform);
        logger_add.info("Taken data:" + count_carriage + platform+track + Time);
        System.out.println("Добавить вагоны к поезду, стоящему на платформе " + platform + "Путь: " + track);
    }
}

public class Acts
{
    private static final Logger logger_acts = Logger.getLogger(Acts.class.getName())
    Handler fileHandler_act = new FileHandler("%h/working_process.log");
    logger_acts.setUseParentHandlers(false);
    logger_acts.addHandler(fileHandler_act);

    private Map <String, Act> events;
    private Undefined unknown_cmd = new Undefined();
    public Acts()
    {
        events = new HashMap <String, Act> ();
        events.put("cur_train", new cur_train());
        events.put("leave_train", new leave_train());
        events.put("adding_carriage", new adding_carriage());
        events.put("arrive_train", new arrive_train());
    }
    public void Act(String cmd, Scanner in, Station station)
    {
        events.get_or_not_exist(cmd, Undefined).execute(in, station);
        logger_acts.info("checking command");
    }
}
