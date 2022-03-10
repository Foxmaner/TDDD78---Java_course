package com.company;

import javax.swing.*;
import java.awt.*;

public class GameInfo extends JPanel {
    private Board board;
    public int TILE_SIZE = 100;

    private JLabel nrOfRoundsText;

    private JLabel whosTurnText;

    private JLabel generalInfo;

    public GameInfo(Board board) {
        this.board = board;

        nrOfRoundsText = new JLabel("RoundNr: " + board.getMoveCounter());
        whosTurnText = new JLabel("Whos turn: " + "White");
        generalInfo = new JLabel("Winner: "+  "White!");

        this.add(nrOfRoundsText);
        this.add(whosTurnText);
        this.add(generalInfo);
    }

    @Override public Dimension getPreferredSize(){
        //Placeholder size
        return new Dimension(4*TILE_SIZE,8*TILE_SIZE);
    }
}
