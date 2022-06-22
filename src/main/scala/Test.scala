import org.springframework.boot.WebApplicationType
import org.springframework.boot.builder.SpringApplicationBuilder
//using boot for webpack build-image heroku and native maybe
//TODO add need ant run scala3's @main
@main def i = new SpringApplicationBuilder(classOf[Test2]).web(WebApplicationType.NONE).run()
