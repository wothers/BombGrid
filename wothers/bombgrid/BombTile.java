package wothers.bombgrid;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BombTile extends JButton {
    private static final MouseListener tileHandler = new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            BombTile bombTile = (BombTile) e.getSource();
            if (e.getButton() == MouseEvent.BUTTON1)
                bombTile.leftClicked();
            else if (e.getButton() == MouseEvent.BUTTON3)
                bombTile.rightClicked();
        }
    };

    final int x, y;
    private final GamePanel gamePanel;
    private boolean isBomb, isFlagged = false, isRevealed = false;

    BombTile(int x, int y, boolean isBomb, GamePanel gamePanel) {
        this.x = x;
        this.y = y;
        this.gamePanel = gamePanel;
        this.isBomb = isBomb;
        setImage("blank");
        setBorderPainted(false);
        addMouseListener(tileHandler);
    }

    void setImage(String filename) {
        this.setIcon(new ImageIcon(getClass().getResource("/images/" + filename + ".png")));
    }

    boolean isBomb() {
        return isBomb;
    }

    void setContainsBomb(boolean isBomb) {
        this.isBomb = isBomb;
    }

    boolean isRevealed() {
        return isRevealed;
    }

    void setAsRevealed() {
        isRevealed = true;
    }

    private void leftClicked() {
        if (!gamePanel.isGameOver() && !isRevealed && !isFlagged)
            gamePanel.revealTile(this);
    }

    private void rightClicked() {
        if (!gamePanel.isGameOver() && !isRevealed) {
            if (!isFlagged)
                setImage("flag");
            else
                setImage("blank");
            isFlagged = !isFlagged;
        }
    }
}
