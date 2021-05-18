package just_food;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
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

public class TestJustFoodPageCases {

    @Step("Открываем страницу и проверяем,что на станице присутствует описание: " +
            "Питание с научным подходом")
    public void openPageJustFoodAndCheckTitle(String url, String title) {
        open(url);
        $(By.xpath("//div[text()='Питание с научным подходом'] ")).shouldBe(visible);
        $(By.xpath("//div[text()='Питание с научным подходом'] ")).shouldHave(text(title));
    }

    @Step("Проверяем наличие на странице разделов: Почему мы?,Наша команда, СМИ о нас, FAQ, Описание программ")
    public void checkMainBlocksOfJustFood() {
        List<String> mainBlocksOfJustFood = new ArrayList<>();
        mainBlocksOfJustFood.add($(By.xpath("//h2[.='Почему мы?'] ")).getText());
        mainBlocksOfJustFood.add($(By.xpath("//h2[.='Наша команда']")).getText());
        mainBlocksOfJustFood.add($(By.xpath("//h2[.=' СМИ о нас ']")).getText());
        mainBlocksOfJustFood.add($(By.xpath("//*[@class='container']/app-faq/h2")).getText());
        mainBlocksOfJustFood.add($(By.xpath("//h2[.='Описание программ']")).getText());

        assertThat(mainBlocksOfJustFood, hasSize(5));
    }

    @Step("Проверяем перечисление всех преимуществ в разделе Почему мы?")
    public void checkWhyBlockOfJustFood() {
        ElementsCollection whyBlockOfJustFood = $$(By.xpath("//*[@id='why']/div/app-why-us/div/div/p"));

        $$(By.xpath("//*[@id='why']/div/app-why-us/div/div/p")).shouldHaveSize(6);

        List<String> ls1 = new ArrayList<>();
        for (int i = 0; i < whyBlockOfJustFood.size(); i++) {
            ls1.add(whyBlockOfJustFood.get(i).getText());
        }

        Collection<String> whyBlockOfJustFoodExpected = Arrays.asList("Безопаснее похода в магазин", "Разнообразное меню",
                "Много овощей и фруктов", "Минимум соли", "Без жарки на масле", "Бесконтактная доставка");
        whyBlockOfJustFoodExpected.retainAll(ls1);
    }

    @Step("Поверяем форму заказа: отображение сообщения, если телефон не  указан")
    public void checkFormForOrder() {
        $(By.xpath("//*[@class='btn btn--orange btn--full-width order-btn']")).click();
        $(By.xpath("//*[@class='step__title']")).shouldBe(visible);
        $(By.xpath("//*[@class='step__title']")).shouldHave(text(" Укажите телефон, чтобы оформить заказ"));

        $(By.xpath("//*[@class='btn btn--black btn--full-width send-code-btn']")).click();
        $(By.xpath("//*[@class='error ng-star-inserted']")).shouldHave(text("Введите номер телефона"));
        $(By.xpath("//*[@class='modal-window__close-btn']")).click();
    }

    @Step("Открываем форму калькулятора для подсчета калорий")
    public void openCalculatorForm() {
        $(".open-calc-btn").shouldBe(visible);
        $(".open-calc-btn").scrollIntoView(false);
        $(".open-calc-btn").click();
    }

    @Step("Проверка подсчета калорий в калькуляторе")
    public void checkСalorieСalculation(String age) {
        $(By.xpath(" //*[@class='wrapper wrapper--gray']")).shouldHave(text("Рассчитать норму калорий"));

        $(By.xpath("//*[@class='radio-group gender']/label[2]")).click();

        ElementsCollection resultSearch = $$(By.xpath("//input[@type='text']"));
        resultSearch.get(0).setValue(String.valueOf(age));
        resultSearch.get(1).setValue("456");
        resultSearch.get(2).setValue("679");

        $(By.xpath("//*[@class='activity-row']/div/label[4]")).click();
        $(By.xpath("//*[@class='activity-row']/p")).
                shouldHave(text("Подготовка к соревнованиям, тяжелые тренировки 5 и более раз в неделю"));
        $(By.xpath("//*[@class='target-row']/div/label[1]")).click();

        $(By.xpath("//*[@class='result__text']/div[2]")).shouldHave(text("2 500 ккал/день"));
    }

    @Step("Негативный тест. Проверка подсчета калорий в калькуляторе")
    public void checkNegativeСalorieСalculation() {
        $(By.xpath(" //*[@class='wrapper wrapper--gray']")).shouldHave(text("Рассчитать норму калорий"));

        $(By.xpath("//*[@class='radio-group gender']/label[2]")).click();

        ElementsCollection resultSearch = $$(By.xpath("//input[@type='text']"));
        resultSearch.get(0).setValue("1");
        resultSearch.get(1).setValue("43");
        resultSearch.get(2).setValue("634");

        $(By.xpath("//*[@class='activity-row']/div/label[4]")).click();
        $(By.xpath("//*[@class='activity-row']/p")).
                shouldHave(text("Подготовка к соревнованиям, тяжелые тренировки 5 и более раз в неделю"));
        $(By.xpath("//*[@class='target-row']/div/label[1]")).click();

        $(By.xpath("//*[@class='result__text']/div[2]")).shouldHave(text("1 100 ккал/день"));
    }
}
