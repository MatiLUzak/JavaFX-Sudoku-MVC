package org.example;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class SudokuStructure implements Cloneable {

    private static final Logger logger = LogManager.getLogger(SudokuStructure.class);
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
                logger.debug("Verification failed for SudokuStructure: invalid value or duplicate found");
                return false;
            }
            seen[value - 1] = true;
        }
        logger.debug("Verification passed for SudokuStructure");
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
            logger.debug("Successfully cloned SudokuStructure");
            return cloned;
        } catch (CloneNotSupportedException e) {
            logger.error("CloneNotSupportedException in SudokuStructure", e);
            throw new InternalError(e);
        }
    }

}
