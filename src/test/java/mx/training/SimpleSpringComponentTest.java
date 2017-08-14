package mx.training;

import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleSpringComponentTest extends CamelSpringTestSupport {
    
    @Override
    protected AbstractApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext("classpath:META-INF/spring/simplespringcomponent-context.xml");
    }

    @Test
    public void testBasicComponent(){
        assertEquals(true,true);
    }

}