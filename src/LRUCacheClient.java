import java.sql.SQLOutput;
import java.util.Calendar;
import java.util.Date;

public class LRUCacheClient {
    public static void main(String[] args) {
       LRUCache<String, String> testCache = new LRUCache<>(4);

        testCache.write("1st cache", "This is the first cache.", new Date(120, 5,1));
        testCache.write("2nd cache", "This is the second cache.", new Date(127, 6, 1));
        testCache.write("3rd cache", "This is the third cache.", new Date(119, 11, 31));
        testCache.write("4th cache", "This is the fourth cache.", new Date(122, 3, 30));

        System.out.println("==1st, 2nd, 3rd, 4th==");
        System.out.println(testCache);

        System.out.println("==get 2nd cache==");
        System.out.println(testCache.get("2nd cache") + "\n");

        System.out.println("==1st, 3rd, 4th, 2nd==");
        System.out.println(testCache);

        testCache.write("5th cache", "this is the fifth cache.", new Date(122, 10, 7));
        System.out.println("==1st removed due to capacity constraint, 3rd, 4th, 2nd, 5th==");
        System.out.println(testCache);

        System.out.println("==3rd cache expired==");
        testCache.get("3rd cache");
        System.out.println();

        System.out.println("==3rd cache removed due to expire==");
        System.out.println(testCache);

        testCache.remove("2nd cache");
        System.out.println("==2nd cache removed==");
        System.out.println(testCache);

    }
}
