import org.springframework.boot.WebApplicationType
import org.springframework.boot.builder.SpringApplicationBuilder

import javax.annotation.PostConstruct
//using boot for webpack build-image heroku and native maybe
@main def i = new SpringApplicationBuilder(classOf[Test2]).web(WebApplicationType.NONE).run()
