package pl.edu.pk.hallreservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.edu.pk.hallreservation.service.reservation.dto.ReservationDTO;

@Service
public class EmailService {

    private static final String SUCCESSFULL_RESERVATION_TEXT = "Pomyślnie zarezerwowano salę %s. \nData: %s \nGodziny lekcyjne: %s \n";
    private static final String SUCCESSFULL_RESERVATION_SUBJECT = "Potwierdzenie rezerwaci";

    private static final String SUCCESSFULL_CANCELLATION_TEXT = "Pomyślnie odwołano rezerwację na salę %s. \nData: %s \nGodziny lekcyjne: %s \n";
    private static final String SUCCESSFULL_CANCELLATION_SUBJECT = "Potwierdzenie odwołania rezerwacji";

    private static final String SYSTEM_CANCELLATION_TEXT = "Rezerwacja na salę %s \nw dniu %s \nna godziny lekcyjne: %s \nzostała odwołana przez system rezerwacji z powodu zmianach w planie lekcyjnym.\nZa utrudnienia przepraszamy";
    private static final String SYSTEM_CANCELLATION_SUBJECT = "Rezerwacja odwołana przez system";

    private static final String INTENTIONED_CANCELLATION_TEXT = "Rezerwacja na salę %s \nw dniu %s \nna godziny lekcyjne: %s \nzostała odwołana przez %s \nz powodu: %s";
    private static final String INTENTIONED_CANCELLATION_SUBJECT = "Rezerwacja odwołana";

    private JavaMailSender emailSender;

    @Autowired
    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendMailAboutSuccessfullReservation(ReservationDTO reservationDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(reservationDTO.getUser().getEmail());
        message.setSubject(SUCCESSFULL_RESERVATION_SUBJECT);
        message.setText(String.format(SUCCESSFULL_RESERVATION_TEXT, reservationDTO.getHall().getName(),
                reservationDTO.getDate(), reservationDTO.getLessonNumbers()));
        emailSender.send(message);
    }

    public void sendEmailAboutSelfCancellation(ReservationDTO reservationDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(reservationDTO.getUser().getEmail());
        message.setSubject(SUCCESSFULL_CANCELLATION_SUBJECT);
        message.setText(String.format(SUCCESSFULL_CANCELLATION_TEXT, reservationDTO.getHall().getName(),
                reservationDTO.getDate(), reservationDTO.getLessonNumbers()));
        emailSender.send(message);
    }

    public void sendEmailAboutAutomatedReservationCancellation(ReservationDTO reservationDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(reservationDTO.getUser().getEmail());
        message.setSubject(SYSTEM_CANCELLATION_SUBJECT);
        message.setText(String.format(SYSTEM_CANCELLATION_TEXT, reservationDTO.getHall().getName(),
                reservationDTO.getDate(), reservationDTO.getLessonNumbers()));
        emailSender.send(message);
    }

    public void sendEmailAboutIntentionedReservationCancellation(ReservationDTO reservationDTO) {
        String cancellerName = reservationDTO.getCanceller().getFirstName() + " " + reservationDTO.getCanceller().getLastName();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(reservationDTO.getUser().getEmail());
        message.setSubject(INTENTIONED_CANCELLATION_SUBJECT);
        message.setText(String.format(INTENTIONED_CANCELLATION_TEXT, reservationDTO.getHall().getName(),
                reservationDTO.getDate(), reservationDTO.getLessonNumbers(), cancellerName,
                reservationDTO.getCancellationReason()));
        emailSender.send(message);
    }
}
