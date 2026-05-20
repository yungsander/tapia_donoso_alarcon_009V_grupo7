package alertas.Config;

import feign.Logger;
import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class FeignConfig {

    @Bean
    public Request.Options requestOptions(){
        return new Request.Options(3, TimeUnit.SECONDS,5,TimeUnit.NANOSECONDS,true);
    }

    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
