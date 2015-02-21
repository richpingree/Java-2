package com.richardpingree.spinner;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Richard Pingree on 2/21/15.
 */
public class DetailTeamFragment extends Fragment{

    public static final String TAG = "DetailTeamFragment.TAG";

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static int secNum;

    Team selectedTeam;

    TextView player1, player2, player3, player4, player5, player6, player7, player8, player9;

    public List<Team> Teams = new ArrayList<Team>();

    //returns a new instance of this fragment for the given section number
    public static DetailTeamFragment newInstance(int sectionNumber) {
        secNum = sectionNumber;
        DetailTeamFragment frag = new DetailTeamFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        frag.setArguments(args);
        return frag;
    }

    public DetailTeamFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        player1 = (TextView)rootView.findViewById(R.id.player1Txt);
        player2 = (TextView)rootView.findViewById(R.id.player2Txt);
        player3 = (TextView)rootView.findViewById(R.id.player3Txt);
        player4 = (TextView)rootView.findViewById(R.id.player4Txt);
        player5 = (TextView)rootView.findViewById(R.id.player5Txt);
        player6 = (TextView)rootView.findViewById(R.id.player6Txt);
        player7 = (TextView)rootView.findViewById(R.id.player7Txt);
        player8 = (TextView)rootView.findViewById(R.id.player8Txt);
        player9 = (TextView)rootView.findViewById(R.id.player9Txt);

        loadTeams();
        //determines what info is displayed by the selected spinner value
        switch (secNum){
            case 0:
                selectedTeam = Teams.get(secNum);
                break;
            case 1:
                selectedTeam = Teams.get(secNum);
                break;
            case 2:
                selectedTeam = Teams.get(secNum);
                break;
            case 3:
                selectedTeam = Teams.get(secNum);
                break;
            case 4:
                selectedTeam = Teams.get(secNum);
                break;
            case 5:
                selectedTeam = Teams.get(secNum);
                break;
        }
        //sets text from team objects within loadTeams method
        player1.setText(selectedTeam.getmPlayer1());
        player2.setText(selectedTeam.getmPlayer2());
        player3.setText(selectedTeam.getmPlayer3());
        player4.setText(selectedTeam.getmPlayer4());
        player5.setText(selectedTeam.getmPlayer5());
        player6.setText(selectedTeam.getmPlayer6());
        player7.setText(selectedTeam.getmPlayer7());
        player8.setText(selectedTeam.getmPlayer8());
        player9.setText(selectedTeam.getmPlayer9());

        return rootView;
    }

    private void loadTeams() {

        Team justice = new Team();
        justice.setmPlayer1("Clark Kent");
        justice.setmPlayer2("Bruce Wayne");
        justice.setmPlayer3("Oliver Queen");
        justice.setmPlayer4("Barry Allen");
        justice.setmPlayer5("Hal Jordan");
        justice.setmPlayer6("Arthur Curry");
        justice.setmPlayer7("John Jones");
        justice.setmPlayer8("Carter Hall");
        justice.setmPlayer9("Hank Heywood, III");
        Teams.add(justice);

        Team birds = new Team();
        birds.setmPlayer1("Barbara Gordon");
        birds.setmPlayer2("Dinah Lance");
        birds.setmPlayer3("Helena Bertinelli");
        birds.setmPlayer4("Zinda Blake");
        birds.setmPlayer5("Selina Kyle");
        birds.setmPlayer6("Tora Olafsdotter");
        birds.setmPlayer7("Cindy Reynolds");
        birds.setmPlayer8("Dawn Granger");
        birds.setmPlayer9("Lori Zechlin");
        Teams.add(birds);

        Team doom = new Team();
        doom.setmPlayer1("Bizarro");
        doom.setmPlayer2("Vril Dox");
        doom.setmPlayer3("Leonard Snart");
        doom.setmPlayer4("Priscilla Rich");
        doom.setmPlayer5("Doris Zeul");
        doom.setmPlayer6("Grodd");
        doom.setmPlayer7("Lex Luthor");
        doom.setmPlayer8("Edward Nigma");
        doom.setmPlayer9("Jonathan Crane");
        Teams.add(doom);

        Team squad = new Team();
        squad.setmPlayer1("Floyd Lawton");
        squad.setmPlayer2("Ben Turner");
        squad.setmPlayer3("June Moone");
        squad.setmPlayer4("George Harkness");
        squad.setmPlayer5("Mark Desmond");
        squad.setmPlayer6("Leah Wasserman");
        squad.setmPlayer7("Tom Tresser");
        squad.setmPlayer8("Eve Eden");
        squad.setmPlayer9("Susan Linden");
        Teams.add(squad);

        Team titans = new Team();
        titans.setmPlayer1("Garth");
        titans.setmPlayer2("Wally West");
        titans.setmPlayer3("Dick Grayson");
        titans.setmPlayer4("Donna Troy");
        titans.setmPlayer5("Tim Drake");
        titans.setmPlayer6("Cassie Sandsmark");
        titans.setmPlayer7("Ray Palmer");
        titans.setmPlayer8("Arthur Light");
        titans.setmPlayer9("Rose Wilson");
        Teams.add(titans);

        Team outsiders = new Team();
        outsiders.setmPlayer1("Jefferson Pierce");
        outsiders.setmPlayer2("Brion Markov");
        outsiders.setmPlayer3("Gabrielle Doe");
        outsiders.setmPlayer4("Rex Mason |");
        outsiders.setmPlayer5("Emily Briggs");
        outsiders.setmPlayer6("Wendy Jones");
        outsiders.setmPlayer7("Gardner Grayle");
        outsiders.setmPlayer8("David Connor");
        outsiders.setmPlayer9("Charlie Wylde");
        Teams.add(outsiders);
    }
}
