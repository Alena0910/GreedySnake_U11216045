import javax.swing.*;
import java.awt.*;
import java.security.SecurityPermission;
import java.util.Random;

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

    // 蛇
    Tile snakehead;

    // 水果
    Tile fruit;

    Random random; // 建立 random 物件

    SnakeGame(int boardWidth, int boardHeight){
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.BLACK);

        snakehead = new Tile((int)Math.sqrt(tileSize) , (int)Math.sqrt(tileSize) );

        fruit = new Tile(10, 10);
        random = new Random();
        placeFruit();
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

        // 畫出水果
        g.setColor(Color.red);
        g.fillRect(fruit.x * tileSize, fruit.y * tileSize, tileSize, tileSize);
    }

    public void placeFruit(){
        fruit.x = random.nextInt(boardWidth / tileSize);
        fruit.y = random.nextInt(boardWidth / tileSize);
    }
}
