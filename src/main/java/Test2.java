import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import reactor.netty.http.server.HttpServer;

import javax.annotation.PostConstruct;

@EnableWebFlux// not working .web(WebApplicationType.NONE) and spring.main.allow-circular-references=true need
@Configuration
@RestController
public class Test2 implements WebFluxConfigurer{

    @Value("${server.port:8082}")
    int port = 8082;
    @Autowired ApplicationContext context;

    @Bean
    public HttpServer nettyHttpServer(ApplicationContext context) {
        HttpHandler handler = WebHttpHandlerBuilder.applicationContext(context).build();
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(handler);
        HttpServer httpServer = HttpServer.create().host("localhost").port(this.port);
        return httpServer.handle(adapter);
    }

    @PostConstruct
    public void init(){
        context.getBean(HttpServer.class).bindNow().onDispose().block(); // webflux server start
    }

    @RequestMapping("/")
    public String hello() {
        return "hello world";
    }
}
