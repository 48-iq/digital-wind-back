package dev.digital_wind_gays.neo_story;

import dev.digital_wind_gays.neo_story.user.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NeoStoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeoStoryApplication.class, args);
		SecurityConfig securityConfig = new SecurityConfig();
		securityConfig.getUtil();
	}

}
