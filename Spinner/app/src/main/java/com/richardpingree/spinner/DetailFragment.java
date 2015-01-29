package com.richardpingree.spinner;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Richard Pingree on 1/28/15.
 */
public class DetailFragment extends Fragment {

    public static final String TAG = "DetailFragement.TAG";

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static int secNum;

    Team selectedTeam;

    TextView player1, player2, player3, player4, player5, player6, player7, player8, player9;

    public List<Team> Teams = new ArrayList<Team>();

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static DetailFragment newInstance(int sectionNumber) {
        secNum = sectionNumber;
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public DetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        player1 = (TextView)rootView.findViewById(R.id.txtplayer1);
        player2 = (TextView)rootView.findViewById(R.id.txtplayer2);
        player3 = (TextView)rootView.findViewById(R.id.txtplayer3);
        player4 = (TextView)rootView.findViewById(R.id.txtplayer4);
        player5 = (TextView)rootView.findViewById(R.id.txtplayer5);
        player6 = (TextView)rootView.findViewById(R.id.txtplayer6);
        player7 = (TextView)rootView.findViewById(R.id.txtplayer7);
        player8 = (TextView)rootView.findViewById(R.id.txtplayer8);
        player9 = (TextView)rootView.findViewById(R.id.txtplayer9);

        loadTeams();
        //determines what info is displayed by spinner selected value
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
        player1.setText("Player 1: " + selectedTeam.getmPlayer1());
        player2.setText("Player 2: " + selectedTeam.getmPlayer2());
        player3.setText("Player 3: " + selectedTeam.getmPlayer3());
        player4.setText("Player 4: " + selectedTeam.getmPlayer4());
        player5.setText("Player 5: " + selectedTeam.getmPlayer5());
        player6.setText("Player 6: " + selectedTeam.getmPlayer6());
        player7.setText("Player 7: " + selectedTeam.getmPlayer7());
        player8.setText("Player 8: " + selectedTeam.getmPlayer8());
        player9.setText("Player 9: " + selectedTeam.getmPlayer9());
        return rootView;
    }

    //creates objects of each team and sets the player names
    public void loadTeams(){

        Team team1 = new Team();
        team1.setmPlayer1("Bob Jones");
        team1.setmPlayer2("Carl Smith");
        team1.setmPlayer3("Arnold White");
        team1.setmPlayer4("John Player");
        team1.setmPlayer5("Fred Styles");
        team1.setmPlayer6("Dean Winchester");
        team1.setmPlayer7("Sam Winchester");
        team1.setmPlayer8("Bobby Fisher");
        team1.setmPlayer9("Harry Adams");
        Teams.add(team1);

        Team team2 = new Team();
        team2.setmPlayer1("Stevne Hill");
        team2.setmPlayer2("Megan Hope");
        team2.setmPlayer3("James Goodson");
        team2.setmPlayer4("Mike Bond");
        team2.setmPlayer5("Stuart David");
        team2.setmPlayer6("Kyle Winston");
        team2.setmPlayer7("Jamie Hamilton");
        team2.setmPlayer8("Rober Fisher");
        team2.setmPlayer9("Adam Adams");
        Teams.add(team2);


        Team team3 = new Team();
        team3.setmPlayer1("George Long");
        team3.setmPlayer2("Sam Jones");
        team3.setmPlayer3("Brian White");
        team3.setmPlayer4("John Quill");
        team3.setmPlayer5("Harold Tate");
        team3.setmPlayer6("Dean Franks");
        team3.setmPlayer7("Larry Ryans");
        team3.setmPlayer8("Kingston Hutt");
        team3.setmPlayer9("Josh Makers");
        Teams.add(team3);


        Team team4 = new Team();
        team4.setmPlayer1("Cooper Roberts");
        team4.setmPlayer2("Stanley Youngblood");
        team4.setmPlayer3("Harry Potter");
        team4.setmPlayer4("John Jones");
        team4.setmPlayer5("Alex Clark");
        team4.setmPlayer6("Dustin James");
        team4.setmPlayer7("Anthony Dean");
        team4.setmPlayer8("Brandon Rays");
        team4.setmPlayer9("Ron Johnston");
        Teams.add(team4);


        Team team5 = new Team();
        team5.setmPlayer1("Charles Manny");
        team5.setmPlayer2("Vince Smith");
        team5.setmPlayer3("Patrick Hughes");
        team5.setmPlayer4("Shannon White");
        team5.setmPlayer5("Timmy King");
        team5.setmPlayer6("Chance Young");
        team5.setmPlayer7("William Rains");
        team5.setmPlayer8("Nate Inman");
        team5.setmPlayer9("Luke Adams");
        Teams.add(team5);


        Team team6 = new Team();
        team6.setmPlayer1("Edward Hampton");
        team6.setmPlayer2("George Walter");
        team6.setmPlayer3("Oliver Mullin");
        team6.setmPlayer4("Kipp Anderson");
        team6.setmPlayer5("Steve Smith");
        team6.setmPlayer6("David Sanders");
        team6.setmPlayer7("Peter Parker");
        team6.setmPlayer8("Clark Kent");
        team6.setmPlayer9("Barry Allen");
        Teams.add(team6);


    }
}
