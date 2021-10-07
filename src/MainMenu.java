package sk.majo.projects.Hangman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu implements ActionListener{

    JFrame frame = new JFrame("Hangman");
    JButton singleplayer;
    JButton twoPlayers;

    public MainMenu(){
        JLabel main_menu = new JLabel("Main Menu");
        main_menu.setBounds(175, 90, 200,35);
        main_menu.setFont(new Font("TimesRoman", Font.BOLD, 28));

        singleplayer = new JButton("Singleplayer");
        singleplayer.setBounds(190,180,110,30);
        singleplayer.addActionListener(this);

        twoPlayers = new JButton("Two players");
        twoPlayers.setBounds(190,230,110,30);
        twoPlayers.addActionListener(this);


        frame.add(main_menu);
        frame.add(singleplayer);
        frame.add(twoPlayers);
        frame.setBounds(200,10,500,500); // zmenit x aby to bolo na strede
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }


    public static void main(String[] args) {
        new MainMenu();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == singleplayer){
            frame.setVisible(false);
            new HangmanVsBot().game();
        }else if (e.getSource() == twoPlayers){
            frame.setVisible(false);
            new HangmanVsPlayer().guessingWord();
        }
    }
}
