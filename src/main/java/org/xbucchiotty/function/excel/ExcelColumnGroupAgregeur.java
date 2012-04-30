package org.xbucchiotty.function.excel;

import org.xbucchiotty.excel.Sheet;
import org.xbucchiotty.function.Reducer;

import java.util.Collection;
import java.util.List;

import static org.xbucchiotty.function.FunctionHelper.map;
import static org.xbucchiotty.function.FunctionHelper.reduce;
import static org.xbucchiotty.function.excel.ExcelHelper.writeTitleToExcelByColumn;

/**
 * User: xbucchiotty
 * Date: 30/04/12
 * Time: 12:40
 */
public class ExcelColumnGroupAgregeur<T> implements Reducer<T> {

    private final List<ColumnGroup<T>> columnGroups;
    private Sheet sheet;
    private int column;
    private final int initialRow;

    public ExcelColumnGroupAgregeur(List<ColumnGroup<T>> columnGroups, Sheet sheet, int initialRow, int initialColumn) {
        this.columnGroups = columnGroups;
        this.sheet = sheet;
        this.column = initialColumn;
        this.initialRow = initialRow;
    }

    @Override
    public void agrege(T bean) {
        //ECRITURE DES TITRES
        writeTitle();


    }

    public void agrege(Collection<T> beans) {
        reduce(this, beans);
    }

    private void writeTitle() {
        reduce(
                writeTitleToExcelByColumn(sheet, initialRow, column++),
                map(
                        ExcelWrapperForColumnGroup.<T>extractTitle(),
                        columnGroups
                )
        );
    }
}
