package wothers.bombgrid;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Random;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    private final int width, height;
    private BombTile[][] grid;
    private boolean isGameOver = false;

    GamePanel(int width, int height, int bombCount) {
        this.width = width;
        this.height = height;
        grid = new BombTile[width][height];

        setLayout(new GridLayout(height, width));
        setPreferredSize(new Dimension(width * 25, height * 25));

        Random random = new Random();
        int operations = 0, target = bombCount > width * height / 2 ? width * height - bombCount : bombCount;
        while (operations < target) {
            int x = random.nextInt(width), y = random.nextInt(height);
            if (grid[x][y] == null) {
                grid[x][y] = new BombTile(x, y, bombCount <= width * height / 2, this);
                operations++;
            }
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (grid[x][y] == null) {
                    grid[x][y] = new BombTile(x, y, bombCount > width * height / 2, this);
                }
                this.add(grid[x][y]);
            }
        }
    }

    void revealTile(BombTile bombTile) {
        if (bombTile.x == -100)
            return;
        if (bombTile.isBomb && !isGameOver) {
            isGameOver = true;
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    revealTile(grid[x][y]);
                }
            }
        } else if (!bombTile.isRevealed()) {
            bombTile.setAsRevealed();
            if (bombTile.isBomb)
                bombTile.setImage("bomb");
            else {
                int adjacentBombs = 0;
                int x = bombTile.x;
                int y = bombTile.y;

                if (getTileAt(x + 1, y + 1).isBomb)
                    adjacentBombs++;
                if (getTileAt(x + 1, y).isBomb)
                    adjacentBombs++;
                if (getTileAt(x + 1, y - 1).isBomb)
                    adjacentBombs++;
                if (getTileAt(x, y - 1).isBomb)
                    adjacentBombs++;
                if (getTileAt(x - 1, y - 1).isBomb)
                    adjacentBombs++;
                if (getTileAt(x - 1, y).isBomb)
                    adjacentBombs++;
                if (getTileAt(x - 1, y + 1).isBomb)
                    adjacentBombs++;
                if (getTileAt(x, y + 1).isBomb)
                    adjacentBombs++;

                if (adjacentBombs == 0) {
                    revealTile(getTileAt(x + 1, y + 1));
                    revealTile(getTileAt(x + 1, y));
                    revealTile(getTileAt(x + 1, y - 1));
                    revealTile(getTileAt(x, y - 1));
                    revealTile(getTileAt(x - 1, y - 1));
                    revealTile(getTileAt(x - 1, y));
                    revealTile(getTileAt(x - 1, y + 1));
                    revealTile(getTileAt(x, y + 1));
                }

                bombTile.setImage(Integer.toString(adjacentBombs));
            }
        }
    }

    boolean isGameOver() {
        return isGameOver;
    }

    private BombTile getTileAt(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height)
            return new BombTile(-100, -100, false, this);
        return grid[x][y];
    }
}
