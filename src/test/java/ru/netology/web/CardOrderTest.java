package ru.netology.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderTest {
    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }

    @Test
    public void shouldPositiveScenarioV1() {
        $("[data-test-id=name] input").setValue("Белянко Ольга");
        $("[data-test-id=phone] input").setValue("+79215555555");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void shouldPositiveScenarioV2() {
        $("[data-test-id=name] input").setValue("Соловьев-Седой Иван");
        $("[data-test-id=phone] input").setValue("+79215555555");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void shouldPositiveScenarioV3() {
        $("[data-test-id=name] input").setValue("Соловьев Иван-Марсель");
        $("[data-test-id=phone] input").setValue("+79215555555");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void shouldNegativeScenarioV1() {
        $("[data-test-id=name] input").setValue("Белянко Ольга");
        $("[data-test-id=phone] input").setValue("+380954210510");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    @Test
    public void shouldNegativeScenarioV2() {
        $("[data-test-id=name] input").setValue("Belianko Olga");
        $("[data-test-id=phone] input").setValue("+30000000000");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
    @Test
    public void shouldNegativeScenarioV3() {
        $("[data-test-id=phone] input").setValue("+30000000000");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText(" Поле обязательно для заполнения"));
    }

    @Test
    public void shouldNegativeScenarioV4() {
        $("[data-test-id=name] input").setValue("Белянко Ольга");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText(" Поле обязательно для заполнения"));
    }

    @Test
    public void shouldNegativeScenarioV5() {
        $("[data-test-id=name] input").setValue("Белянко Ольга");
        $("[data-test-id=phone] input").setValue("+30000000000");
        $("button").click();
        $("[data-test-id=agreement].input_invalid").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
}