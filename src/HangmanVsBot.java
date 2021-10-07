package sk.majo.projects.Hangman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;

public class HangmanVsBot{
    JFrame botFrame;
    JLabel label, hangMan;
    String hiddenWord;

    JButton[] buttons;
    JButton[] alphabet1 = new JButton[14];
    JButton[] alphabet2 = new JButton[12];

    ImageIcon icon;

    private int attempts = 0;

    public void chosenWord(){
        try {
            Random random = new Random();
            File randomWords = new File("C:\\Users\\majof\\IdeaProjects\\MAJOFIGULA\\src\\sk\\majo\\projects\\Hangman\\RandomWords.txt");
            FileReader fileReader = new FileReader(randomWords);
            BufferedReader br = new BufferedReader(fileReader);

            int number = random.nextInt(999) + 1;

            for (int i = 0; i < number; i++) {
                hiddenWord = br.readLine().toLowerCase();
                //System.out.println(hiddenWord);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void paneLoser(){
        int yesOrNo = JOptionPane.showConfirmDialog(botFrame, "You Lost!" + "\nThe hidden word was: " + hiddenWord.toUpperCase() +
                                                    "\n Do you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
        if (yesOrNo == JOptionPane.YES_OPTION) {
            botFrame.setVisible(false);
            new HangmanVsBot().game();
        } else if (yesOrNo == JOptionPane.NO_OPTION) {
            botFrame.setVisible(false);
            new MainMenu();
        }
    }

    public void paneWinner(){
        int count = 0;
        for (JButton value : buttons) {
            if (!value.getText().equals("")){
                count++;
            }
        }

        if (count == hiddenWord.length()){
            int yesOrNo = JOptionPane.showConfirmDialog(botFrame,"You Win!" + "\n Do you want to play again?", "Winner", JOptionPane.YES_NO_OPTION);
            if (yesOrNo == JOptionPane.YES_OPTION){
                botFrame.setVisible(false);
                new HangmanVsBot().game();
            }else if (yesOrNo == JOptionPane.NO_OPTION){
                botFrame.setVisible(false);
                new MainMenu();
            }
        }
    }

    public void gallows(){
        switch (attempts){
            case 1:
                icon = new ImageIcon("C:\\Users\\majof\\IdeaProjects\\MAJOFIGULA\\src\\sk\\majo\\projects\\Hangman\\obrazky\\image1.png");
                label = new JLabel(icon);
                label.setBounds(210,490,200,35);
                botFrame.add(label);
                break;
            case 2:
                icon = new ImageIcon("C:\\Users\\majof\\IdeaProjects\\MAJOFIGULA\\src\\sk\\majo\\projects\\Hangman\\obrazky\\image2.png");
                label = new JLabel(icon);
                label.setBounds(305,370,5,130);
                botFrame.add(label);
                break;
            case 3:
                icon = new ImageIcon("C:\\Users\\majof\\IdeaProjects\\MAJOFIGULA\\src\\sk\\majo\\projects\\Hangman\\obrazky\\image3.png");
                label = new JLabel(icon);
                label.setBounds(305,370,115,4);
                botFrame.add(label);
                break;
            case 4:
                icon = new ImageIcon("C:\\Users\\majof\\IdeaProjects\\MAJOFIGULA\\src\\sk\\majo\\projects\\Hangman\\obrazky\\image4.png");
                label = new JLabel(icon);
                label.setBounds(290,374,98,27);
                botFrame.add(label);
                break;
            case 5:
                icon = new ImageIcon("C:\\Users\\majof\\IdeaProjects\\MAJOFIGULA\\src\\sk\\majo\\projects\\Hangman\\obrazky\\head.png");
                label = new JLabel(icon);
                label.setBounds(390,370,50,45);
                botFrame.add(label);
                break;
            case 6:
                icon = new ImageIcon("C:\\Users\\majof\\IdeaProjects\\MAJOFIGULA\\src\\sk\\majo\\projects\\Hangman\\obrazky\\body.png");
                label = new JLabel(icon);
                label.setBounds(410,400,8,45);
                botFrame.add(label);
                break;
            case 7:
                icon = new ImageIcon("C:\\Users\\majof\\IdeaProjects\\MAJOFIGULA\\src\\sk\\majo\\projects\\Hangman\\obrazky\\leftSide.png");
                label = new JLabel(icon);
                label.setBounds(394,405,15,25);
                botFrame.add(label);
                break;
            case 8:
                icon = new ImageIcon("C:\\Users\\majof\\IdeaProjects\\MAJOFIGULA\\src\\sk\\majo\\projects\\Hangman\\obrazky\\rightSide.png");
                label = new JLabel(icon);
                label.setBounds(420,410,15,25);
                botFrame.add(label);
                break;
            case 9:
                icon = new ImageIcon("C:\\Users\\majof\\IdeaProjects\\MAJOFIGULA\\src\\sk\\majo\\projects\\Hangman\\obrazky\\leftSide.png");
                label = new JLabel(icon);
                label.setBounds(395,440,15,25);
                botFrame.add(label);
                break;
            case 10:
                icon = new ImageIcon("C:\\Users\\majof\\IdeaProjects\\MAJOFIGULA\\src\\sk\\majo\\projects\\Hangman\\obrazky\\rightSide.png");
                label = new JLabel(icon);
                label.setBounds(418,445,15,25);
                botFrame.add(label);
                break;
        }
    }

    // Actual game

    public void game(){
        int x1 = 20;
        int y = 150;
        int x2 = 650;
        int hX = 227;
        int hY = 200;
        char letter = 97;

        chosenWord();

        botFrame = new JFrame("Hangman");
        botFrame.setBounds(200,10,800,600);
        botFrame.getContentPane().setBackground(Color.white);

        hangMan = new JLabel("Hangman");
        hangMan.setBounds(350, 50, 200,35);
        hangMan.setFont(new Font("TimesRoman", Font.BOLD, 24));

        // Alphabet1

        for (int i = 0; i < 7; i++){
            for (int j = 0; j < 2; j++) {
                alphabet1[i] = new JButton(String.valueOf(letter).toUpperCase());
                alphabet1[i].addActionListener(new HangmanVsBot.ButtonListener());
                alphabet1[i].setBounds(x1, y, 45,35);
                botFrame.getContentPane().add(alphabet1[i]);
                letter++;
                x1 += 65;
            }
            x1 = 20;
            y += 50;
        }

        // Alphabet 2

        letter = 111;
        y = 165;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 2; j++) {
                alphabet2[i] = new JButton(String.valueOf(letter).toUpperCase());
                alphabet2[i].addActionListener(new HangmanVsBot.ButtonListener());
                alphabet2[i].setBounds(x2, y, 47, 35);
                botFrame.getContentPane().add(alphabet2[i]);
                letter++;
                x2 += 65;
            }
            x2 = 650;
            y += 50;
        }

        // Creating hidden word

        buttons = new JButton[hiddenWord.length()];

        for (int i = 0; i < hiddenWord.length(); i++) {
            if (hX == 557){
                JLabel label1 = new JLabel("-");
                label1.setBounds(hX + 5, hY + 10, 20,10);
                botFrame.add(label1);
                hX = 227;
                hY += 40;
            }

            buttons[i] = new JButton("");
            buttons[i].setBounds(hX, hY, 45,30);
            buttons[i].setBackground(Color.white);
            botFrame.getContentPane().add(buttons[i]);

            hX += 55;

        }

        botFrame.getContentPane().add(hangMan);
        botFrame.setLayout(null);
        botFrame.setVisible(true);
        botFrame.setResizable(false);

    }


    public class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            for (int i = 0; i < hiddenWord.length(); i++) {
                if (String.valueOf(hiddenWord.charAt(i)).toUpperCase().equals(e.getActionCommand())) {
                    buttons[i].setText(String.valueOf(hiddenWord.charAt(i)).toUpperCase());
                }
            }
            if (!hiddenWord.contains(e.getActionCommand().toLowerCase())){
                attempts++;
            }

            gallows();

            botFrame.remove((Component) e.getSource());
            botFrame.repaint();

            if (attempts == 10){
                paneLoser();
            }
            paneWinner();
        }
    }
}




