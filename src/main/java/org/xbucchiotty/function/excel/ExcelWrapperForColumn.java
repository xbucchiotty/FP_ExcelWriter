package org.xbucchiotty.function.excel;

import org.xbucchiotty.function.Converter;

/**
 * User: xbucchiotty
 * Date: 30/04/12
 * Time: 08:28
 */
public class ExcelWrapperForColumn {

    public static <T> Converter<Column<T, ?>, String> extractTitle() {
        return new Converter<Column<T, ?>, String>() {
            @Override
            public String convert(Column<T, ?> input) {
                return input.getTitle();
            }
        };
    }

    public static <T> Converter<T, Object> extractData(final Column<T, ?> column) {
        return new Converter<T, Object>() {
            @Override
            public Object convert(T input) {
                return column.extractData(input);
            }
        };
    }
}
