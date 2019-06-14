package com.example.diceroller;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    final Random random = new Random();
    int dice1number, dice2number = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            setDiceImageResource(savedInstanceState.getInt("dice1number"), savedInstanceState.getInt("dice2number"));;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main_landscape);
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
            setContentView(R.layout.activity_main);
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outstate) {
        super.onSaveInstanceState(outstate);
        outstate.putInt("dice1number", dice1number);
        outstate.putInt("dice2number", dice2number);
    }

    public void rollDice(View view) {
        setDiceImageResource(dice1number, dice2number);
    }
    private int createRandomNumber1() {
        dice1number = random.nextInt(6) + 1;
        return dice1number;
    }

    private int createRandomNumber2() {
        dice2number = random.nextInt(6) + 1;
        return dice2number;
    }

    public void setDiceImageResource(int dice1number, int dice2number) {
        ImageView dice1ImageView = findViewById(R.id.dice1_imageView);
        ImageView dice2ImageView = findViewById(R.id.dice2_imageView);
        switch (createRandomNumber1()) {
            case 1:
                dice1ImageView.setImageResource(R.drawable.dice_1);
                break;
            case 2:
                dice1ImageView.setImageResource(R.drawable.dice_2);
                break;
            case 3:
                dice1ImageView.setImageResource(R.drawable.dice_3);
                break;
            case 4:
                dice1ImageView.setImageResource(R.drawable.dice_4);
                break;
            case 5:
                dice1ImageView.setImageResource(R.drawable.dice_5);
                break;
            case 6:
                dice1ImageView.setImageResource(R.drawable.dice_6);
                break;
        }
        switch (createRandomNumber2()) {
            case 1:
                dice2ImageView.setImageResource(R.drawable.dice_1);
                break;
            case 2:
                dice2ImageView.setImageResource(R.drawable.dice_2);
                break;
            case 3:
                dice2ImageView.setImageResource(R.drawable.dice_3);
                break;
            case 4:
                dice2ImageView.setImageResource(R.drawable.dice_4);
                break;
            case 5:
                dice2ImageView.setImageResource(R.drawable.dice_5);
                break;
            case 6:
                dice2ImageView.setImageResource(R.drawable.dice_6);
                break;
        }
    }
}
