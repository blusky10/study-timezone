import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.TimeZone;

public class TimezoneUtilTest {

    private static final Logger log = LoggerFactory.getLogger(TimezoneUtilTest.class);

    private Date date;
    private LocalDate localDate;
    private LocalDateTime localDateTime;
    private OffsetDateTime offsetDateTime;

    @Before
    public void setup(){
        date = new Date();
        localDate = LocalDate.now();
        localDateTime = LocalDateTime.now();
        offsetDateTime = OffsetDateTime.now();

        log.info("date : " + date);
        log.info("localDate : " + localDate);
        log.info("localDateTime : " + localDateTime);
        log.info("offsetDateTime : " + offsetDateTime);
    }

    @Test
    public void getOffset(){
        log.info("offsetDateTime offset : " + offsetDateTime.getOffset());
    }

    @Test
    public void getUTC(){
        OffsetDateTime now = OffsetDateTime.now();
        System.out.println("현재 시간 : " + now);

        // Offset
        ZoneOffset offset = now.getOffset();
        System.out.println("offset : " + offset);

        // Offset 을 second 로 변경
        int totalSeconds = offset.getTotalSeconds();
        System.out.println("totalSeconds : " + totalSeconds);

        OffsetDateTime offsetDateTime = null;
        if (totalSeconds > 0){
            offsetDateTime = now.minusSeconds(totalSeconds);
        }else{
            offsetDateTime = now.plusSeconds(totalSeconds);
        }

        System.out.println("offsetDateTime : " + offsetDateTime);
        OffsetDateTime utc = OffsetDateTime.now(ZoneId.of("UTC"));
        System.out.println("utc : " + utc);
    }

    @Test
    public void convertDateTimeToDate(){
        OffsetDateTime now = OffsetDateTime.now();
        System.out.println("현재 시간 : " + now);

        OffsetDateTime utc = OffsetDateTime.now(ZoneId.of("UTC"));
        System.out.println("utc : " + utc);
    }

    @Test
    public void jodaTime(){
        DateTime dateTime = new DateTime(DateTimeZone.UTC);
        System.out.println("dateTime : " + dateTime);
        System.out.println("dateTimeToDate : " + dateTime.toDate());
    }

    @Test
    public void jodaTimeWithFormat(){
        DateTime dateTime = new DateTime(DateTimeZone.UTC);
        System.out.println("dateTime : " + dateTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(ZoneId.of("UTC")));
        System.out.println("dateTimeToDateFormatter : " + simpleDateFormat.format(dateTime.toDate()));
    }
}
