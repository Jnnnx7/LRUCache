import java.util.*;


/*
 I provided an expiration check in get function.
 It won't keep checking continuously and
 delete the value instantly after it expires.
 If we wanted to check expiration continuously,
 we would have to create a new thread.
 This would make the codes way too complicated (break simplicity),
 and the codes would become brittle.
 */

public class LRUCache<K, V> {
    private Map<K, Node> cacheMap = new HashMap<>();
    private Deque<K> keys = new LinkedList<>();
    private int capacity;

    public LRUCache(int pCapacity){
        capacity = pCapacity;
    }


    public V get(K key){
        if(keys.contains(key) && cacheMap.containsKey(key)){
            Node<K, V> temp = cacheMap.get(key);
            keys.remove(key);
            if(temp.expire()){
                System.out.println("Cache expires.");
                return null;
            }else {
                keys.addLast(key);
                return temp.getValue();
            }
        }
        return null;
    }

    public void write(K key, V value, Date date){
        if(keys.contains(key) && cacheMap.containsKey(key)){
            Node<K, V> temp = cacheMap.get(key);
            temp.setValue(value);
            temp.setExpireTime(date);

            if(!key.equals(keys.getLast())){
                keys.remove(key);
                keys.addLast(key);
            }
        }else{
            Node<K, V> newNode = new Node<>(key, value, date);
            cacheMap.put(key, newNode);
            keys.addLast(key);

            if(capacity < this.size()){
                K firstKey = keys.getFirst();
                cacheMap.remove(firstKey);
                keys.removeFirst();
            }
        }
    }


    public V remove(K key){
        if(keys.contains(key) && cacheMap.containsKey(key)){
            Node<K, V> temp = cacheMap.remove(key);
            cacheMap.remove(key);
            keys.remove(key);
            return temp.getValue();
        }
        return null;
    }

    public int size(){
        return cacheMap.size();
    }

    public String toString(){
        StringBuilder str = new StringBuilder();

        for (K key: keys){
            String temp = cacheMap.get(key).toString();
            str.append(temp);
            str.append("\n");
        }

        return str.toString();
    }


}

class Node<K, V>{
    private K key;
    private V value;
    private Date expireTime;

    public Node (K pKey, V pValue, Date pExpire){
        this.key = pKey;
        this.value = pValue;
        this.expireTime = pExpire;
    }

    public void setValue(V pValue){
        this.value = pValue;
    }

    public void setExpireTime(Date pDate){
        this.expireTime = pDate;
    }

    public V getValue(){
        return this.value;
    }


    public boolean expire(){
        if(this.expireTime.before(new Date())){
            return true;
        }else {
            return false;
        }
    }

    public String toString(){
        return key + ": " + value;
    }
}


