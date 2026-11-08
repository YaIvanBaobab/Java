package Homework6;

public class Pair<K,V> {
  private K key;
  private V value;

  public Pair(K key, V value) {
    this.key = key;
    this.value = value;
  }

  public K getKey() {
    return this.key;
  }

  public void setKey(K key) {
    this.key = key;
  }

  public V getValue() {
    return this.value;
  }

  public void setValue(V value) {
    this.value = value;
  }

  public Pair<V, K> swap() {
    return new Pair<V, K>(value, key);
  }

  @Override
  public String toString() {
    return String.format("Pair{key=%s, value=%s}", key, value);
  }

  public static void main(String[] args) {
    final Pair<String, Integer> pair = new Pair<>("Age", 25);
    System.out.println(pair);
    final Pair<Integer, String> swapper = pair.swap();
    System.out.println(swapper);
    final Pair<Object, Object> nullPair = new Pair<>(null, null);
    System.out.println(nullPair);
    final Pair<Object, Object> nullSwapper = nullPair.swap();
    System.out.println(nullSwapper);
  }
}
