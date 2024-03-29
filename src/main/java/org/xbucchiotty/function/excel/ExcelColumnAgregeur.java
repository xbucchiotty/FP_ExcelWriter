package org.xbucchiotty.function.excel;

import org.xbucchiotty.excel.Sheet;
import org.xbucchiotty.function.Reducer;

import java.util.Collection;

import static org.xbucchiotty.function.FunctionHelper.map;
import static org.xbucchiotty.function.FunctionHelper.reduce;

/**
 * User: xbucchiotty
 * Date: 30/04/12
 * Time: 08:43
 */
public class ExcelColumnAgregeur<T> implements Reducer<Column<T, ?>, Integer> {

    private Sheet sheet;
    private final Collection<T> beans;
    private final int initialRow;
    private int column;

    public ExcelColumnAgregeur(Collection<T> beans, Sheet sheet, int initialRow, int initialColumn) {
        this.beans = beans;
        this.sheet = sheet;
        this.initialRow = initialRow;
        this.column = initialColumn;
    }

    @Override
    public void agrege(Column<T, ?> input) {
        Reducer<Object, Integer> writer = ExcelHelper.writeObjectToExcelByRow(sheet, initialRow, column++);

        if (input.isVisible()) {
            reduce(
                    writer,
                    input.getTitle()
            );

            reduce(
                    writer,
                    map(
                            ExcelWrapperForColumn.<T>extractData(input),
                            beans
                    )
            );
        }
    }


    @Override
    public Integer getResult() {
        return column;
    }
}
