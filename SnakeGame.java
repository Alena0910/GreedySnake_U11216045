import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.Timer;

public class SnakeGame extends JPanel implements ActionListener, KeyListener{

    private JFrame frame;

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
    ArrayList<Tile> snakebody;

    // 水果
    Tile fruit;

    Random random; // 建立 random 物件

    // 讓遊戲不斷刷新
    Timer gameloop;
    int speed = 100;
    int velocityX, velocityY;
    boolean gameover = false;
    boolean openGameoverFrame = false;

    Score score = new Score();

    SnakeGame(JFrame frame, int boardWidth, int boardHeight){
        this.frame = frame;
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);

        snakehead = new Tile((int)Math.sqrt(tileSize) , (int)Math.sqrt(tileSize) );
        snakebody = new ArrayList<Tile>();

        fruit = new Tile(10, 10);
        random = new Random();
        placeFruit();

        velocityX = 0;
        velocityY = 1;

        gameloop = new Timer(speed, this); // 100 ms
        gameloop.start();
    }
    public boolean collision(Tile tile1, Tile tile2){
        return tile1.x == tile2.x && tile1.y == tile2.y; // 確認兩點有沒有重疊
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        // 格線
        // for(int i = 0 ; i < boardWidth / tileSize ; i++){
        //     g.drawLine(i * tileSize, 0, i * tileSize, boardHeight); // 垂直線
        //     g.drawLine(0, i * tileSize, boardWidth, i * tileSize); // 水平線
        // }
        // 畫出水果
        g.setColor(new Color(238, 66, 102));
        g.fill3DRect(fruit.x * tileSize, fruit.y * tileSize, tileSize, tileSize, true);

        // 畫出 snakehead
        g.setColor(new Color(115, 251, 211));
        g.fill3DRect(snakehead.x * tileSize, snakehead.y * tileSize, tileSize, tileSize, true);
        // snakebody
        g.setColor(new Color(98, 144, 100));
        for(int i = 0 ; i < snakebody.size() ; i++){
            Tile snakePart = snakebody.get(i);
            g.fill3DRect(snakePart.x * tileSize, snakePart.y * tileSize, tileSize, tileSize, true);
        }

        // score
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        if(gameover){
            g.setColor(Color.red);
            g.drawString("Game Over: " + score.getScore(), tileSize, tileSize + 16);
        }
        else{
            g.drawString("Score: " + score.getScore(), tileSize, tileSize + 16);
        }
    }

    public void placeFruit(){
        fruit.x = random.nextInt(boardWidth / tileSize);
        fruit.y = random.nextInt(boardWidth / tileSize);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        move(); // 更新蛇的位置
        repaint(); // 不斷呼叫 draw 函式
        if(gameover){
            gameloop.stop();
            if(!openGameoverFrame)GameOverFrame.openGameOverFrame(1, frame, false, score, boardWidth, boardHeight);
            openGameoverFrame = true;
        }
    }
    public void move(){
        if(collision(snakehead, fruit)){ // 如果蛇吃到水果
            snakebody.add(new Tile(fruit.x, fruit.y));
            score.addScore();
            placeFruit();
        }
        // 先移動body再移動head，不然list[0]不知道要移動到哪裡
        // snakebody
        for(int i = snakebody.size() - 1 ; i >= 0 ; i--){
            Tile snakePart = snakebody.get(i);
            if(i == 0){
                snakePart.x = snakehead.x;
                snakePart.y = snakehead.y;
            }
            else{
                Tile prevSnakePart = snakebody.get(i - 1);
                snakePart.x = prevSnakePart.x;
                snakePart.y = prevSnakePart.y;
            }
        }
        // snakehead
        snakehead.x += velocityX;
        snakehead.y += velocityY;

        // gameover
        for(int i = 0 ; i < snakebody.size() ; i++){
            Tile snakePart = snakebody.get(i);
            if(collision(snakehead, snakePart)) gameover = true;
        }
        
        //撞牆遊戲結束
        if(snakehead.x * tileSize < 0 || snakehead.x * tileSize >= boardWidth || snakehead.y * tileSize < 0 || snakehead.y * tileSize >= boardHeight){
            gameover = true;
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1){
            velocityX = 0;
            velocityY = -1;
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1){
            velocityX = 0;
            velocityY = 1;
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1){
            velocityX = -1;
            velocityY = 0;
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1){
            velocityX = 1;
            velocityY = 0;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {}
}
