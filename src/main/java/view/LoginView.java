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
    private final JLabel errorLabel = new JLabel("Invalid account or password");  // Error message label

    private AccountController controller;
    private ActionListener switchToSignUpListener;

    public LoginView(AccountController controller, ActionListener switchToSignUpListener) {
        this.controller = controller;
        this.switchToSignUpListener = switchToSignUpListener;

        // Set layout and add components
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);

        // Configure error label
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);  // Initially hidden
        add(errorLabel);

        // Add login button
        add(loginButton);
        add(Box.createVerticalStrut(10));  // Spacing

        // Add "Don't have an account?" button
        add(switchToSignUpButton);

        // Login button action
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                controller.loginUser(username, password);
            }
        });

        // "Don't have an account?" button action
        switchToSignUpButton.addActionListener(switchToSignUpListener);
    }

    // Method to display error message
    public void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }

    // Method to hide error message
    public void hideError() {
        errorLabel.setVisible(false);
    }
}
