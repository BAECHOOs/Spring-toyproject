package toyproject.springteam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextRegionProviderAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication(exclude = ContextRegionProviderAutoConfiguration.class)
public class SpringTeamApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTeamApplication.class, args);
	}
}
