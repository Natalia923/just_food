package just_food;

import io.qameta.allure.*;
import just_food.ui.LayoutSteps;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestUi extends TestBase {

    TestJustFoodPageCases testJustFoodPageCases = new TestJustFoodPageCases();


    @Test
    @Description("Доставка правильной еды на каждый день")
    public void negativeCheckJustFoodPageLayoutInChrome() throws IOException {
        testJustFoodPageCases.openPageJustFoodAndCheckTitle("https://www.justfood.pro/",
                "Доставка правильной еды на каждый день");
        LayoutSteps.compareCurrentPageWithBase("galen-specs/tabs.spec", "chrome");

    }

    @Test
    @Description("Доставка правильной еды на каждый день")
    public void positiveCheckJustFoodPageLayoutInChrome() throws IOException {
        testJustFoodPageCases.openPageJustFoodAndCheckTitle("https://www.justfood.pro/",
                "Доставка правильной еды на каждый день");
        LayoutSteps.compareCurrentPageWithBase("galen-specs/tabs1.spec", "chrome");

    }
}
