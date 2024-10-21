package itst.social_raccoon_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("itst.social_raccoon_api.Repositories")
@EntityScan("itst.social_raccoon_api.models")
public class SocialRaccoonApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialRaccoonApiApplication.class, args);
	}

}
