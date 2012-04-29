package org.xbucchiotty.function.excel;

import org.xbucchiotty.function.Converter;

/**
 * User: xbucchiotty
 * Date: 27/04/12
 * Time: 19:15
 */
public interface Column<I,O> {

    String getTitle();

    O extractData(I input);
}
