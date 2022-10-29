import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.worldline.assignment.perfectnumbers.services.NumberService;
import org.worldline.assignment.perfectnumbers.services.impl.NumberServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class PerfectNumberTests {

    private static NumberService numberService;


    @BeforeAll
    public static void initNumberService() {
        numberService = new NumberServiceImpl();
    }

    @BeforeEach
    public void beforeEachTest() {
        System.out.println("New test case is starting now");
    }

    @AfterEach
    public void afterEachTest() {
        System.out.println("Test case is ended");
    }

    @Test
    public void isTheGivenNumberSixAPerfectNumber() {
        Boolean isPerfect;
        isPerfect = numberService.isPerfectNumber(6);
        assertEquals(isPerfect, true);
    }
    @Test
    public void givenNumberSevenIsNotAPerfectNumber() {
        Boolean isPerfect;
        isPerfect = numberService.isPerfectNumber(7);
        assertEquals(isPerfect, false);
    }

    @Test
    public void getPerfectNumberSeriesBetweenOneAndThousand() {
        List<Integer> perfectNumberSeries;
        perfectNumberSeries = numberService.getPerfectNumberSeries(1, 1000);
        List<Integer> numberSeries = Arrays.asList(6, 28, 496);
        assertIterableEquals(perfectNumberSeries, numberSeries);
    }



}
