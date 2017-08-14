package mx.training.simple;


import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


/**
 * Route que escribe un mensaje en el log cada segundo.
 */
@Component
public class LogRouteBuilder extends RouteBuilder{
    @Override
    public void configure() throws Exception {
        from("timer:logMessageTimer?period=1s")
            .to("mylogger:insideTheRoute?showHeaders=true")
            .log("Event triggered by ${property.CamelTimerName} at ${header.CamelTimerFiredTime}");
    }

}