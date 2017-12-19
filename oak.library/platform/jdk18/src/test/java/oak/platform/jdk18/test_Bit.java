package oak.platform.jdk18;

import oak.platform.api.IBit;
import oak.platform.api.factory_IPlatform;
import org.junit.Test;

public class test_Bit {
    
    @Test
    public void test() {
        
        //Shuld be injected
        factory_IPlatform platform = new factory_Platform();
        
        IBit sut = platform.Bit_SET();
        
        System.out.println("End of Java Test");
    }
    
}