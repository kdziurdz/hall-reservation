package pl.edu.pk.hallreservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.edu.pk.hallreservation.service.HallParserService;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
public class HallReservationApplication {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(HallReservationApplication.class, args);
        System.out.println("SIEMA POZDRO 600");
        context.getBean(HallParserService.class).refreshHallsClasses();
    }
}
