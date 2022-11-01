package ru.netology.rest;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
public class ClassTestSelenide {

    String planningDate = generateDate(4);
    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldRegisterByAccountNumberVisibilityChange() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Новосибирск");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(" ");
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Дмитрий Петров");
        $("[data-test-id='phone'] input").setValue("+79200000000");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }
}