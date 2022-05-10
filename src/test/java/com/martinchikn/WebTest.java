package com.martinchikn;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;



public class WebTest {

    @ValueSource(strings = {
            "Chelsea",
            "Real"
    })
    @ParameterizedTest(name = "Searching for {0}")
    void wordSearchTest(String testData) {
//        Предусловия:
        Selenide.open("https://onefootball.com/en/home");
//        Шаги:
        $("[enterkeyhint=\"search\"]").click();
        $("[enterkeyhint=\"search\"]").setValue(testData);
//        Ожидаемый результат:
        $$(".search-result-list__item")
                .find(Condition.text(testData))
                .shouldBe(visible);
    }

    @CsvSource(value = {
            "Chelsea, Chelsea Women",
            "Real, Real Madrid"
    })

    @ParameterizedTest(name = "Searching for {0}, expected team in results: {1}")
    void teamsSearchTest(String testData, String expectedResult) {
//        Предусловия:
        Selenide.open("https://onefootball.com/en/home");
//        Шаги:
        $("[enterkeyhint=\"search\"]").click();
        $("[enterkeyhint=\"search\"]").setValue(testData);
//        Ожидаемый результат:
        $$(".search-result-list__item")
                .find(Condition.text(expectedResult))
                .shouldBe(visible);

    }

    static Stream<Arguments> methodSourceExampleTest() {
        return Stream.of(
                Arguments.of(1860, "1860 München"),
                Arguments.of(1860, "1860 Rosenheim")
        );
    }
    @MethodSource("methodSourceExampleTest")
    @ParameterizedTest
    void methodSourceExampleTest(Integer first, String second) {
//        Предусловия:
        Selenide.open("https://onefootball.com/en/home");
//        Шаги:
        $("[enterkeyhint=\"search\"]").click();
        $("[enterkeyhint=\"search\"]").setValue(String.valueOf(first));
//        Ожидаемый результат:
        $$(".search-result-list__item")
                .find(Condition.text(second))
                .shouldBe(visible);

    }
}