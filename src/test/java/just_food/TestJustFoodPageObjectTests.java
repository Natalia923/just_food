package just_food;

import org.junit.jupiter.api.Test;

public class TestJustFoodPageObjectTests extends TestBase{

    TestJustFoodPageCases testJustFoodPageCases = new TestJustFoodPageCases();

    @Test
    void positiveTestJustFood() {
        Long age = getRandomLong(0,99);

        testJustFoodPageCases.openPageJustFoodAndCheckTitle("https://www.justfood.pro/",
                "Питание с научным подходом");
        testJustFoodPageCases.checkMainBlocksOfJustFood();
        testJustFoodPageCases.checkWhyBlockOfJustFood();
        testJustFoodPageCases.checkFormForOrder();
        testJustFoodPageCases.openCalculatorForm();
        testJustFoodPageCases.checkСalorieСalculation(String.valueOf(age));

    }

    @Test
    void negativeTestJustFood() {

        testJustFoodPageCases.openPageJustFoodAndCheckTitle("https://www.justfood.pro/",
                "Питание с научным подходом");
        testJustFoodPageCases.openCalculatorForm();
        testJustFoodPageCases.checkNegativeСalorieСalculation();
    }
}
