package org.xbucchiotty.function.excel;

import org.xbucchiotty.excel.Sheet;
import org.xbucchiotty.function.Reducer;

import java.util.Collection;

import static org.xbucchiotty.function.FunctionHelper.reduce;
import static org.xbucchiotty.function.excel.ExcelHelper.writeObjectToExcelByRow;

/**
 * User: xbucchiotty
 * Date: 30/04/12
 * Time: 12:40
 */
public class ExcelColumnGroupAgregeur<T> implements Reducer<ColumnGroup<T>, Integer> {

    private final Collection<T> beans;
    private Sheet sheet;
    private int column;
    private final int initialRow;

    public ExcelColumnGroupAgregeur(Collection<T> beans, Sheet sheet, int initialRow, int initialColumn) {
        this.beans = beans;
        this.sheet = sheet;
        this.column = initialColumn;
        this.initialRow = initialRow;
    }

    @Override
    public void agrege(ColumnGroup<T> group) {
        //ECRITURE DES TITRES
        int rowIndex = reduce(
                writeObjectToExcelByRow(sheet, initialRow, column),
                group.getTitle()
        );

        this.column = reduce(
                writeColumnToExcel(rowIndex),
                group.getColumns()
        );

    }

    private ExcelColumnAgregeur<T> writeColumnToExcel(int rowIndex) {
        return new ExcelColumnAgregeur<T>(
                beans,
                sheet,
                rowIndex,
                column
        );
    }

    public void agrege(Collection<ColumnGroup<T>> groups) {
        reduce(this, groups);
    }

    @Override
    public Integer getResult() {
        return column;
    }
}
