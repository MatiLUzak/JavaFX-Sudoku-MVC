package org.example;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class SudokuStructure implements Cloneable {
    protected List<SudokuField> fields;

    public SudokuStructure(List<SudokuField> fields) {
        if (fields == null) {
            throw new IllegalArgumentException("Fields array cannot be null");
        }
        this.fields = fields;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("fields", fields)
                .toString();
    }

    @Override
    //dopsiek sprawdzający czy program jest akutalny
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SudokuStructure that = (SudokuStructure) obj;
        return new EqualsBuilder()
                .append(fields, that.fields)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(fields)
                .toHashCode();
    }

    public boolean verify() {
        boolean[] seen = new boolean[9];
        for (SudokuField field : fields) {
            int value = field.getValue();
            if (value < 1 || value > 9 || seen[value - 1]) {
                return false;
            }
            seen[value - 1] = true;
        }
        return true;
    }

    @Override
    public SudokuStructure clone() {
        try {
            SudokuStructure cloned = (SudokuStructure) super.clone();
            cloned.fields = new ArrayList<>();
            for (SudokuField field : this.fields) {
                cloned.fields.add(field.clone());
            }
            return cloned;
        } catch (CloneNotSupportedException e) {
            // To wyjątek nie powinien wystąpić, ponieważ klasa implementuje Cloneable
            throw new InternalError(e);
        }
    }

}
