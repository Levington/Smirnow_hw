-- Создание таблицы "Исполнители"
CREATE TABLE Исполнители (
    id INT PRIMARY KEY,
    имя VARCHAR(100) NOT NULL
);

-- Создание таблицы "Альбомы"
CREATE TABLE Альбомы (
    id INT PRIMARY KEY,
    название VARCHAR(100) NOT NULL,
    год INT,
    исполнитель_id INT,
    FOREIGN KEY (исполнитель_id) REFERENCES Исполнители(id)
);

-- Создание таблицы "Песни"
CREATE TABLE Песни (
    id INT PRIMARY KEY,
    название VARCHAR(100) NOT NULL,
    длительность INT,
    альбом_id INT,
    FOREIGN KEY (альбом_id) REFERENCES Альбомы(id)
);