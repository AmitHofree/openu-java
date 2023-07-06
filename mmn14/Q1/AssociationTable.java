import java.util.Iterator;
import java.util.TreeMap;

public class AssociationTable<K extends Comparable<K>, V> {
    private final TreeMap<K, V> map;

    public AssociationTable() {
        map = new TreeMap<>();
    }

    public AssociationTable(K[] keys, V[] values) throws IllegalArgumentException {
        map = new TreeMap<>();
        if (keys.length != values.length) throw new IllegalArgumentException("Keys length isn't equal to values length");
        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i], values[i]);
        }
    }

    public void add(K key, V value) {
        map.put(key, value);
    }

    public V get(K key) {
        return map.get(key);
    }

    public boolean contains(K key) {
        return map.containsKey(key);
    }

    public boolean remove(K key) {
        return map.remove(key) != null;
    }

    public int size() {
        return map.size();
    }

    public Iterator<K> keyIterator() {
        return map.keySet().iterator();
    }
}
