package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import interface_adapter.account.AccountController;

/**
 * A view for account creation, including input fields for username and password
 * and a button to submit the account creation request.
 */
public class AccountCreationView extends JPanel {

    private final JTextField usernameField = new JTextField(20);
    private final JPasswordField passwordField = new JPasswordField(20);
    private final JButton createButton = new JButton("Create Account");
    private final AccountController controller;

    /**
     * Constructs the AccountCreationView with the given controller.
     *
     * @param controller the controller handling account creation logic
     */
    public AccountCreationView(AccountController controller) {
        this.controller = controller;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(createButton);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String username = usernameField.getText();
                final String password = new String(passwordField.getPassword());
                try {
                    controller.createAccount(username, password);
                }
                catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
