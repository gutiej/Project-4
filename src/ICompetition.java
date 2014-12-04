/**
 * Created by Joe on 11/20/2014.
 */


public interface ICompetition {

    void start(Event event, Team team1, Team team2);

    Event getEvent();

    Team getTeam1();

    Team getTeam2();

}//.. end ICompetition
