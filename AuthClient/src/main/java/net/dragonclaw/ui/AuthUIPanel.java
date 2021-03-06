package net.dragonclaw.ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import net.dragonclaw.client.AuthClient;

public class AuthUIPanel extends JPanel {

    private final AuthClient client;
    private AuthUIImagePanel mainPanel = new AuthUIImagePanel("images/background.png");
    private AuthUIImagePanel registerPanel = new AuthUIImagePanel("images/background.png");
    private AuthUIImagePanel loginPanel = new AuthUIImagePanel("images/background.png");
    private JLabel header;

    public AuthUIPanel(AuthClient client) {
        super(new BorderLayout());
        this.client = client;
        setupUI();
        setupLoginPanel();
        setupRegisterPanel();
    }

    public void setupUI() {
        mainPanel.setLayout(new GridBagLayout());
        JButton login = new JButton("Login");
        login.addActionListener(e -> {
            swapToScreen(loginPanel, BorderLayout.CENTER);
        });
        JButton register = new JButton("Register");
        register.addActionListener(e -> {
            swapToScreen(registerPanel, BorderLayout.CENTER);
        });
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 150;
        c.ipady = 10;
        c.insets = new Insets(0, 30, 0, 30);
        mainPanel.add(login, c);
        c.gridx = 1;
        mainPanel.add(register, c);
        add(mainPanel, BorderLayout.CENTER);
    }

    public void setupLoginPanel() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        loginPanel.setLayout(new GridBagLayout());
        JButton login = new JButton("Login");
        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            swapToScreen(mainPanel, BorderLayout.CENTER);
        });
        JTextField username = new JTextField();
        JPasswordField pass = new JPasswordField();
        login.addActionListener(e -> {
            client.sendLoginRequest(username.getText(), new String(pass.getPassword()));
        });
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 150;
        c.ipady = 5;
        c.insets = new Insets(0, 0, 10, 0);
        loginPanel.add(new JLabel("<html><font color='red'>username: </font></html>"), c);
        c.gridx = 1;
        c.gridy = 0;
        c.ipadx = 300;
        loginPanel.add(username, c);
        c.gridx = 0;
        c.gridy = 1;
        c.ipadx = 150;
        loginPanel.add(new JLabel("<html><font color='red'>password: </font></html>"), c);
        c.gridx = 1;
        c.gridy = 1;
        c.ipadx = 300;
        loginPanel.add(pass, c);
        c.gridx = 0;
        c.gridy = 4;
        c.ipadx = 150;
        c.gridwidth = 2;
        c.insets = new Insets(0, 0, 0, 0);
        loginPanel.add(login, c);
        c.gridy = 5;
        loginPanel.add(back, c);
    }

    public void setupRegisterPanel() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        registerPanel.setLayout(new GridBagLayout());
        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            swapToScreen(mainPanel, BorderLayout.CENTER);
        });
        JButton register = new JButton("Register");
        JTextField username = new JTextField();
        JTextField email = new JTextField();
        JPasswordField pass = new JPasswordField();
        JPasswordField repass = new JPasswordField();
        register.addActionListener(e -> {
            if (new String(pass.getPassword()).equals(new String(repass.getPassword()))) {
                client.sendRegisterRequest(username.getText(), new String(pass.getPassword()), email.getText());
            } else {
                JOptionPane.showMessageDialog(this, "passwords do not match!", "Register Request",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 150;
        c.ipady = 5;
        c.insets = new Insets(0, 0, 10, 0);
        registerPanel.add(new JLabel("<html><font color='red'>username: </font></html>"), c);
        c.gridx = 1;
        c.gridy = 0;
        c.ipadx = 300;
        registerPanel.add(username, c);
        c.gridx = 0;
        c.gridy = 1;
        c.ipadx = 150;
        registerPanel.add(new JLabel("<html><font color='red'>email: </font></html>"), c);
        c.gridx = 1;
        c.gridy = 1;
        c.ipadx = 300;
        registerPanel.add(email, c);
        c.gridx = 0;
        c.gridy = 2;
        c.ipadx = 150;
        registerPanel.add(new JLabel("<html><font color='red'>password: </font></html>"), c);
        c.gridx = 1;
        c.gridy = 2;
        c.ipadx = 300;
        registerPanel.add(pass, c);
        c.gridx = 0;
        c.gridy = 3;
        c.ipadx = 150;
        registerPanel.add(new JLabel("<html><font color='red'>re-password: </font></html>"), c);
        c.gridx = 1;
        c.gridy = 3;
        c.ipadx = 300;
        registerPanel.add(repass, c);
        c.gridx = 0;
        c.gridy = 4;
        c.ipadx = 150;
        c.gridwidth = 2;
        c.insets = new Insets(0, 0, 0, 0);
        registerPanel.add(register, c);
        c.gridy = 5;
        registerPanel.add(back, c);
    }

    public void swapToScreen(JPanel panel, String layout) {
        removeAll();
        add(panel, layout);
        validate();
        repaint();
    }

}
