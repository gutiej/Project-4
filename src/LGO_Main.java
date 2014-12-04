/**
 * Created by Joe on 11/20/2014.
 */



import java.io.*;
import java.util.ArrayList;

public class LGO_Main {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedReader file;
    static String fileLocation;
    static String command;

    static EventManager eventManager;

    static OlympianManager olympianManager;

    static TeamManager teamManager;

    static CompetitionManager competitionManager;

    public static void showHelp(){


        System.out.println("******** LGO HELP ******\n*");
        System.out.println("* ARGUMENTS            -- DETAILS\n*");
        System.out.println("* events, e            -- List day\'s events.");
        System.out.println("* olympians, o         -- List day\'s olympians.");
        System.out.println("* teams, t             -- List the days teams as well as their record");
        System.out.println("* competitions, c      -- List the currently running competitions");
        System.out.println("* startcompetition, sc -- Start a new competition");
        System.out.println("* endcompetition, ec   -- End a currently running competition");
        System.out.println("* help, h              -- Display this help message");
        System.out.println("* quit, q              -- Exit the program");
        System.out.println("*\n*************************************");

    }//..

    public static void processCommand() throws IOException{
        command = in.readLine();
        command.toLowerCase();


        if(command.equals("help") || command.equals("h")){
            showHelp();
        }else if(command.equals("quit") || command.equals("q")){
            System.exit(0);
        }else if(command.equals("olympians") || command.equals("o")){
            showOlympians();
        }else if(command.equals("events") || command.equals("e")){
            showEvents();
        }else if(command.equals("teams") || command.equals("t")){
            showTeams();
        }else if(command.equals("competitions") || command.equals("c")){
            showCompetitions();
        }else if(command.equals("startcompetition") || command.equals("sc")){
            startCompetition();
        }else if(command.equals("endcompetition") || command.equals("ec")){
            endCompetition();
        }else{
            showHelp();
        }
    }//..

    public static void showTeams(){
        teamManager.showTeams();
    }//..

    public static void showEvents() {
        eventManager.showEvents();
    }//..

    public static void showOlympians(){
        olympianManager.showOlympians();
    }//...

    public static void showCompetitions(){
        competitionManager.showCompetitions();
    }//..

    public static void startCompetition(){
        competitionManager.initiateStartCompetition();
    }//

    public static void endCompetition(){
        competitionManager.initiateEndCompetition();
    }//..

    public static void main(String[] args) {
        if (args.length < 1) {               //.. handle non-existent argument
            System.out.println("You must enter a .lgoo file location");
            System.exit(1);
        }else {
            try{

                fileLocation = args[0];
                file = new BufferedReader(new FileReader(new File(fileLocation)));

                eventManager = new EventManager();

                olympianManager = new OlympianManager(file);

                teamManager = new TeamManager(olympianManager.getOlympians());

                competitionManager = new CompetitionManager(eventManager.getEvents(),teamManager.getTeams());


                System.out.println("******* LGO ********\n");

                while(true) {
                    System.out.print("\nEnter Command: ");
                    processCommand();
                }

            }catch (FileNotFoundException fnfe){//.. handles invalid arguments
                fnfe.printStackTrace();
                System.out.println("Cannot locate file at location "+fileLocation);
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("Error: internal error");
            }
        }
    }//..


}//.. end Class LGO_Main
