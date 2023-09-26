package hasix.junear.news.application;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class NewsDateFormatter {

    public String format(LocalDateTime dateTime) {
        ZoneId seoulZone = ZoneId.of("Asia/Seoul");
        ZonedDateTime seoulTime = ZonedDateTime.now(seoulZone);
        LocalDateTime now = seoulTime.toLocalDateTime();
        Duration duration = Duration.between(dateTime, now);

        if (duration.toMinutes() < 60) {
            long minutes = duration.toMinutes();
            return minutes + "분 전";
        } else if (duration.toHours() < 24) {
            long hours = duration.toHours();
            return hours + "시간 전";
        } else {
            long days = duration.toDays();
            return days + "일 전";
        }
    }
}
