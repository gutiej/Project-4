 /**
 * Created by Joe on 11/20/2014.
 */




import java.util.ArrayList;

public class TeamManager {

    private ArrayList<Olympian> availableOlympians = new ArrayList<Olympian>();
    private ArrayList<Team> teams = new ArrayList<Team>();

    private ArrayList<Olympian> maleList = new ArrayList<Olympian>();
    private ArrayList<Olympian> femaleList = new ArrayList<Olympian>();

    int numTeamsToMake;

    TeamManager(ArrayList<Olympian> olympians) {
        for (Olympian o:olympians){
            availableOlympians.add(o);
        }

        numTeamsToMake = (int)(olympians.size()*.5);

        splitMaleFemale();

        while (numTeamsToMake > 0) {
            makeTeam();
        }

    }//..

    protected void splitMaleFemale(){

        for (Olympian o : availableOlympians){
            switch (o.sex){
                case MALE:
                    maleList.add(o);
                    break;
                case FEMALE:
                    femaleList.add(o);
                    break;
            }
        }

       /*System.out.println("males: "+maleList.size());
        System.out.println("females: "+femaleList.size());
       */

    }//..

    protected void makeTeam(){
        Olympian o1,o2;

        o1 = pickRandomMale();
        o2 = pickRandomFemale();

        if(o1 != null && o2 != null) { //.. if !null then candidates found
            teams.add(new Team(o1, o2));
            numTeamsToMake--;//.. one less team to make
        }
    }//...

    public Olympian pickRandomMale(){
        int rand = (int )(Math.random()*maleList.size());
        Olympian m;

        if(maleList.size() > 0){ //.. there are males to choose from
            m = maleList.get(rand);//.. pick availableOlympians
            maleList.remove(maleList.get(rand)); //.. remove from available availableOlympians
            return m;
        }else {//.. no more males, try a female
            return pickRandomFemale();
        }
    }//..

    public Olympian pickRandomFemale(){//.. there are females to choose from
        int rand = (int )(Math.random()*femaleList.size());
        Olympian m;

        if(femaleList.size() > 0){
            m = femaleList.get(rand);
            femaleList.remove(femaleList.get(rand));
            return m;
        }else {//.. no more females, try a male
            return pickRandomMale();
        }
    }//..

    public void showTeams(){
        int teamNum = 1;
        Olympian o1,o2;
        Olympian oddOneOut = null;

        for (Team t: teams){
            o1 = t.olympian1;
            o2 = t.olympian2;
            System.out.println("\n****  Team "+(teamNum++)+"  **********");
            System.out.println("*    "+t.wins+" Wins, "+t.losses+" Losses");
            System.out.println("*\n*    "+o1.name+", "+o1.sex+", "+o1.age);
            System.out.println("*    "+o2.name+", "+o2.sex+", "+o2.age);
            System.out.println("*\n************************");
        }

        if(maleList.size() > 0 || femaleList.size() > 0){//.. there is an odd-one-out
            for (Olympian o : maleList){
                oddOneOut = o;
            }
            for (Olympian o : femaleList){
                oddOneOut = o;
            }

            System.out.println("\n\n!! "+oddOneOut.name+" didn\'t make it on a team :(");

        }
    }//..

    public ArrayList<Team> getTeams() {
        return teams;
    }//..

}//.. end Class TeamManager
