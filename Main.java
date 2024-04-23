import java.util.*;

import javax.swing.JFrame;

public class Main{
    public static void main(String[] args) throws Exception{
        int boardWidth = 400;
        int boardHeight = boardWidth;

        JFrame frame = new JFrame("GreedySnake");
        frame.setVisible(true); // 顯示
        frame.setSize(boardWidth, boardHeight); // 設定大小
        frame.setLocationRelativeTo(null); // 讓視窗置中
        frame.setResizable(false); // 不可重新設定frame大小
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 關閉
    }
}