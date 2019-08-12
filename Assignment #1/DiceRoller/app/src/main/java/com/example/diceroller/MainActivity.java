package com.example.diceroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    final Random random = new Random();
    int dice1number, dice2number = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            dice1number = savedInstanceState.getInt("dice1number");
            dice2number = savedInstanceState.getInt("dice2number");
            setDiceImageResource(dice1number, dice2number);
        }
        setDiceImageResource(dice1number, dice2number);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("dice1number", dice1number);
        outState.putInt("dice2number", dice2number);
    }

    public void rollDice(View view) {
        dice1number = random.nextInt(6) + 1;
        dice2number = random.nextInt(6) + 1;
        setDiceImageResource(dice1number, dice2number);
    }

    private void setDiceImageResource(int diceOneNumber, int diceTwoNumber) {
        ImageView dice1ImageView = findViewById(R.id.dice1_imageView);
        ImageView dice2ImageView = findViewById(R.id.dice2_imageView);
        switch (diceOneNumber) {
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
        switch (diceTwoNumber) {
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
