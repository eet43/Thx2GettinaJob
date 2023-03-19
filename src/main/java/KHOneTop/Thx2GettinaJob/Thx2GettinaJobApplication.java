package KHOneTop.Thx2GettinaJob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Thx2GettinaJobApplication {

	public static void main(String[] args) {
		SpringApplication.run(Thx2GettinaJobApplication.class, args);
	}

}
