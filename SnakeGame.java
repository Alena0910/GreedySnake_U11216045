import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class SnakeGame extends JPanel implements ActionListener, KeyListener{
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

    // 讓遊戲不斷刷新
    Timer gameloop;
    int velocityX, velocityY;

    SnakeGame(int boardWidth, int boardHeight){
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);

        snakehead = new Tile((int)Math.sqrt(tileSize) , (int)Math.sqrt(tileSize) );

        fruit = new Tile(10, 10);
        random = new Random();
        placeFruit();

        velocityX = 0;
        velocityY = 1;

        gameloop = new Timer(100, this); // 100 ms
        gameloop.start();
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
    @Override
    public void actionPerformed(ActionEvent e) {
        move(); // 更新蛇的位置
        repaint(); // 不斷呼叫 draw 函式
    }
    public void move(){
        // snakehead
        snakehead.x += velocityX;
        snakehead.y += velocityY;
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            velocityX = 0;
            velocityY = -1;
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            velocityX = 0;
            velocityY = 1;
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            velocityX = -1;
            velocityY = 0;
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            velocityX = 1;
            velocityY = 0;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {}
}
