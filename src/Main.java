import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String date;
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите дату и время: ");
        date = sc.nextLine();
        try {
            System.out.println("Уровень 1");
            System.out.println(isWeekend(date));
        } catch (DateTimeParseException dateTimeParseException){
            System.out.println("Неправильный формат даты! Верный формат: ДД.ММ.ГГГГ");
        }
        try {
            System.out.println("Уровень 2");
            System.out.println(isWorkingTime(date));
        } catch (DateTimeParseException dateTimeParseException){
            System.out.println("Неправильный формат даты и времени! Верный формат: ДД.ММ.ГГГГ чч:мм");
        }
    }

    public static boolean isWeekend(String dateString) {
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        int day = date.getDayOfMonth();
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        if (date.getMonthValue() == 5 && date.getYear() == 2024) {
            if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                return true;
            } else if (day == 1 || day == 9 || day == 10) {
                return true;
            } else {
                return false;
            }
        } else {
            System.out.println("Дата в календаре не найдена!");
            return false;
        }
    }

    public static boolean isWorkingTime(String dateTimeString) {
        ZoneId moscowZone = ZoneId.of("Europe/Moscow");
        LocalDateTime dateTime;
        dateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        int day = dateTime.getDayOfMonth();

        ZonedDateTime zonedDateTime = dateTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(moscowZone);
        DayOfWeek dayOfWeek = zonedDateTime.getDayOfWeek();
        LocalTime timeOfDay = zonedDateTime.toLocalTime();

        if (dateTime.getMonthValue() == 5 && dateTime.getYear() == 2024) {
            if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                return true;
            } else if (day == 1 || day == 9 || day == 10) {
                return true;
            } else if(timeOfDay.isBefore(LocalTime.of(9, 0)) || timeOfDay.isAfter(LocalTime.of(18, 0))) {
                return true;
            } else {
                return false;
            }
        } else {
            System.out.println("Дата в календаре не найдена!");
            return false;
        }
    }
}

