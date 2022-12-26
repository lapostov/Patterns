import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import lombok.Value;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class PatternsTest {

    private static Faker faker;

    private void DataGenerator() {
    }

    public static String generateDate(int shift) {

        String date = LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return date;
    }

    public static String generateCity(String locale) {
        String city = faker.address().cityName();
        return city;
    }

    public static String generateName(String locale) {
        String name = faker.name().username();
        return name;
    }

    public static String generatePhone(String locale) {
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            return new UserInfo(generateCity(locale), generateName(locale), generatePhone(locale));
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }

    @Test
    public void test() {


  //      open("http://localhost:9999");
  //      $("input[placeholder='Город']").setValue("Владивосток");
  //      $("[data-test-id='date'] input").setValue(generateDate(7));
  //      $("input[name='name']").setValue("Александр Четверяков");
  //      $("input[name='phone']").setValue("+79271620850");
  //      $x("//span[@class='checkbox__box']").click();
  //      $x("//span[@class='button__text']").click();
  //      String text = $x("//div[@class='notification__content']").should(visible, Duration.ofSeconds(15)).getText();
  //      $(".notification__content")
//
  //              .shouldHave(Condition.text("Встреча успешно забронирована на " + generateDate(7)), Duration.ofSeconds(15))

  //              .shouldBe(Condition.visible);

        open("http://localhost:9999");
        $("input[placeholder='Город']").setValue("Владивосток");
        String date = $("[data-test-id='date'] input").setValue(generateDate(4)).getText();
        $("input[name='name']").setValue("Александр Четверяков");
        $("input[name='phone']").setValue("+79271620850");
        $x("//span[@class='checkbox__box']").click();
        $x("//span[@class='button__text']").click();

        $x("//div[@data-test-id='replan-notification']//button").click();
        String text = $x("//div[@data-test-id='success-notification']//div[@class='notification__content']").should(visible, Duration.ofSeconds(3)).getText().trim();
        $x("//div[@data-test-id='success-notification']//div[@class='notification__content']")

                .shouldHave(Condition.text("Встреча успешно забронирована на " + date), Duration.ofSeconds(3))

                .shouldBe(Condition.visible);


    }
}

