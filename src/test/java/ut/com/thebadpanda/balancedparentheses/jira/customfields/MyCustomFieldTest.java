package ut.com.thebadpanda.balancedparentheses.jira.customfields;

import com.thebadpanda.balancedparentheses.api.CheckForBalance;
import com.thebadpanda.balancedparentheses.impl.CheckForBalanceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;


public class MyCustomFieldTest {

    private CheckForBalance checkForBalance;

    private final String notBalancedBrackets = ")(hallo((freunde))";
    private final String balancedBrackets = "(wie)((gehts dir))";

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        checkForBalance = new CheckForBalanceImpl();
    }

    @Test
    public void whenBracketsNotBalanced_thenExceptionShouldBeThrown() {
        assertFalse(checkForBalance.isBracketsBalanced(notBalancedBrackets));
    }

    @Test
    public void whenBracketsAreBalanced_thenReturnStringValue() {
        assertTrue(checkForBalance.isBracketsBalanced(balancedBrackets));
    }

    @After
    public void tearDown() {
        checkForBalance = null;
        assertNull(checkForBalance);
    }
}
