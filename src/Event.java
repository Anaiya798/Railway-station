import java.io.FileWriter;
import java.io.IOException;

abstract public class Event {

    int id;
    Train train;

    public int getTime() {
        return time;
    }

    int time;
    Track track;

    abstract void execute(FileWriter writer) throws IOException;

}
class Arrive_Event extends Event{
    int adding_carriage = 0;
    String side;

    @Override
    void execute(FileWriter writer) throws IOException {

        String s = Timer_Convert.get_time(time) + " " + train.type + " train "+train.id+train.way+" arrived.";
        s = s + "Platform: "+track.platform.id+", track: "+ track.id;
        s = s + "Carriage number: "+ train.count_carriage+ " (from "+side+" side)\n";
        writer.write(s);
        train.count_carriage +=adding_carriage;
    }
}
class Leave_Event extends Event{
    int adding_carriage = 0;
    String side;
    @Override
    void execute(FileWriter writer) throws IOException {
        String s = Timer_Convert.get_time(time) + " " + train.type+ " train "+train.id+train.way+" left.";
        s = s + " Platform: " + track.platform.id+", track: "+ track.id;
        s = s + " Carriage number: "+ train.count_carriage+ " (from "+side+" side)\n";
        writer.write(s);
        String p = "Platform " + track.platform.id + " is free \n";
        writer.write(p);
        train.count_carriage +=adding_carriage;
    }
}
class Adding_Carriage_Event extends Event{
    int count_carriage;
    int event_id;
    @Override
    void execute(FileWriter writer) throws IOException {
        String s = Timer_Convert.get_time(time) + " Added "+count_carriage+" carriage to train "+train.id+"\n";
        writer.write(s);
    }
}

class Platform_Destination_Event extends Event{

    @Override
    void execute(FileWriter writer) throws IOException {
        String s = Timer_Convert.get_time(time) + " Platform " + track.platform.id + " was allocated for train"+ train.id+"\n";
        writer.write(s);

    }
}

class Hold_Train_Event extends Event{
    int id;
    int hold_event_id;
    int hold_time;
    @Override
    void execute(FileWriter writer) {

    }
}


