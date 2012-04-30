package org.xbucchiotty.function;

import java.util.ArrayList;
import java.util.Collection;

/**
 * User: xbucchiotty
 * Date: 27/04/12
 * Time: 19:16
 */
public abstract class FunctionHelper {


    public static <I, O> O reduce(Reducer<I, O> reducer, Collection<I> inputs) {
        for (I input : inputs) {
            reducer.agrege(input);
        }

        return reducer.getResult();
    }

    public static <I, O> O reduce(Reducer<I, O> reducer, I... inputs) {
        for (I input : inputs) {
            reducer.agrege(input);
        }

        return reducer.getResult();
    }

    public static <I, O> Collection<O> map(Converter<I, O> converter, Collection<I> inputs) {
        Collection<O> convertedObjects = new ArrayList<O>(inputs.size());
        for (I input : inputs) {
            convertedObjects.add(converter.convert(input));
        }

        return convertedObjects;
    }

}
