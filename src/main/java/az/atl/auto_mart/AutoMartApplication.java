package az.atl.auto_mart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AutoMartApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoMartApplication.class, args);
	}

}
