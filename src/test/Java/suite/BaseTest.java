package suite;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    @BeforeAll
    public static void setUp(){
        System.out.println("Set up before class");
    }

    @AfterAll
    public static void tearDown(){
        System.out.println("Tear down after class");
    }
}
