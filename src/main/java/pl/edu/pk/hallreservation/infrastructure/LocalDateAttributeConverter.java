package pl.edu.pk.hallreservation.infrastructure;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
        return (localDate == null ? null : Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }

    @Override
    public LocalDate convertToEntityAttribute(Date sqlDate) {
        return (sqlDate == null ? null : sqlDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }
}

