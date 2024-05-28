import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class MenuFrame {
    public static void openMenuFrame(String username, int boardWidth, int boardHeight){
        JFrame menuFrame = new JFrame("Menu");
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setSize(400, 300);
        menuFrame.setLayout(new GridBagLayout());
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setPreferredSize(new Dimension(400, 400));
        panel.setBackground(new Color(5, 16, 20));



        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;

        JButton rules = new JButton("遊戲說明");
        rules.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        rules.setContentAreaFilled(true);
        rules.setForeground(Color.BLACK);
        rules.setPreferredSize(new Dimension(100, 50));
        rules.setBackground(new Color(255, 204, 204));
        rules.setBorder(new LineBorder(new Color(255, 204, 204), 3));

        JButton normal = new JButton("基本版");
        normal.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        normal.setContentAreaFilled(true);
        normal.setForeground(Color.BLACK);
        normal.setPreferredSize(new Dimension(100, 50));
        normal.setBackground(new Color(234, 196, 213));
        normal.setBorder(new LineBorder(new Color(234, 196, 213), 3));

        JButton advance = new JButton("愚人節版");
        advance.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        advance.setContentAreaFilled(true);
        advance.setForeground(Color.WHITE);
        advance.setPreferredSize(new Dimension(100, 50));
        advance.setBackground(new Color(71, 108, 155));
        advance.setBorder(new LineBorder(new Color(71, 108, 155), 3));

        JButton mission = new JButton("任務版");
        mission.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        mission.setContentAreaFilled(true);
        mission.setForeground(Color.BLACK);
        mission.setPreferredSize(new Dimension(100, 50));
        mission.setBackground(new Color(180, 159, 204));
        mission.setBorder(new LineBorder(new Color(180, 159, 204), 3));

        JButton rankingList = new JButton("排行榜");
        rankingList.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        rankingList.setContentAreaFilled(true);
        rankingList.setForeground(Color.BLACK);
        rankingList.setPreferredSize(new Dimension(100, 50));
        rankingList.setBackground(new Color(204, 213, 255));
        rankingList.setBorder(new LineBorder(new Color(204, 213, 255), 3));

        panel.add(rules, gbc);
        panel.add(normal, gbc);
        panel.add(advance, gbc);
        panel.add(mission, gbc);
        panel.add(rankingList, gbc);

        rules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Rules.openRules(username, boardWidth, boardHeight);
            }
        });

        normal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Main.openSnakeGame(username, boardWidth, boardHeight);
            }
        });
        normal.addMouseListener(new MouseListener() {
            @Override
            public void mouseEntered(MouseEvent e){
                normal.setBorder(new LineBorder(new Color(234, 196, 213), 6));
            }
            @Override
            public void mouseExited(MouseEvent e){
                normal.setBorder(new LineBorder(new Color(234, 196, 213), 3));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                menuFrame.dispose();
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
        });

        advance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Main.openAdvanceGame(username, boardWidth, boardHeight);
            }
        });

        advance.addMouseListener(new MouseListener() {
            @Override
            public void mouseEntered(MouseEvent e){
                advance.setBorder(new LineBorder(new Color(71, 108, 155), 6));
            }
            @Override
            public void mouseExited(MouseEvent e){
                advance.setBorder(new LineBorder(new Color(71, 108, 155), 3));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                menuFrame.dispose();
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
        });

        mission.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Main.openMissionGame(username, boardWidth, boardHeight);
            }
        });

        mission.addMouseListener(new MouseListener() {
            @Override
            public void mouseEntered(MouseEvent e){
                mission.setBorder(new LineBorder(new Color(180, 159, 204), 6));
            }
            @Override
            public void mouseExited(MouseEvent e){
                mission.setBorder(new LineBorder(new Color(180, 159, 204), 3));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                menuFrame.dispose();
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
        });

        rankingList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                RankingList.openRankingList(username, boardWidth, boardHeight);
            }
        });
        
        menuFrame.add(panel);
        menuFrame.pack();
        menuFrame.setVisible(true);
    }
}
