package just_food;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class TestJustFood extends TestBase {


    @BeforeAll
    static void setup() {


        Configuration.startMaximized = true;
    }

    @org.junit.jupiter.api.Test
    void dataOfPageProcess(){
        Long age = getRandomLong(0,99);

        open("https://www.justfood.pro/");
        $(By.xpath("//div[text()='Питание с научным подходом'] ")).shouldBe(visible);

        List<String> ls3 = new ArrayList<>();
        ls3.add($(By.xpath("//h2[.='Почему мы?'] ")).getText());
        ls3.add($(By.xpath("//h2[.='Наша команда']")).getText());
        ls3.add($(By.xpath("//h2[.=' СМИ о нас ']")).getText());
        ls3.add($(By.xpath("//*[@class='container']/app-faq/h2")).getText());
        ls3.add($(By.xpath("//h2[.='Описание программ']")).getText());


        assertThat(ls3, hasSize(5));
        System.out.println(ls3.size());
        System.out.println("-------------------------------------------");

        for (int i = 0; i < ls3.size(); i++) {
            System.out.println(ls3.get(i));
        }

        ElementsCollection resultSearch1 = $$(By.xpath("//*[@id='why']/div/app-why-us/div/div/p"));

        System.out.println(resultSearch1.size());
        $$(By.xpath("//*[@id='why']/div/app-why-us/div/div/p")).shouldHaveSize(6);
        List<String> ls1 = new ArrayList<>();
        for (int i = 0; i < resultSearch1.size(); i++) {
            ls1.add(resultSearch1.get(i).getText());
        }

        System.out.println("----------%%%%%%%%%%%%%%%%%---------------------------------");
        for (int i = 0; i < ls1.size(); i++) {
            System.out.println(ls1.get(i));
        }
        Collection<String> resultSearch8 = Arrays.asList("Безопаснее похода в магазин", "Разнообразное меню",
                "Много овощей и фруктов", "Минимум соли", "Без жарки на масле", "Бесконтактная доставка");
        resultSearch8.retainAll(ls1);


        $(By.xpath("//*[@class='btn btn--orange btn--full-width order-btn']")).click();
        $(By.xpath("//*[@class='step__title']")).shouldBe(visible);
        $(By.xpath("//*[@class='step__title']")).shouldHave(text(" Укажите телефон, чтобы оформить заказ"));

        $(By.xpath("//*[@class='btn btn--black btn--full-width send-code-btn']")).click();
        $(By.xpath("//*[@class='error ng-star-inserted']")).shouldHave(text("Введите номер телефона"));
        $(By.xpath("//*[@class='modal-window__close-btn']")).click();

        $(".open-calc-btn").shouldBe(visible);
        $(".open-calc-btn").scrollIntoView(false);
        $(".open-calc-btn").click();


        $(By.xpath(" //*[@class='wrapper wrapper--gray']")).shouldHave(text("Рассчитать норму калорий"));

        $(By.xpath("//*[@class='radio-group gender']/label[2]")).click();


        ElementsCollection resultSearch = $$(By.xpath("//input[@type='text']"));

        resultSearch.get(0).setValue(String.valueOf(age));
        resultSearch.get(1).setValue("179");
        resultSearch.get(2).setValue("67");


        $(By.xpath("//*[@class='activity-row']/div/label[4]")).click();

        $(By.xpath("//*[@class='activity-row']/p")).
                shouldHave(text("Подготовка к соревнованиям, тяжелые тренировки 5 и более раз в неделю"));

        $(By.xpath("//*[@class='target-row']/div/label[1]")).click();

        $(By.xpath("//*[@class='result__text']/div[2]")).getText();
        $(By.xpath("//*[@class='result__text']/div[2]")).shouldHave(text("1 500 ккал/день"));
        System.out.println($(By.xpath("//*[@class='result__text']/div[2]")).getText());
    }
}
