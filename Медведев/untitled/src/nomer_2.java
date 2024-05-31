import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class FilmManagerApp extends JFrame {

    private List<Film> filmCollection;
    private JTextField searchField;
    private JTextArea filmListArea;

    public FilmManagerApp() {
        filmCollection = new ArrayList<>();

        setTitle("Film Manager App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JButton addButton = new JButton("Add Film");
        panel.add(addButton, BorderLayout.NORTH);

        searchField = new JTextField();
        panel.add(searchField, BorderLayout.SOUTH);

        filmListArea = new JTextArea();
        panel.add(new JScrollPane(filmListArea), BorderLayout.CENTER);

        add(panel);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog("Enter film title:");
                if (title != null && !title.isEmpty()) {
                    filmCollection.add(new Film(title));
                    updateFilmList();
                }
            }
        });

        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchField.getText().toLowerCase();
                StringBuilder result = new StringBuilder();
                for (Film film : filmCollection) {
                    if (film.getTitle().toLowerCase().contains(query)) {
                        result.append(film.getTitle()).append("\n");
                    }
                }
                filmListArea.setText(result.toString());
            }
        });

        setVisible(true);
    }

    private void updateFilmList() {
        StringBuilder filmList = new StringBuilder();
        for (Film film : filmCollection) {
            filmList.append(film.getTitle()).append("\n");
        }
        filmListArea.setText(filmList.toString());
    }

    public static void main(String[] args) {
        new FilmManagerApp();
    }
}

class Film {
    private String title;

    public Film(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}