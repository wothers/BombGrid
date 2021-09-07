package wothers.bombgrid;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class GameWindow extends JFrame implements ActionListener {
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JMenuBar menuBar = new JMenuBar();
    private JMenu newGameMenu = new JMenu("New Game");
    private JMenuItem beginnerGame = new JMenuItem("Beginner");
    private JMenuItem intermediateGame = new JMenuItem("Intermediate");
    private JMenuItem expertGame = new JMenuItem("Expert");
    private GamePanel gamePanel;

    private enum Difficulty {
        BEGINNER, INTERMEDIATE, EXPERT;
    }

    GameWindow() {
        newGameMenu.add(beginnerGame);
        newGameMenu.add(intermediateGame);
        newGameMenu.add(expertGame);
        menuBar.add(newGameMenu);
        mainPanel.add(menuBar, BorderLayout.NORTH);

        newGame(Difficulty.BEGINNER);
        mainPanel.add(gamePanel, BorderLayout.CENTER);

        beginnerGame.addActionListener(this);
        intermediateGame.addActionListener(this);
        expertGame.addActionListener(this);

        setTitle("BombGrid");
        setContentPane(mainPanel);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        resetPos();
    }

    void newGame(Difficulty difficulty) {
        if (gamePanel != null)
            mainPanel.remove(gamePanel);
        switch (difficulty) {
            case BEGINNER:
                gamePanel = new GamePanel(8, 8, 10);
                break;
            case INTERMEDIATE:
                gamePanel = new GamePanel(16, 16, 40);
                break;
            case EXPERT:
                gamePanel = new GamePanel(30, 16, 99);
                break;
        }
        mainPanel.add(gamePanel);
        resetPos();
    }

    private void resetPos() {
        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(beginnerGame))
            newGame(Difficulty.BEGINNER);
        else if (e.getSource().equals(intermediateGame))
            newGame(Difficulty.INTERMEDIATE);
        else if (e.getSource().equals(expertGame))
            newGame(Difficulty.EXPERT);
    }
}
