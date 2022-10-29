import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.worldline.assignment.perfectnumbers.controller.NumberController;
import org.worldline.assignment.perfectnumbers.services.impl.NumberServiceImpl;

public class PerfectNumberIntegrationTests {

    @InjectMocks
    NumberController numberController;

    @Mock
    NumberServiceImpl numberService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
}
