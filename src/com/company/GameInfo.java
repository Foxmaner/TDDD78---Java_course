package com.company;

import javax.swing.*;
import java.awt.*;

public class GameInfo extends JPanel {
    private Board board;

    public int TILE_SIZE = 100;
    public int FONT_SIZE = 30;

    private JLabel nrOfRoundsText;

    private JLabel whosTurnText;

    private JLabel generalInfo;

    public GameInfo(Board board) {
        this.board = board;
        this.setLayout(new BorderLayout());
        nrOfRoundsText = new JLabel("RoundNr: " + board.getMoveCounter());
        whosTurnText = new JLabel("Whos turn: " + "White");
        generalInfo = new JLabel("Winner: "+  "No winner yet");

        nrOfRoundsText.setFont(new Font("Comic Sans MS", Font.PLAIN, FONT_SIZE));
        whosTurnText.setFont(new Font("Comic Sans MS", Font.PLAIN, FONT_SIZE));
        generalInfo.setFont(new Font("Comic Sans MS", Font.PLAIN, FONT_SIZE));

        this.add(nrOfRoundsText, BorderLayout.NORTH);
        this.add(whosTurnText, BorderLayout.CENTER);
        this.add(generalInfo, BorderLayout.SOUTH);


    }

    public void updateInfo(){
        System.out.println("updating game info");
        nrOfRoundsText.setText("RoundNr: " + board.getMoveCounter());
        if (board.getMoveCounter() % 2 == 0) {
            whosTurnText.setText("Whos turn: " + "White");
        } else {
            whosTurnText.setText("Whos turn: " + "Black");
        }
        if (board.getWinner() != null) {
            generalInfo.setText("Winner: " + board.getWinner());
        }

    }

    @Override public Dimension getPreferredSize(){
        //Placeholder size
        return new Dimension(4*TILE_SIZE,8*TILE_SIZE);
    }
}
