package cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class CacheService {
    //LRU
    private Map<String, Node> cache;
    private int cacheSize;
    private Node root, tail;

    private final Semaphore lock = new Semaphore(1);

    public CacheService(int cacheSize) {
        this.cache = new HashMap<>();
        this.cacheSize = cacheSize;
        this.root = new Node();
        this.tail = new Node();
        root.next = tail;
        tail.prev = root;
    }


    public String get(String key) {
        try {
            if (cache.containsKey(key)) {
                Thread.sleep(20);
                updateHead(key);
                return cache.get(key).getValue();
            }
            return null;
        }catch (InterruptedException ie) {
            System.out.println(ie);
            return "EXE";
        }
    }

    private void updateHead(String key) {
        Node keyNode = cache.get(key);
        Node next = keyNode.getNext();
        Node prev = keyNode.getPrev();
        if(prev!=null) prev.next = next;
        if(next!=null) next.prev = prev;

        keyNode.next = root.next;
        keyNode.prev = root;
        root.next.prev = keyNode;
        root.next = keyNode;
    }

    public void updateOrInset(String key, String value) {
        if(cache.containsKey(key)) {
            cache.get(key).setValue(value);
        } else {
            if(cacheSize==cache.size()) {
                removeTail();
            }
            Node node = createNewNode(key,value);
            cache.put(key, node);
        }
        updateHead(key);
    }

    private void removeTail() {
        Node toRemove = tail.prev;
        cache.remove(toRemove.key);
        Node prev = tail.prev.prev;
        prev.next = tail;
        tail.prev = prev;
    }

    private Node createNewNode(String key, String value) {
        Node node = new Node();
        node.value = value;
        node.key = key;
        return node;
    }
}
