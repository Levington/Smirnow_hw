package org.example;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Класс для подключения к базе данных
class DatabaseConnector {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/nomer_4";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "12345678";

    static {
        try {
            // Загрузка драйвера MySQL JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    }
}

// Основной класс приложения
class MusicApp extends JFrame {
    private JTextField txtSongName, txtAuthor, txtAlbum;
    private JButton btnAdd, btnUpdate;
    private JPanel panel;

    public MusicApp() {
        // Создание интерфейса
        setTitle("Music Application");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel = new JPanel();
        getContentPane().add(panel);

        panel.add(new JLabel("Название песни:"));
        txtSongName = new JTextField(20);
        panel.add(txtSongName);

        panel.add(new JLabel("Автор:"));
        txtAuthor = new JTextField(20);
        panel.add(txtAuthor);

        panel.add(new JLabel("Альбом:"));
        txtAlbum = new JTextField(20);
        panel.add(txtAlbum);

        btnAdd = new JButton("Добавить");
        btnUpdate = new JButton("Изменить");

        panel.add(btnAdd);
        panel.add(btnUpdate);

        // Обработчики событий
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addSong();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateSong();
            }
        });
    }

    private void addSong() {
        String songName = txtSongName.getText();
        String author = txtAuthor.getText();
        String album = txtAlbum.getText();

        // SQL запрос для добавления песни
        String sql = "INSERT INTO songs (name, author, album) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, songName);
            pstmt.setString(2, author);
            pstmt.setString(3, album);

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Песня добавлена успешно!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ошибка при добавлении песни.");
        }
    }

    private void updateSong() {
        // Предполагается, что у вас есть уникальный идентификатор для песен
        // Здесь должен быть код для обновления информации о песне
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MusicApp().setVisible(true);
            }
        });
    }
}

// Не забудьте заменить 'yourDatabase', 'yourUsername' и 'yourPassword' на реальные значения.
