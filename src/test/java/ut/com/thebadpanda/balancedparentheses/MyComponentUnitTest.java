package ut.com.thebadpanda.balancedparentheses;

import org.junit.Before;
import org.junit.Test;
import com.thebadpanda.balancedparentheses.api.MyPluginComponent;
import com.thebadpanda.balancedparentheses.impl.MyPluginComponentImpl;

import static org.junit.Assert.assertEquals;

public class MyComponentUnitTest {

    private MyPluginComponent component;

    @Before
    public void setup() {
        component = new MyPluginComponentImpl(null);
    }

    @Test
    public void testMyName() {
        assertEquals("names do not match!", "myComponent", component.getName());
    }
}