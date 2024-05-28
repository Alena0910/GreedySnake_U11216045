import java.awt.*;
import java.io.*;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

public class Rules {

    private static String[] RulesFile = {"Rules1.txt", "Rules2.txt", "Rules3.txt"};
    private static String[] RulesTitle = {"基本版遊戲說明", "愚人節版遊戲說明", "任務版遊戲說明"};
    private static Color transparent = new Color(0, 0, 0, 0);

    public static void openRules(String username, int boardWidth, int boardHeight){
        JFrame frame = new JFrame("Game Rules");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setMinimumSize(new Dimension(600, 600));
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        String backgroundImageFile = "RulesBackgroundImage.jpg";
        ImagePanel panel = new ImagePanel(backgroundImageFile);
        panel.setLayout(new GridBagLayout());
        panel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;

        UIManager.put("TabbedPane.contentOpaque", false);
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
        tabbedPane.setPreferredSize(new Dimension(600, 600));
        tabbedPane.setBackground(transparent); // 未選擇的標籤的背景顏色
        tabbedPane.setForeground(Color.BLACK);
        tabbedPane.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        tabbedPane.setUI(new BasicTabbedPaneUI() {
            @Override
            protected void installDefaults() {
                super.installDefaults();
                highlight = transparent; // 選擇標籤的背景顏色
                lightHighlight = transparent; // 選擇標籤的邊框顏色
                shadow = transparent; // 未選擇標籤的背景顏色
                darkShadow = transparent; // 未選擇標籤的邊框顏色
                focus = transparent; // 標籤被選中時的顏色
            }
        });

        try{
            for(int i = 0; i < 3; i++){
                BufferedReader br = new BufferedReader(new FileReader(RulesFile[i]));
                String description = "";
                String line;
                while((line = br.readLine()) != null){
                    description += line + "\n";
                }
                tabbedPane.add(RulesTitle[i], createDescriptionPanel(RulesTitle[i], description));
                br.close();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }

        panel.add(tabbedPane, gbc);

        frame.getContentPane().setBackground(transparent);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
    private static JPanel createDescriptionPanel(String title, String description) {
        Dimension screenSize = new Dimension(450, 400);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.setPreferredSize(screenSize);
        panel.setBackground(transparent);
        panel.setBorder(BorderFactory.createEmptyBorder());

        JTextArea textArea = new JTextArea(description, 30, 60);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setForeground(Color.WHITE);
        textArea.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        textArea.setOpaque(false);
        textArea.setPreferredSize(screenSize);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setPreferredSize(screenSize);
        scrollPane.setMinimumSize(screenSize); // 設置最小大小

        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }
}
