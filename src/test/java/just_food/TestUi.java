package just_food;

import io.qameta.allure.Description;
import just_food.ui.LayoutSteps;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestUi extends TestBase {

    TestJustFoodPageCases testJustFoodPageCases = new TestJustFoodPageCases();


    @Test
    @Description("Питание с научным подходом")
    public void negativeCheckJustFoodPageLayoutInChrome() throws IOException {
        testJustFoodPageCases.openPageJustFoodAndCheckTitle("https://www.justfood.pro/",
                "Питание с научным подходом");
        LayoutSteps.compareCurrentPageWithBase("galen-specs/tabs.spec", "chrome");

    }

    @Test
    @Description("Питание с научным подходом")
    public void positiveCheckJustFoodPageLayoutInChrome() throws IOException {
        testJustFoodPageCases.openPageJustFoodAndCheckTitle("https://www.justfood.pro/",
                "Питание с научным подходом");
        LayoutSteps.compareCurrentPageWithBase("galen-specs/tabs1.spec", "chrome");

    }
}
