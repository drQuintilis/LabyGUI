import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Task125 extends JFrame {
    private JTextField textField;
    private JPasswordField passwordField;
    private JTextArea textArea;
    private JButton showButton;
    private JPanel contentPane;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    Task125 frame = new Task125();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        });
    }

    public Task125() throws HeadlessException {
        this("Log in");
    }

    public Task125(String title) throws HeadlessException {
        super(title); // Wywołujemy konstruktor klasy bazowej, aby ustawić tytuł okna

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 500);

        // Tworzymy JPanel
        contentPane = new JPanel();
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
        showButton = new JButton("Pokaż login i hasło");
        showButton.setBounds(10, 110, 250, 20);
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printTextFields();
            }
        });
        contentPane.add(showButton);

        JButton changeColorButton = new JButton("Zmień kolor");
        changeColorButton.setBounds(300, 30, 120, 20);
        changeColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeColor();
            }
        });
        contentPane.add(changeColorButton);

        JButton changeSizeButton = new JButton("Zmień rozmiar");
        changeSizeButton.setBounds(300, 60, 120, 20);
        changeSizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeSize();
            }
        });
        contentPane.add(changeSizeButton);

        JButton changePositionButton = new JButton("Zmień pozycje");
        changePositionButton.setBounds(300, 90, 120, 20);
        changePositionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePosition();
            }
        });
        contentPane.add(changePositionButton);

        JButton checkPasswordButton = new JButton("Sprawdź siłę hasła");
        checkPasswordButton.setBounds(170, 300, 200, 30);
        checkPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkPassword();
            }
        });
        contentPane.add(checkPasswordButton);

        // Dodajemy JTextArea
        textArea = new JTextArea();
        textArea.setBounds(10, 140, 250, 100);
        contentPane.add(textArea);

    }

    private void checkPassword() {
        String password = new String(passwordField.getPassword());
        String message;
        if (password.isEmpty()) {
            message = "Nie wprowadzono hasła!";
        } else if (password.length() < 8) {
            message = "Hasło jest za krótkie!";
        } else if (!password.matches(".*[A-Z].*")) {
            message = "Hasło musi zawierać co najmniej jedną wielką literę!";
        } else if (!password.matches(".*[a-z].*")) {
            message = "Hasło musi zawierać co najmniej jedną małą literę!";
        } else if (!password.matches(".*\\d.*")) {
            message = "Hasło musi zawierać co najmniej jedną cyfrę!";
        } else if (!password.matches(".*[!@#$%^&*()].*")) {
            message = "Hasło musi zawierać co najmniej jeden znak specjalny!";
        } else {
            message = "Hasło jest silne!";
        }
        JOptionPane.showMessageDialog(this, message, "Siła hasła", JOptionPane.INFORMATION_MESSAGE);
    }

    private void changeColor() {
        JComponent selectedElement = chooseElement();
        if (selectedElement == null) {
            return;
        }
        Color color = JColorChooser.showDialog(Task125.this, "Wybierz kolor", Color.WHITE);
        selectedElement.setBackground(color);
    }

    private void changeSize() {
        JComponent selectedElement = chooseElement(new String[]{"Pole tekstowe", "Pole hasłowe",
                "Pole wyświetlenia tekstu", "Przycisk 'Pokaż login i hasło'"});
        if (selectedElement == null) {
            return;
        }
        Integer[] coordinates = getCoordinates("Podaj nowy rozmiar w formacie: szerokość, wysokość",
                "Zmiana rozmiaru");
        if (coordinates == null) {
            return;
        }
        selectedElement.setSize(coordinates[0], coordinates[1]);
    }

    private void changePosition() {
        JComponent selectedElement = chooseElement(new String[]{"Pole tekstowe", "Pole hasłowe",
                "Pole wyświetlenia tekstu", "Przycisk 'Pokaż login i hasło'"});
        if (selectedElement == null) {
            return;
        }
        Integer[] coordinates = getCoordinates("Podaj nową pozycję w formacie: x, y", "Zmiana pozycji");
        if (coordinates == null) {
            return;
        }
        selectedElement.setLocation(coordinates[0], coordinates[1]);
    }

    private Integer[] getCoordinates(String message, String title) {
        String newSize = JOptionPane.showInputDialog(this, message, title, JOptionPane.PLAIN_MESSAGE);
        if (newSize != null && newSize.contains(",")) {
            String[] sizes = newSize.split(",");
            try {
                int width = Integer.parseInt(sizes[0].trim());
                int height = Integer.parseInt(sizes[1].trim());
                return new Integer[]{width, height};
            } catch (NumberFormatException|ArrayIndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(this, "Nieprawidłowy format rozmiaru",
                        "Błąd", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        return null;
    };

    private void printTextFields() {
        String text = textField.getText(); // Uzyskujemy tekst z textField
        String password = passwordField.getText(); // Uzyskujemy tekst z passwordField
        textArea.setText("Login: " + text + "\nPassword: " + password); // Ustawiamy tekst w textArea
    }

    private JComponent chooseElement(){
        String[] options = {"Pole tekstowe", "Pole hasłowe", "Pole wyświetlenia tekstu",
                "Przycisk 'Pokaż login i hasło'", "Tło"};
        return chooseElement(options);
    }

    private JComponent chooseElement(String[] options){
        String selectedOption = (String) JOptionPane.showInputDialog(
                Task125.this,
                "Wybierz opcję:",
                "Wybór opcji",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
        if (selectedOption == null) {
            return null;
        }
        switch (selectedOption) {
            case "Pole tekstowe":
                return textField;
            case "Pole hasłowe":
                return passwordField;
            case "Pole wyświetlenia tekstu":
                return textArea;
            case "Przycisk 'Pokaż login i hasło'":
                return showButton;
            case "Tło":
                return contentPane;
        }
        return null;
    }
}
