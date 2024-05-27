import java.awt.Component;
import javax.swing.*;
import javax.swing.table.*;

public class TableColumnAdjuster {
    private JTable table;

    public TableColumnAdjuster(JTable table) {
        this.table = table;
    }

    public void adjustColumns() {
        TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int maxWidth = 0;
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
                Object value = table.getValueAt(row, column);
                Component comp = cellRenderer.getTableCellRendererComponent(table, value, false, false, row, column);
                maxWidth = Math.max(comp.getPreferredSize().width, maxWidth);
            }
            columnModel.getColumn(column).setPreferredWidth(maxWidth + 10); // 加上一點空間
        }
    }
}
