import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

interface Init
{
    void init(Scanner in, Station station) throws IOException;
}

class Undefined implements Init
{
    @Override
    public void init(Scanner in, Station station) {

        System.out.println("Данная команда недоступна");
    }
}

class Train_Init implements Init {
    private static final Logger logger_init_train = Logger.getLogger(Train_Init.class.getName());
    Handler fileHandler_arr = new FileHandler("%h/working_process.log");

    public Train_Init() throws IOException {
        logger_init_train.setUseParentHandlers(false);
        logger_init_train.addHandler(fileHandler_arr);
    }

    @Override
    public void init(Scanner in, Station station) throws IOException {
        logger_init_train.info("Method execute in class Train_Init called");
        int train_number = in.nextInt();
        Train train = null;
        for (Train t: station.trains)
            if (t.id == train_number)
                train = t;
        if (train == null) {
            train = new Train(train_number);
            station.trains.add(train);
        }

        train.category = in.next();
        train.type = in.next();
        train.way = in.next();
        train.count_carriage = in.nextInt();
    }
}

class Arrive_Train_Init implements Init
{
    private static final Logger logger_arrive = Logger.getLogger(Arrive_Train_Init.class.getName());
    Handler fileHandler_arr = new FileHandler("%h/working_process.log");

    public Arrive_Train_Init() throws IOException {
        logger_arrive.setUseParentHandlers(false);
        logger_arrive.addHandler(fileHandler_arr);
    }

    @Override
    public void init(Scanner in, Station station) throws IOException {
        logger_arrive.info("Method execute in class Arrive_Train_Init called");
        Arrive_Event event = new Arrive_Event();
        event.id = in.nextInt();
        int train_number = in.nextInt();
        Train train = null;
        for (Train t: station.trains)
            if (t.id == train_number)
                train = t;
        event.train = train;
        event.time = Timer_Convert.trans_time(in.next());
        event.side = in.next();
        int platform = in.nextInt();
        int track = in.nextInt();
        event.track = station.platforms[platform].tracks[track];
        station.shedule.events.add(event);
    }
}

class Leave_Train_Init implements Init
{
    private static final Logger logger_leave = Logger.getLogger(Leave_Train_Init.class.getName());
    Handler fileHandler_leave = new FileHandler("%h/working_process.log");

    public Leave_Train_Init() throws IOException {
        logger_leave.setUseParentHandlers(false);
        logger_leave.addHandler(fileHandler_leave);
    }

    @Override
    public void init(Scanner in, Station station) throws IOException {
        logger_leave.info("Method execute in class Leave_Train_Init called");
        Leave_Event event = new Leave_Event();
        event.id = in.nextInt();
        int train_number = in.nextInt();
        Train train = null;
        for (Train t: station.trains)
            if (t.id == train_number)
                train = t;
        event.train = train;
        event.time = Timer_Convert.trans_time(in.next());
        event.side = in.next();
        int platform = in.nextInt();
        int track = in.nextInt();
        event.track = station.platforms[platform].tracks[track];
        station.shedule.events.add(event);
    }
}

class Hold_Train_Init implements Init
{
    private static final Logger logger_hold = Logger.getLogger(Leave_Train_Init.class.getName());
    Handler fileHandler_hold = new FileHandler("%h/working_process.log");

    public Hold_Train_Init() throws IOException {
        logger_hold.setUseParentHandlers(false);
        logger_hold.addHandler(fileHandler_hold);
    }

    @Override
    public void init(Scanner in, Station station) throws IOException {
        logger_hold.info("Method execute in class Hold_Train_Init called");
        Hold_Train_Event event = new Hold_Train_Event();
        event.id = in.nextInt();
        event.hold_event_id = in.nextInt();
        event.hold_time = in.nextInt();
        station.shedule.events.add(event);
    }
}

class Adding_Carriage_Init implements Init
{
     private static final Logger logger_add = Logger.getLogger(Adding_Carriage_Init.class.getName());
     Handler fileHandler_add = new FileHandler("%h/working_process.log");

    public Adding_Carriage_Init() throws IOException {
        logger_add.setUseParentHandlers(false);
        logger_add.addHandler(fileHandler_add);
    }

    @Override
    public void init(Scanner in, Station station) throws IOException {
        logger_add.info("Method execute in class Adding_Carriage_Init called");
        Adding_Carriage_Event event = new Adding_Carriage_Event();
        event.id = in.nextInt();
        event.event_id = in.nextInt();
        event.count_carriage = in.nextInt();
        station.shedule.events.add(event);
    }
}

public class Inits
{
    private static final Logger logger_acts = Logger.getLogger(Inits.class.getName());
    Handler fileHandler_act = new FileHandler("%h/working_process.log");


    private Map <String, Init> acts;
    private Undefined unknown_cmd = new Undefined();
    public Inits() throws IOException {
        logger_acts.setUseParentHandlers(false);
        logger_acts.addHandler(fileHandler_act);
        acts = new HashMap <String, Init> ();
        acts.put("train", new Train_Init());
        acts.put("arrive_train", new Arrive_Train_Init());
        acts.put("leave_train", new Leave_Train_Init());
        acts.put("adding_carriage", new Adding_Carriage_Init());
        acts.put("hold_train", new Hold_Train_Init());
    }

    public void Init(String cmd, Scanner in, Station station) throws IOException {
        acts.getOrDefault(cmd, new Undefined()).init(in, station);
        logger_acts.info("checking command " + cmd);
    }
}
