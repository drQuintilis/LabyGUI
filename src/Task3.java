import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class Task3 extends JFrame {
    private JPanel contentPane;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    Task3 frame = new Task3();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        });
    }

    public Task3() throws HeadlessException {
        this("Log in");
    }

    public Task3(String title) throws HeadlessException {
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
        JLabel label = new JLabel("");
        label.setBounds(10, 10, 200, 20);
        contentPane.add(label, null);
        String name = JOptionPane.showInputDialog(this, "Napisz swoje imię",
                "Imię użytkownika", JOptionPane.PLAIN_MESSAGE);
        if (name == null) System.exit(0);
        else if (name.isEmpty()) label.setText("Witaj, Anonimowy Użytkownik!");
        else label.setText("Witaj, " + name + "!");
    }
}