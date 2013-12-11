package net.ostis.confman.ui.participant;

public interface TableModel {

    int getColumnCount();

    int getRowCount();

    String getColumnName(int columnIndex);

    Object getValue(int row, int column);
}
