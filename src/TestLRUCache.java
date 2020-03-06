import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Deque;

public class TestLRUCache {
    private final LRUCache<String, String> testCache = new LRUCache<>(4);
    private final Node<String, String> aNode1 = new Node<>("1st cache", "This is the first cache.", new Date(120, 5,1));
    private final Node<String, String> aNode2 = new Node<>("2nd cache", "This is the second cache.", new Date(127, 6, 1));
    private final Node<String, String> aNode3 = new Node<>("3rd cache", "This is the third cache.", new Date(119, 11, 31));
    private final Node<String, String> aNode4 = new Node<>("4th cache", "This is the fourth cache.", new Date(122, 3, 30));

    private String getLatestCache(LRUCache pCache){
        try{
            Field field = LRUCache.class.getDeclaredField("keys");
            field.setAccessible(true);
            Deque temp = (Deque)field.get(testCache);
            return (String) temp.getLast();
        }catch (ReflectiveOperationException e){
            e.printStackTrace();
            return null;
        }
    }

    @BeforeEach
    public void setup(){
        testCache.clear();
        testCache.write(aNode1);
        testCache.write(aNode2);
        testCache.write(aNode3);
        testCache.write(aNode4);
    }

    @Test
    public void testGet_Contain_NotExpire(){
        assertEquals(aNode2.getValue(), testCache.get("2nd cache"));
        assertEquals(4, testCache.size());
        assertEquals(aNode2.getKey(), getLatestCache(testCache));
    }

    @Test
    public void testGet_Contain_Expire(){
        assertEquals(null, testCache.get("3rd cache"));
        assertEquals(3, testCache.size());
        assertEquals(aNode4.getKey(), getLatestCache(testCache));
    }

    @Test
    public void testeGet_NotContain(){
        assertEquals(null, testCache.get("not existed cache"));
        assertEquals(4, testCache.size());
        assertEquals(aNode4.getKey(), getLatestCache(testCache));
    }


    @Test
    public void testWrite_NotContain_NotExceedCap(){
        assertEquals(4, testCache.size());
        assertEquals(aNode4.getKey(), getLatestCache(testCache));
    }

    @Test
    public void testWrite_NotContain_ExceedCap(){
        Node<String, String> node5 = new Node<>("5th cache", "this is the fifth cache.", new Date(122, 10, 7));
        testCache.write(node5);
        assertEquals(4, testCache.size());
        assertEquals(node5.getKey(), getLatestCache(testCache));
    }

    @Test
    public void testWrite_Contain_NodeExceedCap(){
        Node<String, String> node2 = new Node<>("2th cache", "this is the new second cache.", new Date(122, 10, 7));
        testCache.write(node2);
        assertEquals(4, testCache.size());
        assertEquals(node2.getKey(), getLatestCache(testCache));
    }

    @Test
    public void testRemove_Contain(){
        assertEquals(aNode2.getValue(), testCache.remove("2nd cache"));
        assertEquals(3, testCache.size());
        assertEquals(aNode4.getKey(), getLatestCache(testCache));
    }

    @Test
    public void testRemove_NotContain(){
        assertEquals(null, testCache.get("not existed cache"));
        assertEquals(4, testCache.size());
        assertEquals(aNode4.getKey(), getLatestCache(testCache));
    }

}
