package wothers.bombgrid;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BombTile extends JButton {
    private static final MouseListener HANDLER = new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            BombTile bombTile = (BombTile) e.getSource();
            if (e.getButton() == MouseEvent.BUTTON1)
                bombTile.leftClicked();
            else if (e.getButton() == MouseEvent.BUTTON3)
                bombTile.rightClicked();
        }
    };

    final int x, y;
    final boolean isBomb;
    private final GamePanel gamePanel;
    private boolean isFlagged = false, isRevealed = false;

    BombTile(int x, int y, boolean isBomb, GamePanel gamePanel) {
        this.x = x;
        this.y = y;
        this.isBomb = isBomb;
        this.gamePanel = gamePanel;
        setImage("blank");
        setBorderPainted(false);
        addMouseListener(HANDLER);
    }

    boolean isRevealed() {
        return isRevealed;
    }

    void setAsRevealed() {
        isRevealed = true;
    }

    void setImage(String filename) {
        this.setIcon(new ImageIcon(getClass().getResource("/images/" + filename + ".png")));
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
