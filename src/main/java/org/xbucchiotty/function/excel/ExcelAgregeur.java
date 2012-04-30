package org.xbucchiotty.function.excel;

import org.xbucchiotty.excel.Sheet;

import java.util.Collection;
import java.util.List;

import static org.xbucchiotty.function.FunctionHelper.map;
import static org.xbucchiotty.function.FunctionHelper.reduce;

/**
 * User: xbucchiotty
 * Date: 30/04/12
 * Time: 08:43
 */
public class ExcelAgregeur<T> {

    private final Collection<ExcelWrapperForColumn<T>> columns;
    private Sheet sheet;
    private int row;
    private int column;

    private ExcelAgregeur(Collection<ExcelWrapperForColumn<T>> columns, Sheet sheet, int initialRow, int initialColumn) {
        this.columns = columns;
        this.sheet = sheet;
        this.row = initialRow;
        this.column = initialColumn;
    }

    public void agrege(Collection<T> beans) {
        //ECRITURE DES TITRES
        writeLabels();
    }

    private void writeLabels() {
        Collection<String> titles = map(ExcelWrapperForColumn.<T>extractTitle(), columns);
        reduce(ExcelHelper.writeToExcelLine(sheet, row++, column), titles);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public static <T> ExcelAgregeur<T> getInstance(List<Column<T, ?>> columns, Sheet sheet, int initialRow, int initialColumn) {
        return new ExcelAgregeur<T>(
                map(ExcelHelper.<T>wrapColumnForExcel(), columns),
                sheet, initialRow, initialColumn);
    }
}
