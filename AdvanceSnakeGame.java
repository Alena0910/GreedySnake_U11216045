import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.Timer;

public class AdvanceSnakeGame extends JPanel implements ActionListener, KeyListener{
    
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
    int fruitMoveDirectionX = 1;
    int fruitMoveDirectionY = 1;
    int[] direction = {1, 0, 0, 1, -1, 0, 0, -1, 1, 1, -1, -1, -1, 1, 1, -1};

    Random random; // 建立 random 物件

    // 讓遊戲不斷刷新
    Timer gameloop, sec, repaintTimer;
    int speed = 128;
    int velocityX, velocityY;
    boolean gameover = false;
    boolean win = false;
    boolean openGameoverFrame = false;

    Score score = new Score();
    FruitCounter counter = new FruitCounter();
    RandomMode mode = new RandomMode();

    AdvanceSnakeGame(JFrame frame, int boardWidth, int boardHeight){
        this.frame = frame;
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));

        addKeyListener(this);
        setFocusable(true);

        snakehead = new Tile((int)Math.sqrt(tileSize) , (int)Math.sqrt(tileSize) );
        snakebody = new ArrayList<Tile>();

        fruit = new Tile(10, 10);
        random = new Random();
        placeFruit();

        velocityX = 0;
        velocityY = 1;

        repaintTimer = new Timer(100, this);
        repaintTimer.start();

        gameloop = new Timer(speed, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move(); // 更新蛇的位置
                if(gameover){
                    gameloop.stop();
                    repaintTimer.stop();
                    sec.stop();
                    if(!openGameoverFrame)GameOverFrame.openGameOverFrame(2, frame, win, score, boardWidth, boardHeight);
                    openGameoverFrame = true;
                }
            }
        });
        gameloop.start();

        sec = new Timer(1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                counter.addCounter();
                if(counter.getCounter() >= 10){
                    placeFruit();
                }

                // 讓水果移動
                if(mode.getMode() == 5){
                    if(fruit.x * tileSize < 0 || fruit.x * tileSize > boardWidth || fruit.y * tileSize < 0 || fruit.y * tileSize > boardHeight){
                        placeFruit();
                    }
                    else{
                        fruit.x += fruitMoveDirectionX;
                        fruit.y += fruitMoveDirectionY;
                    }
                }
            }
        });
        sec.start();
    }
    public boolean collision(Tile tile1, Tile tile2){
        return tile1.x == tile2.x && tile1.y == tile2.y; // 確認兩點有沒有重疊
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        try{
            Image backgroundImage = new ImageIcon("backgroundImg.jpg").getImage(); //設置背景圖片
            g.drawImage(backgroundImage, 0, 0, boardWidth, boardHeight, this);
        }
        catch(Exception e){
            System.out.println("Error: " + e);
        }
        draw(g);
    }
    public void draw(Graphics g){
        // 格線
        // for(int i = 0 ; i < boardWidth / tileSize ; i++){
        //     g.drawLine(i * tileSize, 0, i * tileSize, boardHeight); // 垂直線
        //     g.drawLine(0, i * tileSize, boardWidth, i * tileSize); // 水平線
        // }
        // 畫出水果
        g.setColor(mode.getFruitColor());
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
        if(gameover && win){
            g.setColor(Color.green);
            g.drawString("You Win!", tileSize, tileSize + 16);
        }
        else if(gameover){
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
        mode.setMode(); // 隨機設定水果顏色及遊戲模式
        if(mode.getMode() == 5){
            fruitMoveDirectionX = direction[random.nextInt(8) * 2];
            fruitMoveDirectionY = direction[random.nextInt(8) * 2 + 1];
        }
        counter.resetCounter(); // 重新計時
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint(); // 不斷呼叫 draw 函式
    }
    public void move(){
        if(collision(snakehead, fruit)){ // 如果蛇吃到水果
            switch (mode.getMode()) {
                case 0:
                    // 蛇變長
                    for(int i = 0 ; i < 2 ; i++){
                        score.addScore();
                        snakebody.add(new Tile(fruit.x, fruit.y));
                    }
                    break;
                case 1:
                    // 蛇速度加倍
                    speed *= 2;
                    gameloop.setDelay(speed);
                    break;
                case 2:
                    // 蛇變短
                    score.subScore();
                    if(snakebody.size() > 0) snakebody.remove(snakebody.size() - 1);
                    break;
                case 3:
                    // 蛇速度減少20
                    speed -= 20;
                    if(speed < 0){
                        gameover = true;
                    }
                    else gameloop.setDelay(speed);
                    break;
                case 4:
                    // 蛇身體清空
                    score.resetScore();
                    snakebody.clear();
                    break;
                case 5:
                    // 水果移動
                    score.addScore();
                    snakebody.add(new Tile(fruit.x, fruit.y));
                    break;
                default:
                    break;
            }
            if(score.getScore() < 0) gameover = true; // 分數小於0遊戲結束
            else if(score.getScore() >= 10){ // 分數大於等於10遊戲勝利
                win = true;
                gameover = true;
            }
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
        if(snakehead.x * tileSize < 0 || snakehead.x * tileSize > boardWidth || snakehead.y * tileSize < 0 || snakehead.y * tileSize > boardHeight){
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
