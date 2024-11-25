package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import interface_adapter.account.AccountController;

/**
 * The LoginView class represents the user interface for logging into the application.
 * It includes input fields for username and password, a login button, and a link to switch to sign-up.
 */

public class LoginView extends JPanel {

    private static final int VERTICAL_SPACING = 10;
    private final JTextField usernameField = new JTextField(20);
    private final JPasswordField passwordField = new JPasswordField(20);
    private final JButton loginButton = new JButton("Login");
    private final JButton switchToSignUpButton = new JButton("Don't have an account?");
    private final JLabel errorLabel = new JLabel("Invalid account or password");

    public LoginView(AccountController controller, ActionListener switchToSignUpListener) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);

        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);
        add(errorLabel);

        add(loginButton);
        add(Box.createVerticalStrut(VERTICAL_SPACING));

        add(switchToSignUpButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String username = usernameField.getText();
                final String password = new String(passwordField.getPassword());
                try {
                    controller.loginUser(username, password);
                }
                catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        switchToSignUpButton.addActionListener(switchToSignUpListener);
    }

}
