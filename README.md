# JavaFX-Sudoku-MVC

## 📌 Opis projektu

**JavaFX-Sudoku-MVC** to aplikacja napisana w języku **Java**, która implementuje grę **Sudoku** z wykorzystaniem biblioteki **JavaFX** do stworzenia interfejsu graficznego. Projekt jest zorganizowany zgodnie z wzorcem **Model-View-Controller (MVC)**, co zapewnia czytelność i modularność kodu.

## 🛠 Wymagania

Aby uruchomić projekt, potrzebujesz:

- **Java Development Kit (JDK) 11** lub nowszy
- **Maven** do zarządzania zależnościami i budowania projektu

## 🚀 Instalacja i uruchomienie

1. **Klonowanie repozytorium:**

   ```bash
   git clone https://github.com/MatiLUzak/Sudoku.git
   cd Sudoku
   ```

2. **Budowanie projektu za pomocą Mavena:**

   ```bash
   mvn clean install
   ```

3. **Uruchomienie aplikacji:**

   ```bash
   mvn javafx:run
   ```

   *Upewnij się, że w pliku `pom.xml` masz skonfigurowany plugin `javafx-maven-plugin`.*

## 📂 Struktura projektu

```
Sudoku/
├── .idea/
├── Model/
│   ├── Board.java
│   ├── Cell.java
│   └── ...
├── SudokuGameView/
│   ├── Main.java
│   ├── SudokuController.java
│   ├── SudokuView.fxml
│   └── ...
├── logs/
├── .gitignore
├── checkstyle2023.xml
├── pom.xml
└── README.md
```

- **Model/** – zawiera klasy reprezentujące logikę gry, takie jak `Board` i `Cell`.
- **SudokuGameView/** – zawiera klasy odpowiedzialne za interfejs użytkownika, w tym kontroler `SudokuController` i plik FXML `SudokuView.fxml`.
- **logs/** – katalog przeznaczony na pliki logów.
- **checkstyle2023.xml** – plik konfiguracyjny dla narzędzia Checkstyle.
- **pom.xml** – plik konfiguracyjny Mavena.

## ✍️ Autor

- **MatiLUzak** – [GitHub](https://github.com/MatiLUzak)

## 📜 Licencja

Ten projekt jest licencjonowany na podstawie licencji MIT. Szczegóły znajdują się w pliku `LICENSE`.
