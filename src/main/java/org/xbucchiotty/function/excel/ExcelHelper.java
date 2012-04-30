package org.xbucchiotty.function.excel;

import org.xbucchiotty.excel.Sheet;
import org.xbucchiotty.function.Reducer;

import java.util.Date;

import static org.xbucchiotty.excel.Sheet.setCellValue;

/**
 * User: xbucchiotty
 * Date: 28/04/12
 * Time: 09:38
 */
public abstract class ExcelHelper {

    public static final String BLANK = "";

    public static Reducer<Object, Integer> writeObjectToExcelByRow(final Sheet sheet, final int firstRow, final int column) {
        return new Reducer<Object, Integer>() {
            int row = firstRow;

            @Override
            public void agrege(Object input) {
                if (input == null) {
                    setCellValue(sheet, row++, column, BLANK);
                }
                if (input instanceof Date) {
                    setCellValue(sheet, row++, column, (Date) input);
                } else if (input instanceof Double) {
                    setCellValue(sheet, row++, column, (Double) input);
                } else if (input instanceof String) {
                    setCellValue(sheet, row++, column, input.toString());
                }
            }

            @Override
            public Integer getResult() {
                return row;
            }
        };
    }

}
