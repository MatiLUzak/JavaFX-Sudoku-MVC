package org.example;

import org.example.exceptions.SudokuException;

public interface Dao<T> extends AutoCloseable {

    T read() throws SudokuException;

    void write(T obj) throws SudokuException;

    @Override
    void close() throws SudokuException;
}
