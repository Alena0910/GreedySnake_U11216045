import javax.swing.JFrame;

/*資科一 U11216045 黃品甄 */

public class Main{
    public static void main(String[] args) throws Exception{
        int boardWidth = 600;
        int boardHeight = boardWidth;

        JFrame frame = new JFrame("GreedySnake");
        frame.setVisible(true); // 顯示
        frame.setSize(boardWidth, boardHeight); // 設定大小
        frame.setLocationRelativeTo(null); // 讓視窗置中
        frame.setResizable(false); // 不可重新設定frame大小
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 關閉

        SnakeGame snakegame = new SnakeGame(boardWidth, boardHeight);
        // 建立一個繼承JPanel的 class 物件
        frame.add(snakegame); // 將panel snakegame 加入 frame 中
        frame.pack(); // 讓標題列不算進畫面大小(完整顯示400 * 400 的大小)
        snakegame.requestFocus(); // snakegame會開始監聽鍵盤按鍵
    }
}