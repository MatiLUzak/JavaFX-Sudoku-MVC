package org.example;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

public class SudokuField {
    private int value;
    private PropertyChangeSupport support;

    public SudokuField() {
        support =new PropertyChangeSupport(this);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        int oldValue = this.value;
        this.value = value;
        support.firePropertyChange("value",oldValue,this.value);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}
