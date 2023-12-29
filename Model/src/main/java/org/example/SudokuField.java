package org.example;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;

public class SudokuField implements Serializable,Cloneable, Comparable<SudokuField> {

    private static final Logger logger = LogManager.getLogger(SudokuField.class);
    private static final long serialVersionUID = 1L;
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        logger.debug("Setting SudokuField value to: " + value);
        this.value = value;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("value", value)
                .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SudokuField that = (SudokuField) obj;
        return new EqualsBuilder()
                .append(value, that.value)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(value)
                .toHashCode();
    }

    @Override
    public SudokuField clone() throws CloneNotSupportedException {
        return (SudokuField) super.clone();
    }

    @Override
    public int compareTo(SudokuField o) {
        return Integer.compare(this.value, o.value);
    }

    public String getStringValue() {
        return value == 0 ? "" : String.valueOf(value);
    }

    public void setStringValue(String value) {
        this.value = value.isEmpty() ? 0 : Integer.parseInt(value);
    }
}