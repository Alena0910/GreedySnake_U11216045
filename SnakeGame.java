import javax.swing.*;
import java.awt.*;

public class SnakeGame extends JPanel{
    private class Tile {
        int x, y;

        Tile(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    int boardWidth, boardHeight;
    int tileSize = 12; // 格子邊長

    Tile snakehead;

    SnakeGame(int boardWidth, int boardHeight){
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.BLACK);

        snakehead = new Tile((int)Math.sqrt(tileSize) , (int)Math.sqrt(tileSize) );
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        // 格線
        for(int i = 0 ; i < boardWidth / tileSize ; i++){
            g.drawLine(i * tileSize, 0, i * tileSize, boardHeight); // 垂直線
            g.drawLine(0, i * tileSize, boardWidth, i * tileSize); // 水平線
        }
        // 畫出蛇
        g.setColor(Color.green);
        g.fillRect(snakehead.x * tileSize, snakehead.y * tileSize, tileSize, tileSize);
    }
}
