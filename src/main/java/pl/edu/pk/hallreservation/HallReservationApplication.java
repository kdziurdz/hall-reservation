package pl.edu.pk.hallreservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
public class HallReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(HallReservationApplication.class, args);
		System.out.println("SIEMA");
	}
}
