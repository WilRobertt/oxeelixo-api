package br.com.ifpe.oxeelixo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class OxeelixoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OxeelixoApiApplication.class, args);
	}

	 @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
	  return new BCryptPasswordEncoder();
    }


}
