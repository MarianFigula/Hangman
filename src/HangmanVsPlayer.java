package sk.majo.projects.Hangman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/** If game is over - connect with Main Menu
 *  frame from MainMenu
 *  create Enter_Key - Single + 2 players
 *
 */



public class HangmanVsPlayer implements ActionListener {
    JFrame frame;
    private JPasswordField field;
    private JButton enter;
    JLabel hangMan, word, label;
    private String hiddenWord;
    private JButton[] buttons;
    JButton[] alphabet1 = new JButton[14];
    JButton[] alphabet2 = new JButton[12];
    ImageIcon icon;
    private int attempts = 0;

    public void paneLoser(){
        int yesOrNo = JOptionPane.showConfirmDialog(frame, "You Lost!" + "\n Do you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
        if (yesOrNo == JOptionPane.YES_OPTION) {
            frame.setVisible(false);
            new HangmanVsPlayer().guessingWord();
        } else if (yesOrNo == JOptionPane.NO_OPTION) {
            frame.setVisible(false);
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
            int yesOrNo = JOptionPane.showConfirmDialog(frame,"You Win!" + "\n Do you want to play again?", "Winner", JOptionPane.YES_NO_OPTION);
            if (yesOrNo == JOptionPane.YES_OPTION){
                frame.setVisible(false);
                new HangmanVsPlayer().guessingWord();
            }else if (yesOrNo == JOptionPane.NO_OPTION){
                frame.setVisible(false);
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
                frame.add(label);
                break;
            case 2:
                icon = new ImageIcon("C:\\Users\\majof\\IdeaProjects\\MAJOFIGULA\\src\\sk\\majo\\projects\\Hangman\\obrazky\\image2.png");
                label = new JLabel(icon);
                label.setBounds(305,370,5,130);
                frame.add(label);
                break;
            case 3:
                icon = new ImageIcon("C:\\Users\\majof\\IdeaProjects\\MAJOFIGULA\\src\\sk\\majo\\projects\\Hangman\\obrazky\\image3.png");
                label = new JLabel(icon);
                label.setBounds(305,370,115,4);
                frame.add(label);
                break;
            case 4:
                icon = new ImageIcon("C:\\Users\\majof\\IdeaProjects\\MAJOFIGULA\\src\\sk\\majo\\projects\\Hangman\\obrazky\\image4.png");
                label = new JLabel(icon);
                label.setBounds(290,374,98,27);
                frame.add(label);
                break;
            case 5:
                icon = new ImageIcon("C:\\Users\\majof\\IdeaProjects\\MAJOFIGULA\\src\\sk\\majo\\projects\\Hangman\\obrazky\\head.png");
                label = new JLabel(icon);
                label.setBounds(390,370,50,45);
                frame.add(label);
                break;
            case 6:
                icon = new ImageIcon("C:\\Users\\majof\\IdeaProjects\\MAJOFIGULA\\src\\sk\\majo\\projects\\Hangman\\obrazky\\body.png");
                label = new JLabel(icon);
                label.setBounds(410,400,8,45);
                frame.add(label);
                break;
            case 7:
                icon = new ImageIcon("C:\\Users\\majof\\IdeaProjects\\MAJOFIGULA\\src\\sk\\majo\\projects\\Hangman\\obrazky\\leftSide.png");
                label = new JLabel(icon);
                label.setBounds(394,405,15,25);
                frame.add(label);
                break;
            case 8:
                icon = new ImageIcon("C:\\Users\\majof\\IdeaProjects\\MAJOFIGULA\\src\\sk\\majo\\projects\\Hangman\\obrazky\\rightSide.png");
                label = new JLabel(icon);
                label.setBounds(420,410,15,25);
                frame.add(label);
                break;
            case 9:
                icon = new ImageIcon("C:\\Users\\majof\\IdeaProjects\\MAJOFIGULA\\src\\sk\\majo\\projects\\Hangman\\obrazky\\leftSide.png");
                label = new JLabel(icon);
                label.setBounds(395,440,15,25);
                frame.add(label);
                break;
            case 10:
                icon = new ImageIcon("C:\\Users\\majof\\IdeaProjects\\MAJOFIGULA\\src\\sk\\majo\\projects\\Hangman\\obrazky\\rightSide.png");
                label = new JLabel(icon);
                label.setBounds(418,445,15,25);
                frame.add(label);
                break;
        }
    }

    // Actual game

    public void game(){
        int x1 = 20;
        int y = 150;
        int x2 = 650;
        int nOfLetters = 18;
        int hX = 227;
        int hY = 200;
        char letter = 97;

        hangMan.setBounds(350, 50, 200,35);

        frame.setBounds(200,10,800,600);
        frame.getContentPane().setBackground(Color.white);
        frame.remove(word);
        frame.remove(field);
        frame.remove(enter);

        // Alphabet1

        for (int i = 0; i < 7; i++){
            for (int j = 0; j < 2; j++) {
                alphabet1[i] = new JButton(String.valueOf(letter).toUpperCase());
                alphabet1[i].addActionListener(new ButtonListener());
                alphabet1[i].setBounds(x1, y, 45,35);
                frame.getContentPane().add(alphabet1[i]);
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
                alphabet2[i].addActionListener(new ButtonListener());
                alphabet2[i].setBounds(x2, y, 47, 35);
                frame.getContentPane().add(alphabet2[i]);
                letter++;
                x2 += 65;
            }
            x2 = 650;
            y += 50;
        }

        // Creating hidden word

        for (int i = 0; i < hiddenWord.length(); i++) {
            if (hiddenWord.length() > nOfLetters){
                JOptionPane.showMessageDialog(frame, "Maximum word length is " + nOfLetters + " !", "Maximum length",
                        JOptionPane.INFORMATION_MESSAGE);

                frame.setVisible(false);
                new HangmanVsPlayer().guessingWord();
                break;
            }
            if (hX == 557){
                JLabel label1 = new JLabel("-");
                label1.setBounds(hX + 5, hY + 10, 20,10);
                frame.add(label1);
                hX = 227;
                hY += 40;
            }

            buttons[i] = new JButton("");
            buttons[i].setBounds(hX, hY, 45,30);
            buttons[i].setBackground(Color.white);
            frame.getContentPane().add(buttons[i]);

            hX += 55;

        }
    }

    // Set the hidden word for another player

    public void guessingWord(){
        frame = new JFrame("Hangman");
        frame.getContentPane().setBackground(new Color(0,194,253));

        field = new JPasswordField();
        field.setBounds(180,200,200,30);

        hangMan = new JLabel("Hangman");
        hangMan.setBounds(230,50,200,35);
        hangMan.setFont(new Font("TimesRoman", Font.BOLD, 24));

        word = new JLabel("Enter the word for another player:");
        word.setBounds(180,170,300,30);

        enter = new JButton("Enter");
        enter.setBounds(230,250,100,30);
        enter.setBackground(Color.white);
        enter.addActionListener(this);

        frame.getContentPane().add(field);
        frame.getContentPane().add(enter);
        frame.getContentPane().add(hangMan);
        frame.getContentPane().add(word);

        frame.setBounds(300,80,600,500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }


    public class ButtonListener implements ActionListener{
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

            frame.remove((Component) e.getSource());
            frame.repaint();

            if (attempts == 10){
                paneLoser();
            }
            paneWinner();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) { // zadanie skryteho textu
        for (char value : field.getText().toCharArray()){
            if (value < (char) 97 || value > 122){
                word.setText("Low letters only! Try again: ");
                field.setText("");
            }
        }
        if (!field.getText().equals("")){
            hiddenWord = field.getText();
            buttons = new JButton[hiddenWord.length()];
            game();
            //System.out.println(hiddenWord);
        }

    }
}
