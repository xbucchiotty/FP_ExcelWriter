package org.xbucchiotty.function;

/**
 * User: xbucchiotty
 * Date: 27/04/12
 * Time: 19:16
 */
public interface Reducer<I, O> {

    void agrege(I input);

    O getResult();
}
