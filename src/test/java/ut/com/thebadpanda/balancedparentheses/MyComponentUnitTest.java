package ut.com.thebadpanda.balancedparentheses;

import org.junit.Test;
import com.thebadpanda.balancedparentheses.api.MyPluginComponent;
import com.thebadpanda.balancedparentheses.impl.MyPluginComponentImpl;

import static org.junit.Assert.assertEquals;

public class MyComponentUnitTest
{
    @Test
    public void testMyName()
    {
        MyPluginComponent component = new MyPluginComponentImpl(null);
        assertEquals("names do not match!", "myComponent",component.getName());
    }
}