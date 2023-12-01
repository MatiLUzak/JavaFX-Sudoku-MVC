package org.example;

public enum Difficulty {
    EASY(10), // Łatwy - usunięcie 10 pól
    MEDIUM(20), // Średni - usunięcie 20 pól
    HARD(30); // Trudny - usunięcie 30 pól

    private final int fieldsToRemove;

    Difficulty(int fieldsToRemove) {
        this.fieldsToRemove = fieldsToRemove;
    }

    public int getFieldsToRemove() {
        return fieldsToRemove;
    }
}

