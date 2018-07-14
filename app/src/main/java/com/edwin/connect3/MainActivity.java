package com.edwin.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    int activePlayer = 0; //0 is yellow 1 is red

    int[] gameState = {2,2,2,2,2,2,2,2,2};

    // 2 means empty

    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6} };

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2) {

            gameState[tappedCounter] = activePlayer;

            counter.setVisibility(View.VISIBLE);
            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }


            counter.animate().translationYBy(1000f).rotation(100).setDuration(500);

            for (int[] winningPosition : winningPositions){
                if ((gameState[winningPosition[0]] == gameState[winningPosition[1]]) && (gameState[winningPosition[1]]==gameState[winningPosition[2]]) && (gameState[winningPosition[0]] != 2)){
                    if (gameState[winningPosition[0]] == 0){
                        Toast.makeText(MainActivity.this,"Yellow wins!",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this,"Red wins!",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        }
    }
    public void playAgain(View view) {

        activePlayer = 0;
        for (int i=0; i < gameState.length; i++){
            gameState[i]=2;
        };
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for (int i =0; i < gridLayout.getChildCount(); i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
