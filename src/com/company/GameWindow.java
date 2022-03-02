package com.company;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private Board board;
    private JFrame frame;
    private RenderArea render;

    public GameWindow(Board board){
        this.board = board;
        this.frame = new JFrame("TJACK");
        this.render = new RenderArea(board);
    }

    @Override
    public void show(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(render,BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.pack();
    }
}
