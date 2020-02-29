import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestVersion {
    @Test
    public void testVersion_sameLength_firstGreater(){
        assertEquals(Version.Result.GREATER, Version.versionCompare("1.3", "1.1"));
    }

    @Test
    public void testVersion_sameLength_secondGreater(){
        assertEquals(Version.Result.LESS, Version.versionCompare("1.3", "1.7"));
    }

    @Test
    public void testVersion_diffLength_firstGreater(){
        assertEquals(Version.Result.GREATER, Version.versionCompare("1.3.1", "1.3"));
    }

    @Test
    public void testVersion_diffLength_secondGreater(){
        assertEquals(Version.Result.LESS, Version.versionCompare("1.3.1", "1.7"));
    }

    @Test
    public void testVersion_sameVersion(){
        assertEquals(Version.Result.EQUAL, Version.versionCompare("1.7.1.3", "1.7.1.3"));
    }

}
