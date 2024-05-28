import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameOverFrame {

    private static String[] fruitColor = {"red=", "orange=", "yellow=", "green=", "blue="};

    public static void openGameOverFrame(String username, int GameMode, JFrame frame, boolean win, Score score, int[] mission, int highestScore, int boardWidth, int boardHeight){

        if(GameMode <= 2){
            RankingList.readRankingList(GameMode);
            RankingList.rewriteRankingList(GameMode, username, score.getScore(), win);
        }
        if(GameMode == 2){
            RankingList.readRankingList(3);
            RankingList.rewriteRankingList(3, username, highestScore, win);
        }
        if(GameMode == 4){
            RankingList.readRankingList(4);
            RankingList.rewriteRankingList(4, username, highestScore, win);
        }

        String labelText = "";
        if(GameMode == 4 && win){
            labelText = "You Win! Mission Completed!";
        }
        else if(GameMode == 4){
            labelText = "Game Over! Mission Uncompleted: ";
            for(int i = 0 ; i < 5 ; i++){
                if(mission[i] == 0) continue;
                labelText += fruitColor[i] + RandomMission.mission[i] + " ";
            }
        }
        else if(GameMode == 2 && win){
            labelText = "You Win!";
        }
        else{
            labelText = "Game Over:  " + score.getScore();
        }

        JFrame Ending = new JFrame("Game Over");
        Ending.setSize(700, 350);
        Ending.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Ending.setLocationRelativeTo(null);
        Ending.setResizable(false);


        JPanel EndingPanel = new JPanel();
        EndingPanel.setLayout(new GridBagLayout());   
        EndingPanel.setBackground(new Color(5, 16, 20));
        EndingPanel.setPreferredSize(new Dimension(700, 350));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel label = new JLabel();
        label.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        label.setForeground(Color.WHITE);
        label.setText(labelText);

        JButton restart = new JButton("Restart");
        restart.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        restart.setForeground(Color.BLACK);
        restart.setBackground(new Color(237, 106, 94));
        restart.setPreferredSize(new Dimension(100, 50));
        restart.addActionListener(new ActionListener(){ // 重新開始遊戲
            public void actionPerformed(ActionEvent e){
                Ending.dispose();
                if(GameMode == 1) Main.openSnakeGame(username, boardWidth, boardHeight); // 開啟正常版遊戲(重新開始
                else if(GameMode == 2) Main.openAdvanceGame(username, boardWidth, boardHeight);
                else if(GameMode == 4) Main.openMissionGame(username, boardWidth, boardHeight);
                frame.dispose();
            }
        });

        JButton exit = new JButton("Exit");
        exit.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        exit.setForeground(Color.BLACK);
        exit.setBackground(new Color(204, 213, 255));
        exit.setPreferredSize(new Dimension(100, 50));
        exit.addActionListener(new ActionListener(){ // 離開遊戲
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        
        JButton moreOption = new JButton("More Option");
        moreOption.setFont(new Font("微軟正黑體", Font.BOLD, 12));
        moreOption.setForeground(Color.WHITE);
        moreOption.setBackground(new Color(5, 16, 20));
        moreOption.setPreferredSize(new Dimension(100, 50));
        moreOption.addActionListener(new ActionListener(){ // 更多選項
            public void actionPerformed(ActionEvent e){
                Ending.dispose();
                MenuFrame.openMenuFrame(username, boardWidth, boardHeight);
                frame.dispose();
            }
        });

        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        restart.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        moreOption.setAlignmentX(Component.CENTER_ALIGNMENT);

        EndingPanel.add(label, gbc);
        EndingPanel.add(restart, gbc);
        EndingPanel.add(exit, gbc);
        EndingPanel.add(moreOption, gbc);

        Ending.add(EndingPanel);
        Ending.pack();
        Ending.setVisible(true);
    }
}
