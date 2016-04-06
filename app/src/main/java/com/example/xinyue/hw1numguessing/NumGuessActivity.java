/*  Xinyue Liu <xinyue@stanford.edu>
    hw1NumGuessing - The computer generates a random number from 1 to 50 after
    the user presses "start new game" button.  Then the user fill in his/her guess and
    press "enter". The app hints to the user whether the guess is too large, too small or correct
    compared to the right answer.
    The user can start a new game any time by pressing "start new game" button.
*/

package com.example.xinyue.hw1numguessing;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.util.*;
import java.util.*;


public class NumGuessActivity extends AppCompatActivity {
    private int randNum = 0;
    private int defaultHintColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num_guess);

        TextView hintsView = (TextView)findViewById(R.id.hint_view);
        hintsView.setTextSize(18);
        defaultHintColor = hintsView.getCurrentTextColor();
    }

    public void startButtonClick(View view) {
        // generate a random number from 1 to 50
        Random randy = new Random();
        randNum = randy.nextInt(50)+1; // 1-50
        Log.d("randNum", "Generated Number:" + randNum); // for debugging

        //hints: fill in your guess
        TextView hintsView = (TextView) findViewById(R.id.hint_view);
        hintsView.setText("A random number is generated. Please fill in your guess.");
        setDefaultHintText(hintsView);

        //clear editText field
        TextView editTextView = (TextView) findViewById(R.id.inputNum);
        editTextView.setText("");
    }

    public void enterButtonClick(View view) {
        TextView hintsView = (TextView) findViewById(R.id.hint_view);
        setDefaultHintText(hintsView);

        if (randNum == 0){
            //hints: generate a random number first
            hintsView.setText("Press START NEW GAME to generate a random number.");
        }
        else{
            // get user's input
            TextView editTextView = (TextView) findViewById(R.id.inputNum);
            CharSequence input = editTextView.getText();

            if (!input.toString().equals("")) {
                int inputNum = Integer.parseInt(input.toString());
                System.out.println("Your guess:" + inputNum); // for debugging
                update_hints(inputNum);

            }else{
                hintsView.setText("Please fill in an integer from 1 to 50.");
            }
        }
    }

    public void setDefaultHintText(TextView hintsView){
        hintsView.setTextColor(defaultHintColor);
        hintsView.setTextSize(18);
        hintsView.setTypeface(null, Typeface.NORMAL);
    }

    public void update_hints(int inputNum){
        TextView hintsView = (TextView) findViewById(R.id.hint_view);
        setDefaultHintText(hintsView);

        if (inputNum > 50 || inputNum < 1){
            hintsView.setText("Please fill in an integer from 1 to 50");
        }
        else{
            if (inputNum == randNum){
                hintsView.setText("Your guess is correct! Press the button to start a new game.");
                hintsView.setTextColor(Color.RED);
                hintsView.setTextSize(22);
                hintsView.setTypeface(null, Typeface.BOLD_ITALIC);

            }else if(inputNum > randNum){
                hintsView.setText("Too large. Fill in a smaller number.");

            }else{
                hintsView.setText("Too small. Fill in a larger number.");
            }
        }
    }


}
