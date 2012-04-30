package org.xbucchiotty.function.excel;

import org.xbucchiotty.function.Converter;

/**
 * User: xbucchiotty
 * Date: 30/04/12
 * Time: 08:28
 */
public class ExcelWrapperForColumn<I> {
    private final Column<I, ?> column;

    public ExcelWrapperForColumn(Column<I, ?> column) {
        this.column = column;
    }

    public static <T> Converter<ExcelWrapperForColumn<T>, String> extractTitle() {
        return new Converter<ExcelWrapperForColumn<T>, String>() {
            @Override
            public String convert(ExcelWrapperForColumn<T> input) {
                return input.column.getTitle();
            }
        };
    }
}
