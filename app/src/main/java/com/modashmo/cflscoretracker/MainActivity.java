package com.modashmo.cflscoretracker;

    /*
    Created by Mauricio Fagundes - fagundesm86@gmail.com
    Udacity's Android Basics Nanodegree by Google

    Sainte-Agathe, Manitoba, Canada
    April 23, 2017
    */

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /*
    *   Variables used in this app to compute the score
    *   between Home and Away teams playing a hard-nosed
    *   Canadian football game.
    *
    *   LET'S GO BOMBERS!
    * */
    int homeTeamScore;              // Track Home team score
    int awayTeamScore;              // Track Away team score
    final int INITIAL_SCORE = 0;
    final int TOUCHDOWN = 7;
    final int FIELDGOAL = 3;
    final int EXTRA_POINT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayScore(INITIAL_SCORE, 'A');
        displayScore(INITIAL_SCORE, 'B');
    }

    /*
    *   This method receives a char as a parameter and
    *   displays the score for the correct team in a TextView.
    *   Team A is home team, team B is away team.
    *   */
    public void displayScore(int score, char team) {
        switch (team) {
            case 'A':
                TextView homeTeamScoreTextView = (TextView) findViewById(R.id.home_team_score);
                homeTeamScore = score;
                homeTeamScoreTextView.setText(String.valueOf(homeTeamScore));
                break;
            case 'B':
                TextView awayTeamScoreTextView = (TextView) findViewById(R.id.away_team_score);
                awayTeamScore = score;
                awayTeamScoreTextView.setText(String.valueOf(awayTeamScore));
                break;
        }
    }

    /*
     *   This method updates the team score according to two parameters
     *   received which are number of points (int) and scoring team (char).
     *   Then it calls the method displayScore to correctly update the TextView
     *   for the user.
     *   */
    public void addPoints(int points, char team) {
        switch (team) {
            case 'A':
                homeTeamScore = homeTeamScore + points;
                displayScore(homeTeamScore, 'A');
                break;
            case 'B':
                awayTeamScore = awayTeamScore + points;
                displayScore(awayTeamScore,'B');
                break;
        }
    }

    /*
    *   This method is a common method used in all buttons. It was
    *   added to the styles.xml by default. It checks which button is being pressed
    *   by using a switch statement in order to add points to the correct
    *   team, calling the method addPoints.
    *   */
    public void clickAddScore(View view) {
        switch (view.getId()) {
            case R.id.home_team_touchdown:
                addPoints(TOUCHDOWN, 'A');
                break;
            case R.id.away_team_touchdown:
                addPoints(TOUCHDOWN, 'B');
                break;
            case R.id.home_team_fieldgoal:
                addPoints(FIELDGOAL, 'A');
                break;
            case R.id.away_team_fieldgoal:
                addPoints(FIELDGOAL, 'B');
                break;
            case R.id.home_team_extrapoint:
                addPoints(EXTRA_POINT, 'A');
                break;
            case R.id.away_team_extrapoint:
                addPoints(EXTRA_POINT, 'B');
                break;
        }
    }

    /*
    *   resetScore will prompt the user whether or not they want to
    *   erase both team's scores, if tapped yes then it resets both scores
    *   back to zero and throws a Toast in the screen confirming the opeation.
    *   If tapped no, then nothing happens -- obviously.
    *   */
    public void resetScore(View view) {
        new AlertDialog.Builder(this)
                .setTitle("SCORE RESET")
                .setMessage("Are you sure you want to reset the score?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        displayScore(INITIAL_SCORE, 'A');
                        displayScore(INITIAL_SCORE, 'B');
                        Toast.makeText(MainActivity.this, "Score has been erased!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}
