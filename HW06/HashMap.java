import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Your implementation of HashMap.
 *
 * @author Max Bentata
 * @version 1.0
 */
public class HashMap<K, V> implements HashMapInterface<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code INITIAL_CAPACITY}.
     *
     * Do not use magic numbers!
     *
     * Use constructor chaining.
     */
    public HashMap() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code initialCapacity}.
     *
     * You may assume {@code initialCapacity} will always be positive.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    public HashMap(int initialCapacity) {
        table = (MapEntry<K, V>[]) new MapEntry[initialCapacity];
        size = 0;
    }

    @Override
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key or Value is not valid");
        }

        if (((float) size + 1) / table.length > MAX_LOAD_FACTOR) {
            resizeBackingTable(table.length * 2 + 1);
        }
        int idx = Math.abs(hash(key)) % table.length;
        int h = 1;
        int idxi = idx;

        while (true) {
            if (table[idx] == null) {
                table[idx] = new MapEntry<K, V>(key, value);
                size++;
                return null;
            } else if (table[idx] != null) {
                if (table[idx].isRemoved()) {
                    table[idx].setValue(value);
                    table[idx].setKey(key);
                    table[idx].setRemoved(false);
                    size++;
                    return null;
                } else if (table[idx].getKey().equals(key)) {
                    V old = table[idx].getValue();
                    table[idx].setValue(value);
                    if (table[idx].isRemoved()) {
                        size++;
                        table[idx].setRemoved(false);
                    }
                    return old;
                } else {    // Check if different key
                    idx = (idxi + h * h++) % table.length;
                }
            }
        }
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is not valid");
        }
        int idx = Math.abs(hash(key)) % table.length;
        int h = 1;
        int idxi = idx;
        while (table[idx] != null) {
            if (table[idx].getKey().equals(key)) {
                if (!table[idx].isRemoved()) {
                    table[idx].setRemoved(true);
                    size--;
                    return table[idx].getValue();
                } else if (table[idx].isRemoved()) {
                    throw new java.util.NoSuchElementException("That key is not"
                    + "recorded in the map");
                }
            } else {
                idx = (idxi + h * h++) % table.length;
            }
        }
        throw new java.util.NoSuchElementException("That key is not"
        + "recorded in the map");
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is not valid");
        }
        int idx = Math.abs(hash(key)) % table.length;
        int h = 1;
        int idxi = idx;
        while (table[idx] != null) {
            if (table[idx].getKey().equals(key)) {
                if (!table[idx].isRemoved()) {
                    return table[idx].getValue();
                } else {
                    throw new java.util.NoSuchElementException("That key is not"
                    + "recorded in the map");
                }

            } else {
                idx = (idxi + h * h++) % table.length;
            }
        }
        throw new java.util.NoSuchElementException("That key is not"
        + "recorded in the map");
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is not valid");
        }
        try {
            this.get(key);
            return true;
        } catch (java.util.NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public void clear() {
        table = (MapEntry<K, V>[]) new MapEntry[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Set<K> keySet() {
        Set<K> s = new HashSet(this.size);
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                s.add(table[i].getKey());
            }
        }
        return s;
    }

    @Override
    public List<V> values() {
        List<V> s = new ArrayList<V>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                s.add(table[i].getValue());
            }
        }
        return s;
    }

    @Override
    public void resizeBackingTable(int length) {
        MapEntry<K, V>[] temp = table;
        table = (MapEntry<K, V>[]) new MapEntry[length];

        for (int i = 0; i < temp.length; i++) {
            if (temp[i] != null && !temp[i].isRemoved()) {
                this.put(temp[i].getKey(), temp[i].getValue());
                size--;
            }
        }
    }
    /**
    * @param key the key to be hashed
    * @return hash
    */
    private int hash(K key) {
        return Math.abs(key.hashCode());
    }

    @Override
    public MapEntry<K, V>[] getTable() {
        // DO NOT EDIT THIS METHOD!
        return table;
    }

}
