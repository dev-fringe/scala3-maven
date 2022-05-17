import org.springframework.context.annotation.{AnnotationConfigApplicationContext, Configuration}
import javax.annotation.PostConstruct

@main def i = new AnnotationConfigApplicationContext(classOf[Test])
@Configuration class Test {
  @PostConstruct def init() = print("test")
}
