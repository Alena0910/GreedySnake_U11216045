import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.table.*;
import java.io.*;

public class RankingList {

    private static final String[] columnNames = {"Rank", "Username", "Score"};
    private static String[] rank1name = new String[10], rank2name = new String[10], rank3name = new String[10];
    private static int[] rank1score = new int[10], rank2score = new int[10], rank3score = new int[10];

    private static Color transparent = new Color(0, 0, 0, 0);

    
    public static void openRankingList(String username, int boardWidth, int boardHeight){
        JFrame frame = new JFrame("Ranking List");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        String backgroundImageFile = "RankingBackgroundImage.jpg";
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
        tabbedPane.setPreferredSize(new Dimension(400, 500));
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

        JTable table1 = createTable();
        JTable table2 = createTable();
        JTable table3 = createTable();

        tabbedPane.add("基本版排名", createTablePanel("基本版排名", table1));
        tabbedPane.add("愚人節版排名", createTablePanel("愚人節版排名", table2));
        tabbedPane.add("愚人節版最高分數排名", createTablePanel("愚人節版最高分數排名", table3));

        panel.add(tabbedPane, gbc);

        readRankingList(table1, 1);
        readRankingList(table2, 2);
        readRankingList(table3, 3);

        frame.getContentPane().setBackground(transparent);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

    }

    private static JPanel createTablePanel(String title, JTable table) {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(transparent); // 標題顏色
        panel.setOpaque(false); // 確保面板透明

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("微軟正黑體", Font.BOLD, 18));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        table.setBorder(BorderFactory.createEmptyBorder()); // Remove table border
        scrollPane.setOpaque(false); // 確保滾動窗格透明
        scrollPane.getViewport().setOpaque(false); // 確保視圖區域透明

        CustomTableCellRenderer centerRenderer = new CustomTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        // Center align table headers and set header transparent
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        header.setForeground(Color.BLACK);
        header.setBackground(transparent);
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        header.setOpaque(false);

        // Set table grid color to transparent
        table.setGridColor(transparent);

        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private static JTable createTable(){
        JTable table = new JTable();
        table.setOpaque(false);
        table.setShowGrid(false); // Remove grid lines if needed
        table.setPreferredScrollableViewportSize(new Dimension(380, 250));
        table.setFillsViewportHeight(true); // Fill the entire height of the scroll pane
        table.setBackground(transparent);
        table.setForeground(Color.BLACK);
        table.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        table.setRowHeight(30);
        return table;
    }

    private static class CustomTableCellRenderer extends DefaultTableCellRenderer {
        public CustomTableCellRenderer() {
            setOpaque(false);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setBackground(transparent); // 設置單元格背景透明
            setOpaque(false); // 設置單元格透明
            return this;
        }
    }

    public static void readRankingList(JTable table, int game){
        String filename = game == 1 ? "RankingListSnakeGame.txt" : (game == 2 ? "RankingListAdvance.txt" : "RankingListAdvanceHighestScore.txt");
        String[] name = new String[10];
        int[] scoreList = new int[10];
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);        
        table.setModel(model);
        try{
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            int counter = 0;
            while((line = br.readLine()) != null){
                if(counter >= 10) break;
                name[counter] = line;
                line = br.readLine();
                scoreList[counter] = Integer.parseInt(line);
                model.addRow(new Object[]{counter + 1, name[counter], scoreList[counter]});
                counter++;
            }
            while(counter < 10){
                name[counter] = "N/A";
                scoreList[counter] = 0;
                model.addRow(new Object[]{counter + 1, name[counter], scoreList[counter]});
                counter++;
            }
            if(game == 1){
                rank1name = name;
                rank1score = scoreList;
            } 
            else if(game == 2){
                rank2name = name;
                rank2score = scoreList;
            }
            else{
                rank3name = name;
                rank3score = scoreList;
            }
            br.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void rewriteRankingList(int game, String username, int score){
        String filename = game == 1 ? "RankingListSnakeGame.txt" : (game == 2 ? "RankingListAdvance.txt" : "RankingListAdvanceHighestScore.txt");
        String[] name = game == 1 ? rank1name : (game == 2 ? rank2name : rank3name);
        int[] scoreList = game == 1 ? rank1score : (game == 2 ? rank2score : rank3score);
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
            for(int i = 0; i < 10; i++){
                if(score > scoreList[i]){
                    for(int j = 9; j > i; j--){
                        name[j] = name[j - 1];
                        scoreList[j] = scoreList[j - 1];
                    }
                    name[i] = username;
                    scoreList[i] = score;
                    break;
                }
            }
            for(int i = 0; i < 10; i++){
                bw.write(name[i]);
                bw.newLine();
                bw.write(Integer.toString(scoreList[i]));
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
