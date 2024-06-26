import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class EnterUserName {
    
    public static void enterUserName(int boardWidth, int boardHeight){
        JFrame frame = new JFrame("Enter User Name");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setPreferredSize(new Dimension(400, 300));
        panel.setBackground(new Color(5, 16, 20));

        JLabel label = new JLabel("Enter your name: ");
        label.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        label.setForeground(Color.WHITE);

        JTextField textField = new JTextField();
        textField.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        textField.setPreferredSize(new Dimension(200, 30));


        JButton button = new JButton("Start Game");
        button.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        button.setForeground(Color.BLACK);
        button.setBackground(new Color(237, 106, 94));
        button.setPreferredSize(new Dimension(150, 50));
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String name = textField.getText();
                if(name.equals("")){
                    JOptionPane.showMessageDialog(frame, "Please enter your name", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    frame.dispose();
                    MenuFrame.openMenuFrame(name, boardWidth, boardHeight);
                }
            }
        });


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;

        panel.add(label, gbc);
        panel.add(textField, gbc);
        panel.add(button, gbc);


        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

}
