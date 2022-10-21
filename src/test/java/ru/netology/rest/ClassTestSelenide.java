package ru.netology.rest;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
public class ClassTestSelenide {
    @Test
    void shouldRegisterByAccountNumberVisibilityChange() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Новосибирск");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(" ");
        $("[data-test-id='date'] input").setValue("25.10.2022");
        $("[data-test-id='name'] input").setValue("Дмитрий Петров");
        $("[data-test-id='phone'] input").setValue("+79200000000");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
    }
}
