import java.util.*;

public class LRUCache<K, V> {
    private Map<K, Node> cacheMap = new HashMap<>();
    private Deque<K> keys = new LinkedList<>();
    private int capacity;

    public LRUCache(int pCapacity){
        capacity = pCapacity;
    }


    public V get (K key){
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


    public V remove (K key){
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




    private class Node<K, V>{
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
    }


}


