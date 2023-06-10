package cache;

import lombok.Data;

@Data
public class Node {
    String key;
    String value;
    Node next;
    Node prev;
}
