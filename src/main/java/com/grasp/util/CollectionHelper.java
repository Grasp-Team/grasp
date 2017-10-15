package com.grasp.util;

import java.util.Collection;

public class CollectionHelper {

    public static <E> boolean isEmpty(Collection<E> collection) {
        return collection == null || collection.isEmpty();
    }

}
