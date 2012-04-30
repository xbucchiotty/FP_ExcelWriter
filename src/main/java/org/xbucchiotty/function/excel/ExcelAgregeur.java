package org.xbucchiotty.function.excel;

import org.xbucchiotty.excel.Sheet;
import org.xbucchiotty.function.Reducer;

import java.util.Collection;
import java.util.List;

import static org.xbucchiotty.function.FunctionHelper.map;
import static org.xbucchiotty.function.FunctionHelper.reduce;
import static org.xbucchiotty.function.excel.ExcelHelper.writeObjectToExcelColumn;
import static org.xbucchiotty.function.excel.ExcelHelper.writeTitleToExcelLine;

/**
 * User: xbucchiotty
 * Date: 30/04/12
 * Time: 08:43
 */
public class ExcelAgregeur<T> {

    private final List<Column<T, ?>> columns;
    private Sheet sheet;
    private int row;
    private int column;
    private final int initialRow;
    private final int initialColumn;

    public ExcelAgregeur(List<Column<T, ?>> columns, Sheet sheet, int initialRow, int initialColumn) {
        this.columns = columns;
        this.sheet = sheet;
        this.row = initialRow;
        this.column = initialColumn;
        this.initialRow = initialRow;
        this.initialColumn = initialColumn;
    }

    public void agrege(Collection<T> beans) {
        //ECRITURE DES TITRES
        writeLabels();

        reduce(new BeanAgregeurForExcel(beans), columns);
    }

    private void writeLabels() {
        if (row == initialRow) {
            Collection<String> titles = map(ExcelWrapperForColumn.<T>extractTitle(), columns);
            reduce(writeTitleToExcelLine(sheet, row++, initialColumn), titles);
        }
    }

    private class BeanAgregeurForExcel implements Reducer<Column<T, ?>> {

        private final Collection<T> beans;

        private BeanAgregeurForExcel(Collection<T> beans) {
            this.beans = beans;
        }

        @Override
        public void agrege(Column<T, ?> input) {
            reduce(writeObjectToExcelColumn(sheet, row, column++),
                    map(ExcelWrapperForColumn.extractData(input), beans)
            );
        }
    }
}
