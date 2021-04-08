import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class Shedule {
    static final int MINUTES_IN_DAY = 1440;
    ArrayList<Event> events = new ArrayList<Event>();
    boolean is_init = false;

    void init() {
        for (int i = 0; i<events.size();i++) {
            if (events.get(i).getClass() == Hold_Train_Event.class) {
                Hold_Train_Event hold_train_event = (Hold_Train_Event) events.get(i);
                for (int j = 0; j < events.size(); j++) {
                    if (events.get(j).id == hold_train_event.hold_event_id)
                        events.get(j).time = events.get(j).time + hold_train_event.hold_time;
                }
            }
        }
        for (int i = 0; i<events.size();i++) {
            if (events.get(i).getClass() == Arrive_Event.class ){
                Platform_Destination_Event newEvent = new Platform_Destination_Event();
                newEvent.track = events.get(i).track;
                newEvent.train = events.get(i).train;

                if (events.get(i).train.category.equals("local_forming_end")|| events.get(i).train.category.equals("local_forming_start"))
                    newEvent.time = events.get(i).time - 30;
                else
                    newEvent.time = events.get(i).time - 10;
                events.add(newEvent);
            }
            if (events.get(i).getClass()==Adding_Carriage_Event.class) {
                Adding_Carriage_Event adding_carriage_event = (Adding_Carriage_Event) events.get(i);
                for (int j = 0; j < events.size(); j++) {
                    if (events.get(j).id == adding_carriage_event.event_id) {
                        Event dest_event = events.get(j);
                        adding_carriage_event.train = dest_event.train;

                        if (dest_event.getClass() == Arrive_Event.class) {
                            Arrive_Event arrive_event = (Arrive_Event)dest_event;
                            adding_carriage_event.time = dest_event.time + 5;
                            arrive_event.adding_carriage += adding_carriage_event.count_carriage;
                        }
                        if (dest_event.getClass() == Leave_Event.class) {
                            Leave_Event leave_event = (Leave_Event)dest_event;
                            adding_carriage_event.time = dest_event.time - 5;
                            leave_event.adding_carriage += adding_carriage_event.count_carriage;
                        }
                    }
                }
            }
        }
        events.sort(Comparator.comparingInt(Event::getTime));
        is_init = true;
    }

    void execute() throws IOException {

        if (!is_init)
            init();

        try (FileWriter writer = new FileWriter("protocol.txt", false)){
            int period_start = 0;
            for (int time = 0; time< MINUTES_IN_DAY; time++){
                for (Event event: events) {
                    if (event.time==time && event.getClass()!=Hold_Train_Event.class) {
                        writer.write(Timer_Convert.get_time(period_start) + " - " + Timer_Convert.get_time(time - 1) + " no events\n");
                        event.execute(writer);
                        period_start = time + 1;
                    }
                }
            }
            if (period_start <MINUTES_IN_DAY)
                writer.write(Timer_Convert.get_time(period_start) + " - " + Timer_Convert.get_time(MINUTES_IN_DAY - 1) + " no events\n");
        }

        catch (IOException e){
            e.printStackTrace();
        }

    }
}
