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

    public static Reducer<String> writeTitleToExcelLine(final Sheet sheet, final int row, final int firstColumn) {
        return new Reducer<String>() {
            int col = firstColumn;

            @Override
            public void agrege(String input) {
                setCellValue(sheet, row, col++, input);
            }
        };
    }

    public static Reducer<Object> writeObjectToExcelColumn(final Sheet sheet, final int firstRow, final int column) {
        return new Reducer<Object>() {
            int row = firstRow;

            @Override
            public void agrege(Object input) {
                if(input == null){
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
        };
    }

}
