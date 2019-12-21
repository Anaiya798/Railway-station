package com.spbu.mcs.ppl.ppl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class App
{
    private static final Logger logger_app = Logger.getLogger(App.class.getName());
    Handler fileHandler_ap = new FileHandler("%h/working_process.log");
    logger_app.setUseParentHandlers(false);
    logger_app.addHandler(fileHandler_ap);

    public static void main(String[] args) {
        logger_app.info("Method main in class App called");
        Acts cmd = new Acts();
        Station station = new Station();
        File f = new File("src/com/timetable.txt");
        logger_app.info("File timetable.txt opened");
        try {
            Scanner in = new Scanner(f);
            while (true) {
                String new_cmd = in.next();
                logger_app.info("Command is" + new_cmd);
                if (new_cmd.compareTo("end") == 0) {
                    logger_app.info("End command list");
                    break;

                }
                cmd.Act(new_cmd, in, station);
            }
        }
      catch(FileNotFoundException ex)
            {
                System.out.println(ex);
            }
        }
    }
