import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private JTextField textField;
    private JPasswordField passwordField;
    private JTextArea textArea;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainWindow frame = new MainWindow();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        });
    }

    public MainWindow() throws HeadlessException {
        this("Log in");
    }

    public MainWindow(String title) throws HeadlessException {
        super(title); // Wywołujemy konstruktor klasy bazowej, aby ustawić tytuł okna

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 500);

        // Tworzymy JPanel
        JPanel contentPane = new JPanel();
        // Ustawiamy layout na null, aby móc ręcznie ustawiać pozycje elementów
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Dodajemy JLabel
        JLabel login = new JLabel("Wprowadź login");
        login.setBounds(10, 10, 100, 20);
        JLabel password = new JLabel("Wprowadź hasło");
        password.setBounds(10, 50, 100, 20);
        contentPane.add(login, null);
        contentPane.add(password, null);

        // Dodajemy JTextField
        textField = new JTextField(20); // Tworzymy pole tekstowe o szerokości 20 znaków
        textField.setBounds(10, 30, 250, 20);
        contentPane.add(textField);

        // Dodajemy JPasswordField
        passwordField = new JPasswordField(20); // Tworzymy pole hasłowe o szerokości 20 znaków
        passwordField.setBounds(10, 70, 250, 20);
        contentPane.add(passwordField);

        // Dodajemy JButton
        JButton button = new JButton("Pokaż login i hasło");
        button.setBounds(10, 110, 250, 20);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printTextFields();
            }
        });
        contentPane.add(button);

        // Dodajemy JTextArea
        textArea = new JTextArea();
        textArea.setBounds(10, 140, 250, 100);
        contentPane.add(textArea);

    }

    private void printTextFields() {
        String text = textField.getText(); // Uzyskujemy tekst z textField
        String password = passwordField.getText(); // Uzyskujemy tekst z passwordField
        textArea.setText("Text: " + text + "\nPassword: " + password); // Ustawiamy tekst w textArea
    }
}
