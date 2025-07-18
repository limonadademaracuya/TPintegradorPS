package ar.edu.centro8.ps.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class JwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("1234"));

		//para tener a la mano, link para testearlo:
		// http://localhost:8080/swagger-ui/index.html
	}

}
