package com.spbu.mcs.ppl.ppl;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class Train
{
    private static final Logger logger_train = Logger.getLogger(Train.class.getName());
    Handler fileHandler_tr = new FileHandler("%h/working_process.log");
    logger_train.setUseParentHandlers(false);
    logger_train.addHandler(fileHandler_tr);

    private String type= "unknown";
    private int arrive_time = 0;
    private int leave_time = 0;
    private String way = "unknown";
    private int count_carriage = 0;
    private String d_number = "unknown";
    private int max_time = 3600;

    public void cur_train(String new_type, int new_arrive_time, int new_leave_time, String new_way, int new_count_carriage, String new_d_number)
    {
        logger_train.info("Method cur_train in class Train called");
        logger_train.info("Parametres:"+ new_type + new_arrive_time + new_leave_time + new_way + new_count_carriage + new_d_number );
        type = new_type;
        arrive_time = new_arrive_time;
        leave_time = new_leave_time;
        way = new_way;
        count_carriage = new_count_carriage;
        d_number = new_d_number;
    }
    public void leave_train(int new_leave_time)
    {
        logger_train.info("Method leave_train in class Train called");
        logger_train.info("Parametres:"+  new_leave_time );
        type = "unknown";
        leave_time = new_leave_time;
        way = "unknown";
        count_carriage = 0;
        d_number = "unknown";
    }
    public void arrive_train(String new_type, int new_arrive_time, String new_way, int new_count_carriage, String new_d_number)
    {
        logger_train.info("Method arrive_train in class Train called");
        logger_train.info("Parametres:"+ new_type + new_arrive_time + new_way + new_count_carriage + new_d_number );
        type = new_type;
        arrive_time = new_arrive_time;
        leave_time = max_time;
        way = new_way;
        count_carriage = new_count_carriage;
        d_number = new_d_number;
    }
    public void adding_carriage(int new_count_carriage)
    {
        logger_train.info("Method adding_carriage in class Train called");
        logger_train.info("Parametres:"+  new_count_carriage );
        count_carriage += new_count_carriage;
    }
    public int get_leave_time()
    {
        return leave_time;
    }
}
