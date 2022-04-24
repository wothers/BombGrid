package wothers.bombgrid;

import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GameWindow extends Frame implements WindowListener {
    private MenuBar menuBar = new MenuBar();
    private Menu newGameMenu = new Menu("New Game");
    private MenuItem beginnerGame = new MenuItem("Beginner");
    private MenuItem intermediateGame = new MenuItem("Intermediate");
    private MenuItem expertGame = new MenuItem("Expert");
    private GamePanel gamePanel;

    private enum Difficulty {
        BEGINNER, INTERMEDIATE, EXPERT;
    }

    GameWindow() {
        beginnerGame.addActionListener((e) -> newGame(Difficulty.BEGINNER));
        intermediateGame.addActionListener((e) -> newGame(Difficulty.INTERMEDIATE));
        expertGame.addActionListener((e) -> newGame(Difficulty.EXPERT));
        newGameMenu.add(beginnerGame);
        newGameMenu.add(intermediateGame);
        newGameMenu.add(expertGame);
        menuBar.add(newGameMenu);
        setMenuBar(menuBar);

        addWindowListener(this);
        setTitle("BombGrid");
        setResizable(false);
        setVisible(true);

        newGame(Difficulty.BEGINNER);
    }

    void newGame(Difficulty difficulty) {
        if (gamePanel != null)
            remove(gamePanel);
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
        add(gamePanel);
        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {
        this.dispose();
    }

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}
}
