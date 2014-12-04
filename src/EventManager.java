/**
 * Created by Joe on 11/20/2014.
 */



import java.util.ArrayList;

public class EventManager{

    private Event event[] = new Event[]{
            new CanJamEvent(),
            new CornHoleEvent(),
            new HorseShoesEvent(),
            new LadderBallEvent(),
            new StickGameEvent(),
            new WashoosEvent()
    };

    private ArrayList<Event> events = new ArrayList<Event>();

    EventManager(){
        events.add(new CanJamEvent());
        events.add(new CornHoleEvent());
        events.add(new HorseShoesEvent());
        events.add(new LadderBallEvent());
        events.add(new StickGameEvent());
        events.add(new WashoosEvent());
    }//..

    public void showEvents(){
        System.out.println("******* Events *******");
        for(Event e:events){
            System.out.println(e.name+"\n");
        }
    }//..

    public Event[] getEvent() {
        return event;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }//..


}//.. end EventManager