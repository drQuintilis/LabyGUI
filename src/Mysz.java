import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Mysz extends JFrame {
    private JPanel contentPane;
    private JButton runawayButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    Mysz frame = new Mysz();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        });
    }

    public Mysz() throws HeadlessException {
        this("Myszka");
    }

    public Mysz(String title) throws HeadlessException {
        super(title); // Wywołujemy konstruktor klasy bazowej, aby ustawić tytuł okna

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 500);

        // Tworzymy JPanel
        contentPane = new JPanel();
        // Ustawiamy layout na null, aby móc ręcznie ustawiać pozycje elementów
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Dodajemy "uciekający" przycisk
        runawayButton = new JButton("Kliknij mnie");
        runawayButton.setBounds(200, 200, 120, 30);
        contentPane.add(runawayButton);

        runawayButton.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int mouseX = e.getXOnScreen();
                int mouseY = e.getYOnScreen();
                int buttonX = runawayButton.getLocationOnScreen().x;
                int buttonY = runawayButton.getLocationOnScreen().y;
                int buttonWidth = runawayButton.getWidth();
                int buttonHeight = runawayButton.getHeight();

                // Strefa bezpieczna (20 pikseli od prawej)
                int safeZone = 20;

                if (mouseX > buttonX - safeZone && mouseX < buttonX + buttonWidth + safeZone &&
                        mouseY > buttonY - safeZone && mouseY < buttonY + buttonHeight + safeZone) {
                    // Przesuwamy przycisk na nowe losowe położenie
                    int newX = (int) (Math.random() * (contentPane.getWidth() - buttonWidth));
                    int newY = (int) (Math.random() * (contentPane.getHeight() - buttonHeight));
                    runawayButton.setLocation(newX, newY);
                }
            }
        });
    }
}