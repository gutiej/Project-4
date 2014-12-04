/**
 * Created by Joe on 11/20/2014.
 */


abstract class Event{

    String name;
    int playTo;
    boolean isPlayToExact;
    int playDistance;

    public abstract String getExtraInfo();

}//.. end Event
