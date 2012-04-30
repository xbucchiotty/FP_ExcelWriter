package org.xbucchiotty.function.excel;

import org.xbucchiotty.function.Converter;

import java.util.List;

/**
 * User: xbucchiotty
 * Date: 30/04/12
 * Time: 08:28
 */
public class ExcelWrapperForColumnGroup {

    public static <T> Converter<ColumnGroup<T>, String> extractTitle() {
        return new Converter<ColumnGroup<T>, String>() {
            @Override
            public String convert(ColumnGroup<T> input) {
                return input.getTitle();
            }
        };
    }

    public static <T> Converter<ColumnGroup<T>, List<Column<T, ?>>> extractColumn() {
        return new Converter<ColumnGroup<T>, List<Column<T, ?>>>() {
            @Override
            public List<Column<T, ?>> convert(ColumnGroup<T> input) {
                return input.getColumns();
            }
        };
    }

}
