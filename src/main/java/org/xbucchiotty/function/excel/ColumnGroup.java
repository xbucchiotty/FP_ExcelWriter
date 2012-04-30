package org.xbucchiotty.function.excel;

import java.util.List;

/**
 * User: xbucchiotty
 * Date: 30/04/12
 * Time: 12:33
 */
public interface ColumnGroup<I> {

    String getTitle();

    List<Column<I, ?>> getColumns();
}
