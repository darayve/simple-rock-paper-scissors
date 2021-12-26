package com.estudandinho.rockpaperscissors;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView rock, paper, scissors;
    private TextView resultText, playersChoiceText;

    private int _playerChoice, _computerChoice;
    private final int ROCK = 0, PAPER = 1, SCISSORS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();

        rock.setOnClickListener(view -> {
            chooseOption(ROCK);
            setPlayersChoiceText(R.string.rock_choice);
        });

        paper.setOnClickListener(view -> {
            chooseOption(PAPER);
            setPlayersChoiceText(R.string.paper_choice);
        });

        scissors.setOnClickListener(view -> {
            chooseOption(SCISSORS);
            setPlayersChoiceText(R.string.scissors_choice);
        });
    }

    private void getViews() {
        rock = findViewById(R.id.imgRock);
        paper = findViewById(R.id.imgPaper);
        scissors = findViewById(R.id.imgScissors);
        resultText = findViewById(R.id.resultText);
        playersChoiceText = findViewById(R.id.playersChoiceText);
    }

    private void setPlayersChoiceText(int choiceStringId) {
        playersChoiceText.setText(choiceStringId);
    }

    private void chooseOption(int chosenOpt) {
        _playerChoice = chosenOpt;
        drawGame();
    }

    private void drawGame() {
        int computerPlay = new Random().nextInt(3);

        handleComputerPlay(computerPlay);
        checkThePlay(_playerChoice, _computerChoice);
    }

    private void handleComputerPlay(int choice) {
        ImageView computersChoiceImg = findViewById(R.id.computersChoice);

        switch (choice) {
            case 0:
                computersChoiceImg.setImageResource(R.drawable.pedra);
                _computerChoice = ROCK;
                break;
            case 1:
                computersChoiceImg.setImageResource(R.drawable.papel);
                _computerChoice = PAPER;
                break;
            case 2:
                computersChoiceImg.setImageResource(R.drawable.tesoura);
                _computerChoice = SCISSORS;
                break;
        }
    }

    private void checkThePlay(int playerPlay, int computerPlay) {
        if (playerPlay == computerPlay) tieGame();
        else if (playerPlay == ROCK) {
            if (computerPlay == PAPER) youLost();
            if (computerPlay == SCISSORS) youWon();
        } else if (playerPlay == PAPER) {
            if (computerPlay == ROCK) youWon();
            if (computerPlay == SCISSORS) youLost();
        } else if (playerPlay == SCISSORS) {
            if (computerPlay == ROCK) youLost();
            if (computerPlay == PAPER) youWon();
        }
    }

    private void tieGame() {
        resultText.setText(getString(R.string.tie_game));
        resultText.setTextColor(Color.rgb(255,165,0));
    }

    private void youLost() {
        resultText.setText(getString(R.string.you_lost));
        resultText.setTextColor(Color.RED);
    }

    private void youWon() {
        resultText.setText(getString(R.string.you_won));
        resultText.setTextColor(Color.GREEN);
    }
}