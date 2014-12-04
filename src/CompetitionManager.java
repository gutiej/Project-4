import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * Created by Joe on 11/20/2014.
 */


public class CompetitionManager {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private  ArrayList<Team> availableTeams = new ArrayList<Team>();
    private  ArrayList<Team> teams = new ArrayList<Team>();
    private ArrayList<Event> availableEvents = new ArrayList<Event>();

    protected ArrayList<Competition> competitions = new ArrayList<Competition>();

    CompetitionManager(ArrayList<Event> events,ArrayList<Team> teams) {
        this.teams=teams;
        for (Event e:events){
            availableEvents.add(e);
        }
        for (Team t: teams){
            availableTeams.add(t);
        }
    }//..

    public void showCompetitions(){
        System.out.println("\n******* Showing Competitions *******\n");

        for (Competition c: competitions){
            System.out.println("\nEvent: "+c.event.name+
                               "\n\n Team 1: "+c.team1.olympian1.name+
                               "\n         "+c.team1.olympian2.name+
                               "\n\n Team 2: "+c.team2.olympian1.name+
                               "\n         "+c.team2.olympian2.name);
        }
    }//..

    public void initiateStartCompetition(){
        System.out.println("\n******* Start New Competition *******\n");
        Event event;
        Team team1, team2;

        if( (event = chooseEvent()) == null){
            cancel();
            availableEvents.add(event);
            return;
        }

        if((team1 = chooseTeam(1))==null){
            cancel();
            availableTeams.add(team1);
            return;
        }

        if((team2 = chooseTeam(2))==null){
            cancel();
            availableTeams.add(team2);
            return;
        }

        System.out.println("\nStarting competition: " +
                "\nEvent-" + event.name +
                "\nTeam1-" + team1.olympian1.name + ", " + team1.olympian2.name +
                "\nTeam2-"+team2.olympian1.name+", "+team2.olympian2.name);

        startCompetition(event,team1,team2);
    }//..

    public void startCompetition(Event event,Team team1,Team team2){
        Competition competition = new Competition();
        competition.start(event, team1, team2);
        competitions.add(competition);
    }//..

    public Team chooseTeam(int teamNumber){
        System.out.print("\nEnter an Olympian to add their team as team"+teamNumber+": ");
        String choice=getChoice();

            if (choice.toLowerCase().equals("q"))
                return null;

            for (Team t:availableTeams){
                if(choice.toLowerCase().equals(t.olympian1.name.toLowerCase()) ||
                        choice.toLowerCase().equals(t.olympian2.name.toLowerCase()) ){
                    availableTeams.remove(t);
                    System.out.println("Added to team " + teamNumber);
                    return t;
                }
            }

            System.out.println("\n!"+choice + " is not on an LGO team\nOr their team is already in a competition\n");
            return chooseTeam(teamNumber);
    }//..

    private Event chooseEvent(){
        System.out.print("Enter Event: ");

        String choice=getChoice();

            if (choice.toLowerCase().equals("q"))
                return null;

            for (Event e: availableEvents){
                if(choice.toLowerCase().equals(e.name.toLowerCase())){
                    availableEvents.remove(e);
                    return e;
                }
            }

            System.out.println("\nEither "+choice + " is not a LGO event \n"+
                                "Or teams are already competing in this event");
            return chooseEvent();
    }//..

    protected void cancel(){
        System.out.println("Canceling competition creation");
    }//..


    public void initiateEndCompetition(){
        boolean ended=false;
        System.out.print("Enter Completed Event: ");

        String eventName=getChoice();
        if(eventName.toLowerCase().equals("q")){
            return;
        }

        String teamMem;

        for (Competition c:competitions){
            if(eventName.toLowerCase().equals(c.event.name.toLowerCase())){
                System.out.print("Enter Olympian on winning team: ");
                teamMem=getChoice();
                if(teamMem.toLowerCase().equals(c.team1.olympian1.name.toLowerCase()) || teamMem.toLowerCase().equals(c.team1.olympian2.name.toLowerCase()) ){
                    endCompetition(c,c.team1);
                    ended=true;
                    break;
                }else if(teamMem.toLowerCase().equals(c.team2.olympian1.name.toLowerCase()) || teamMem.toLowerCase().equals(c.team2.olympian2.name.toLowerCase()) ){
                    endCompetition(c,c.team2);
                    ended=true;
                    break;
                }else {
                    System.out.println( eventName+" was not competing in this round of " + c.event.name);
                }
            }
        }
        if(!ended) {
            System.out.println("There are no teams currently competing in a " + eventName + " competition");
            initiateEndCompetition();
        }else {
            System.out.println("Competition Ended");
        }
    }//..

    public void endCompetition(Competition competition,Team winningTeam){
        Team losingTeam;

        if(competition.team1 != winningTeam){
            losingTeam=competition.team1;
        }else {
            losingTeam=competition.team2;
        }
        for (Team t:teams){
            if(t==winningTeam){
                t.wins++;
            }
            if(t==losingTeam){
                t.losses++;
            }
        }
        availableTeams.add(competition.team1);
        availableTeams.add(competition.team2);
        availableEvents.add(competition.event);
        competitions.remove(competition);
    }//..

    protected String getChoice(){
        String choice = "";
        try{
            choice = in.readLine();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        return choice;
    }//..

}//.. end Class CompetitionManager
