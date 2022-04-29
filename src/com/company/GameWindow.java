package com.company;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private JFrame frame;
    private RenderArea render;
    private GameInfo gameInfo;

    public GameWindow(Board board){
        this.frame = new JFrame("TJACK");
        this.gameInfo = new GameInfo(board);
        this.render = new RenderArea(board, this.gameInfo);
    }

    @Override
    public void show(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(render, BorderLayout.CENTER);
        frame.add(gameInfo, BorderLayout.EAST);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.pack();
    }
}
