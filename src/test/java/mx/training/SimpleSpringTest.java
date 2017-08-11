package mx.training;

import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;


//@ContextConfiguration("classpath:META-INF/spring/simpleSpring-context.xml")
public class SimpleSpringTest extends CamelSpringTestSupport{

    @Override
    protected AbstractApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext("classpath:META-INF/spring/simpleSpring-context.xml");
    }


    @Test
    public void checkForCamelContext(){
        assertNotNull(this.applicationContext.getId());
    }

    
}