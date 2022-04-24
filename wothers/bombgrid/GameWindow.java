package wothers.bombgrid;

import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow extends Frame {
    private final MenuBar menuBar = new MenuBar();
    private final Menu newGameMenu = new Menu("New Game");
    private final MenuItem beginnerGame = new MenuItem("Beginner");
    private final MenuItem intermediateGame = new MenuItem("Intermediate");
    private final MenuItem expertGame = new MenuItem("Expert");
    private final MenuItem customGame = new MenuItem("Custom...");
    private GamePanel gamePanel;

    private enum Difficulty {
        BEGINNER, INTERMEDIATE, EXPERT;
    }

    GameWindow() {
        beginnerGame.addActionListener((e) -> newGame(Difficulty.BEGINNER));
        intermediateGame.addActionListener((e) -> newGame(Difficulty.INTERMEDIATE));
        expertGame.addActionListener((e) -> newGame(Difficulty.EXPERT));
        customGame.addActionListener((e) -> new CustomGameDialog(this));
        newGameMenu.add(beginnerGame);
        newGameMenu.add(intermediateGame);
        newGameMenu.add(expertGame);
        newGameMenu.add(customGame);
        menuBar.add(newGameMenu);

        setMenuBar(menuBar);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        setTitle("BombGrid");
        setResizable(false);
        newGame(Difficulty.BEGINNER);
        setVisible(true);
    }

    void newGame(Difficulty difficulty) {
        switch (difficulty) {
            case BEGINNER:
                newGame(8, 8, 10);
                break;
            case INTERMEDIATE:
                newGame(16, 16, 40);
                break;
            case EXPERT:
                newGame(30, 16, 99);
                break;
        }
    }

    void newGame(int width, int height, int bombCount) {
        if (gamePanel != null)
            remove(gamePanel);
        gamePanel = new GamePanel(width, height, bombCount);
        add(gamePanel);
        pack();
        setLocationRelativeTo(null);
    }
}
