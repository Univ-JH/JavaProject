package DB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ModernLoginScreenB extends JFrame {
    private JTextField idField;
    private JPasswordField pwField;
    private LoginManager loginManager;

    public ModernLoginScreenB(LoginManager loginManager) {
        this.loginManager = loginManager;

        setTitle("로그인 화면");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(54, 57, 63));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(new Color(47, 49, 54));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints inputGbc = new GridBagConstraints();
        inputGbc.insets = new Insets(10, 10, 10, 10);

        JLabel idLabel = new JLabel("아이디");
        styleLabel(idLabel);
        idField = new JTextField(15);
        styleTextField(idField);

        inputGbc.gridx = 0;
        inputGbc.gridy = 0;
        inputPanel.add(idLabel, inputGbc);

        inputGbc.gridx = 1;
        inputPanel.add(idField, inputGbc);

        JLabel pwLabel = new JLabel("비밀번호");
        styleLabel(pwLabel);
        pwField = new JPasswordField(15);
        styleTextField(pwField);

        inputGbc.gridx = 0;
        inputGbc.gridy = 1;
        inputPanel.add(pwLabel, inputGbc);

        inputGbc.gridx = 1;
        inputPanel.add(pwField, inputGbc);

        JButton loginButton = new JButton("LOG IN");
        styleButton(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String password = new String(pwField.getPassword());

                loginManager.setUserCredentials(id, password);
                loginManager.checkLogin(); // Perform login check
            }
        });

        inputGbc.gridx = 0;
        inputGbc.gridy = 2;
        inputGbc.gridwidth = 2;
        inputPanel.add(loginButton, inputGbc);

        JButton signUpButton = new JButton("회원가입");
        styleButton(signUpButton);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUpForm.createAndShowGUI();
            }
        });


        inputGbc.gridx = 0;
        inputGbc.gridy = 3;
        inputGbc.gridwidth = 2;
        inputPanel.add(signUpButton, inputGbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(inputPanel, gbc);

        add(mainPanel);
        setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(114, 137, 218));
        button.setFocusPainted(false);
        button.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void styleTextField(JTextField textField) {
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.BLACK);
        textField.setCaretColor(Color.BLACK);
        textField.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }

    private void styleLabel(JLabel label) {
        label.setForeground(Color.WHITE);
        label.setFont(new Font("맑은 고딕", Font.BOLD, 14));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginManager loginManager = new LoginManager(); // Create LoginManager instance
            new ModernLoginScreenB(loginManager); // Pass to UI
        });
    }
}
