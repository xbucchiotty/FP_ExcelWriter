package org.xbucchiotty.function.excel;

import org.xbucchiotty.excel.Sheet;
import org.xbucchiotty.function.Reducer;

import static org.xbucchiotty.excel.Sheet.setCellValue;

/**
 * User: xbucchiotty
 * Date: 28/04/12
 * Time: 09:38
 */
public abstract class ExcelHelper {

    public static Reducer<String> writeToExcelLine(final Sheet sheet, final int row, final int firstColumn) {
        return new Reducer<String>() {
            int col = firstColumn;

            @Override
            public void agrege(String input) {
                setCellValue(sheet, row, col++, input);
            }
        };
    }

}
