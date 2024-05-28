import javax.swing.*;

/*資科一 U11216045 黃品甄 */

public class Main{
    public static void main(String[] args) throws Exception{
        int boardWidth = 600;
        int boardHeight = boardWidth;

        EnterUserName.enterUserName(boardWidth, boardHeight);
    }

    public static void openSnakeGame(String username, int boardWidth, int boardHeight){
        JFrame frame = new JFrame("GreedySnake");
        frame.setVisible(true); // 顯示
        frame.setSize(boardWidth, boardHeight); // 設定大小
        frame.setLocationRelativeTo(null); // 讓視窗置中
        frame.setResizable(false); // 不可重新設定frame大小
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 關閉

        SnakeGame snakegame = new SnakeGame(username, frame, boardWidth, boardHeight);
        // 建立一個繼承JPanel的 class 物件
        frame.add(snakegame); // 將panel snakegame 加入 frame 中
        frame.pack(); // 讓標題列不算進畫面大小(完整顯示400 * 400 的大小)
        snakegame.requestFocus(); // snakegame會開始監聽鍵盤按鍵
    }

    public static void openAdvanceGame(String username, int boardWidth, int boardHeight){
        JFrame frame2 = new JFrame("AdvanceGreedySnake");
        frame2.setVisible(true); // 顯示
        frame2.setSize(boardWidth, boardHeight); // 設定大小
        frame2.setLocationRelativeTo(null); // 讓視窗置中
        frame2.setResizable(false); // 不可重新設定frame大小
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 關閉

        AdvanceSnakeGame snakegame2 = new AdvanceSnakeGame(username, frame2, boardWidth, boardHeight);
        // 建立一個繼承JPanel的 class 物件
        frame2.add(snakegame2); // 將panel snakegame 加入 frame 中
        frame2.pack(); // 讓標題列不算進畫面大小(完整顯示400 * 400 的大小)
        snakegame2.requestFocus(); // snakegame會開始監聽鍵盤按鍵
    }

    public static void openMissionGame(String username, int boardWidth, int boardHeight){
        JFrame frame3 = new JFrame("MissionGreedySnake");
        frame3.setVisible(true); // 顯示
        frame3.setSize(boardWidth, boardHeight); // 設定大小
        frame3.setLocationRelativeTo(null); // 讓視窗置中
        frame3.setResizable(false); // 不可重新設定frame大小
        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 關閉

        Mission snakegame3 = new Mission(username, frame3, boardWidth, boardHeight);
        // 建立一個繼承JPanel的 class 物件
        frame3.add(snakegame3); // 將panel snakegame 加入 frame 中
        frame3.pack(); // 讓標題列不算進畫面大小(完整顯示400 * 400 的大小)
        snakegame3.requestFocus(); // snakegame會開始監聽鍵盤按鍵
    }
}