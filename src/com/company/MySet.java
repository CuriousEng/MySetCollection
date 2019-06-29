package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class MySet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E> {
    private static final Object PRESENT = new Object(); //заглушка
    private transient HashMap<E,Object> map;

    public MySet() {
        map = new HashMap<>();
    }

    public MySet(Collection<? extends E> collection) {
        map = new HashMap<>(Math.max(16, (int)(collection.size()/.75f)+1));
        this.addAll(collection);
    }

    @Override
    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }

    @Override
    public Iterator iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean remove(Object value) {
        return map.remove(value) == null;
    }

    @Override
    public boolean contains(Object key) {
        return map.containsKey(key);
    }

    @Override
    public Object clone() {
        try {
            MySet<E> set = new MySet<>();
            set.map = (HashMap<E, Object>) map.clone();
            return set;
        } catch (Exception e){
            throw new InternalError(e);
        }
    }

    private void writeObject (ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
        out.writeFloat(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
        out.writeInt(map.size());
        for (E e : map.keySet()) {
            out.writeObject(e);
        }
    }

    private void readObject (ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        int capacity = in.readInt();
        float loadFactor = in.readFloat();
        int size = in.readInt();

        map = new HashMap<>(capacity, loadFactor);

        for (int i = 0; i < size; i++) {
            map.put((E) in.readObject(), PRESENT);
        }
    }
}
