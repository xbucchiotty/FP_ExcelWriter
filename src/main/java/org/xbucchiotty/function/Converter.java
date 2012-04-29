package org.xbucchiotty.function;

/**
     * User: xbucchiotty
     * Date: 27/04/12
     * Time: 19:19
     */
    public interface Converter<I, O> {

        O convert(I input);
    }
