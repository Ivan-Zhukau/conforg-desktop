package net.ostis.confman.ui.participant;

public abstract class AbstractTableModel implements TableModel {

    AbstractTableModel() {

    }

    abstract void setValueAt(String Value, int row, int column);

}
