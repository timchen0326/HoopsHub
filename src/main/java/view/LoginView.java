package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import interface_adapter.account.AccountController;
import java.awt.Color;

public class LoginView extends JPanel {

    private final JTextField usernameField = new JTextField(20);
    private final JPasswordField passwordField = new JPasswordField(20);
    private final JButton loginButton = new JButton("Login");
    private final JButton switchToSignUpButton = new JButton("Don't have an account?");
    private final JLabel errorLabel = new JLabel("Invalid account or password");

    private final AccountController controller;
    private final ActionListener switchToSignUpListener;

    public LoginView(AccountController controller, ActionListener switchToSignUpListener) {
        this.controller = controller;
        this.switchToSignUpListener = switchToSignUpListener;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);

        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);
        add(errorLabel);

        add(loginButton);
        add(Box.createVerticalStrut(10));

        add(switchToSignUpButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                controller.loginUser(username, password);
            }
        });

        switchToSignUpButton.addActionListener(switchToSignUpListener);
    }

    public void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }

    public void hideError() {
        errorLabel.setVisible(false);
    }
}
