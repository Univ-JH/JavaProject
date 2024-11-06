package DB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

enum Theme {
    DARK,
    LIGHT
}

class ThemeManager {
    private Theme currentTheme;

    // 다크 모드 색상 정의
    public Color panelBackgroundDark = new Color(54, 57, 63); // 원래 다크 색상
    public Color textFieldBackgroundDark = new Color(72, 75, 79); // 원래 텍스트 필드 배경
    public Color labelForegroundDark = Color.WHITE; // 라벨 텍스트 색상
    public Color checkboxBackgroundDark = new Color(54, 57, 63); // 체크박스 배경
    public Color buttonBackgroundDark = new Color(88, 101, 242); // 버튼 배경
    public Color buttonForegroundDark = Color.WHITE; // 버튼 텍스트 색상

    // 라이트 모드 색상 정의
    public Color panelBackgroundLight = Color.WHITE; // 라이트 모드 배경
    public Color textFieldBackgroundLight = Color.LIGHT_GRAY; // 라이트 텍스트 필드 배경
    public Color labelForegroundLight = Color.BLACK; // 라이트 라벨 텍스트 색상
    public Color checkboxBackgroundLight = Color.WHITE; // 라이트 체크박스 배경
    public Color buttonBackgroundLight = new Color(200, 200, 255); // 라이트 버튼 배경
    public Color buttonForegroundLight = Color.BLACK; // 라이트 버튼 텍스트 색상

    public ThemeManager() {
        currentTheme = Theme.DARK; // 기본 테마는 다크 모드
    }

    public void toggleTheme() {
        if (currentTheme == Theme.DARK) {
            currentTheme = Theme.LIGHT;
        } else {
            currentTheme = Theme.DARK;
        }
    }

    public Theme getCurrentTheme() {
        return currentTheme;
    }

    // 현재 테마에 따른 색상 반환 메서드
    public Color getPanelBackground() {
        return currentTheme == Theme.DARK ? panelBackgroundDark : panelBackgroundLight;
    }

    public Color getTextFieldBackground() {
        return currentTheme == Theme.DARK ? textFieldBackgroundDark : textFieldBackgroundLight;
    }

    public Color getLabelForeground() {
        return currentTheme == Theme.DARK ? labelForegroundDark : labelForegroundLight;
    }

    public Color getCheckboxBackground() {
        return currentTheme == Theme.DARK ? checkboxBackgroundDark : checkboxBackgroundLight;
    }

    public Color getButtonBackground() {
        return currentTheme == Theme.DARK ? buttonBackgroundDark : buttonBackgroundLight;
    }

    public Color getButtonForeground() {
        return currentTheme == Theme.DARK ? buttonForegroundDark : buttonForegroundLight;
    }
}


class RoundJTextField extends JTextField {
    private int arcWidth;
    private int arcHeight;

    public RoundJTextField(int columns, int arcWidth, int arcHeight) {
        super(columns);
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        setOpaque(false); // 투명 배경 설정
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight); // 둥근 사각형 배경
        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(getForeground());
        g2.setStroke(new BasicStroke(1));
        g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, arcWidth, arcHeight); // 둥근 사각형 테두리
        g2.dispose();
    }
}

class RoundJButton extends JButton {
    private int arcWidth;
    private int arcHeight;

    public RoundJButton(String label, int arcWidth, int arcHeight) {
        super(label);
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        setOpaque(false); // 투명 배경 설정
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight); // 둥근 사각형 배경
        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(getForeground());
        g2.setStroke(new BasicStroke(1));
        g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, arcWidth, arcHeight); // 둥근 사각형 테두리
        g2.dispose();
    }
}

class RoundJComboBox<E> extends JComboBox<E> {
    private int arcWidth;
    private int arcHeight;

    public RoundJComboBox(E[] items, int arcWidth, int arcHeight) {
        super(items);
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        setOpaque(false); // 투명 배경 설정
        setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                                                          int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setOpaque(false);
                return label;
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight); // 둥근 사각형 배경
        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(getForeground());
        g2.setStroke(new BasicStroke(1));
        g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, arcWidth, arcHeight); // 둥근 사각형 테두리
        g2.dispose();
    }
}

public class SignUpForm{
    private static ThemeManager themeManager = new ThemeManager();
    private static JFrame frame;
    private static JPanel panel;

    public static void createAndShowGUI() {
        frame = new JFrame("디스코드 회원가입");
        frame.setSize(380, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(themeManager.getPanelBackground());

        JLabel titleLabel = new JLabel("계정 만들기", SwingConstants.CENTER);
        titleLabel.setBounds(50, 20, 280, 30);
        titleLabel.setForeground(themeManager.getLabelForeground());
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20)); // "맑은 고딕" 대신 "SansSerif"
        panel.add(titleLabel);

        Color textFieldBackground = themeManager.getTextFieldBackground();

        JLabel emailLabel = new JLabel("이메일 *");
        emailLabel.setBounds(50, 210, 100, 25);
        emailLabel.setForeground(themeManager.getLabelForeground());
        panel.add(emailLabel);

        RoundJTextField emailField = new RoundJTextField(20, 15, 15);
        emailField.setBounds(50, 235, 270, 30);
        emailField.setBackground(textFieldBackground);
        emailField.setForeground(themeManager.getLabelForeground());
        emailField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        panel.add(emailField);

        JLabel nicknameLabel = new JLabel("별명 *");
        nicknameLabel.setBounds(50, 280, 100, 25);
        nicknameLabel.setForeground(themeManager.getLabelForeground());
        panel.add(nicknameLabel);

        RoundJTextField nicknameField = new RoundJTextField(20, 15, 15);
        nicknameField.setBounds(50, 305, 270, 30);
        nicknameField.setBackground(textFieldBackground);
        nicknameField.setForeground(themeManager.getLabelForeground());
        nicknameField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        panel.add(nicknameField);

        JLabel usernameLabel = new JLabel("아이디 *");
        usernameLabel.setBounds(50, 70, 100, 25);
        usernameLabel.setForeground(themeManager.getLabelForeground());
        panel.add(usernameLabel);

        RoundJTextField usernameField = new RoundJTextField(20, 15, 15);
        usernameField.setBounds(50, 95, 270, 30);
        usernameField.setBackground(textFieldBackground);
        usernameField.setForeground(themeManager.getLabelForeground());
        usernameField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("비밀번호 *");
        passwordLabel.setBounds(50, 140, 100, 25);
        passwordLabel.setForeground(themeManager.getLabelForeground());
        panel.add(passwordLabel);

        RoundJTextField passwordField = new RoundJTextField(20, 15, 15);
        passwordField.setBounds(50, 165, 270, 30);
        passwordField.setBackground(textFieldBackground);
        passwordField.setForeground(themeManager.getLabelForeground());
        passwordField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        panel.add(passwordField);

        // 생년월일 라벨과 둥근 모서리 콤보박스
        JLabel dobLabel = new JLabel("생년월일 *");
        dobLabel.setBounds(50, 350, 80, 25);
        dobLabel.setForeground(themeManager.getLabelForeground());
        panel.add(dobLabel);

        // 둥근 모서리 콤보박스
        RoundJComboBox<String> yearBox = new RoundJComboBox<>(getYearOptions(), 15, 15);
        yearBox.setBounds(50, 375, 80, 30);
        yearBox.setBackground(themeManager.getTextFieldBackground());
        yearBox.setForeground(themeManager.getLabelForeground());
        yearBox.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        panel.add(yearBox);

        RoundJComboBox<String> monthBox = new RoundJComboBox<>(getMonthOptions(), 15, 15);
        monthBox.setBounds(140, 375, 60, 30);
        monthBox.setBackground(themeManager.getTextFieldBackground());
        monthBox.setForeground(themeManager.getLabelForeground());
        monthBox.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        panel.add(monthBox);

        RoundJComboBox<String> dayBox = new RoundJComboBox<>(getDayOptions(), 15, 15);
        dayBox.setBounds(210, 375, 60, 30);
        dayBox.setBackground(themeManager.getTextFieldBackground());
        dayBox.setForeground(themeManager.getLabelForeground());
        dayBox.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        panel.add(dayBox);

        // 둥근 모서리 버튼
        RoundJButton signUpButton = new RoundJButton("계속하기", 15, 15);
        signUpButton.setBounds(50, 470, 270, 35);
        signUpButton.setBackground(themeManager.getButtonBackground());
        signUpButton.setForeground(themeManager.getButtonForeground());
        signUpButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        panel.add(signUpButton);

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String nickname = nicknameField.getText();
                String username = usernameField.getText();
                String password = passwordField.getText();
                String birthYear = yearBox.getSelectedItem().toString();
                String birthMonth = monthBox.getSelectedItem().toString();
                String birthDay = dayBox.getSelectedItem().toString();
                String birthDate = birthYear + "-" + birthMonth + "-" + birthDay;

                SignUpManager manager = new SignUpManager();
                manager.setUserCredentials(username, password, email, nickname, birthDate);
            }
        });

        // 설정 버튼 추가
        RoundJButton settingsButton = new RoundJButton("설정", 15, 15);
        settingsButton.setBounds(300, 20, 60, 30);
        settingsButton.setBackground(new Color(88, 101, 242)); // 고정된 색상 (설정 버튼은 고정)
        settingsButton.setForeground(Color.WHITE);
        settingsButton.setFont(new Font("SansSerif", Font.PLAIN, 12));
        settingsButton.addActionListener(e -> openSettingsWindow());
        panel.add(settingsButton);

        // 프레임에 패널 추가 및 표시
        frame.add(panel);
        frame.setLocationRelativeTo(null); // 화면 중앙에 위치
        frame.setVisible(true);
    }

    private static void openSettingsWindow() {
        JFrame settingsFrame = new JFrame("설정");
        settingsFrame.setSize(200, 150);
        settingsFrame.setLocationRelativeTo(frame);
        settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        settingsFrame.setResizable(false);

        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(null);
        settingsPanel.setBackground(themeManager.getPanelBackground());

        // 테마 전환 버튼
        RoundJButton toggleThemeButton = new RoundJButton("테마 전환", 15, 15);
        toggleThemeButton.setBounds(50, 50, 100, 30);
        toggleThemeButton.setBackground(themeManager.getButtonBackground());
        toggleThemeButton.setForeground(themeManager.getButtonForeground());
        toggleThemeButton.setFont(new Font("SansSerif", Font.PLAIN, 12));
        toggleThemeButton.addActionListener(e -> {
            themeManager.toggleTheme();
            updateTheme();
            settingsFrame.dispose();
        });
        settingsPanel.add(toggleThemeButton);

        settingsFrame.add(settingsPanel);
        settingsFrame.setVisible(true);
    }

    private static void updateTheme() {
        // 메인 패널 배경 업데이트
        panel.setBackground(themeManager.getPanelBackground());

        // 패널 내 모든 컴포넌트 색상 업데이트
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JLabel) {
                comp.setForeground(themeManager.getLabelForeground());
            } else if (comp instanceof RoundJTextField) {
                comp.setBackground(themeManager.getTextFieldBackground());
                comp.setForeground(themeManager.getLabelForeground());
            } else if (comp instanceof RoundJButton) {
                RoundJButton button = (RoundJButton) comp;
                if (button.getText().equals("설정")) {
                    button.setBackground(new Color(88, 101, 242)); // 설정 버튼은 고정
                    button.setForeground(Color.WHITE);
                } else {
                    button.setBackground(themeManager.getButtonBackground());
                    button.setForeground(themeManager.getButtonForeground());
                }
            } else if (comp instanceof RoundJComboBox<?>) {
                comp.setBackground(themeManager.getTextFieldBackground());
                comp.setForeground(themeManager.getLabelForeground());
            } else if (comp instanceof JCheckBox) {
                JCheckBox checkbox = (JCheckBox) comp;
                checkbox.setForeground(themeManager.getLabelForeground());
                checkbox.setBackground(themeManager.getCheckboxBackground());
            }
        }

        // UI 업데이트 적용
        panel.repaint();
    }

    private static String[] getYearOptions() {
        String[] years = new String[100];
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        for (int i = 0; i < 100; i++) {
            years[i] = String.valueOf(currentYear - i);
        }
        return years;
    }

    private static String[] getMonthOptions() {
        String[] months = new String[12];
        for (int i = 1; i <= 12; i++) {
            months[i - 1] = String.valueOf(i);
        }
        return months;
    }

    private static String[] getDayOptions() {
        String[] days = new String[31];
        for (int i = 1; i <= 31; i++) {
            days[i - 1] = String.valueOf(i);
        }
        return days;
    }
}
