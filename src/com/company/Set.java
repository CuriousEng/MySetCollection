package com.company;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Iterator;

public class Set<E> extends AbstractSet implements Serializable, Cloneable {
    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
