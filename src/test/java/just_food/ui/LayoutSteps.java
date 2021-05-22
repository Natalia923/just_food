package just_food.ui;

import com.galenframework.api.Galen;
import com.galenframework.reports.model.LayoutReport;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.assertj.core.api.Fail.fail;

public class LayoutSteps {

//    @Step("Проверить соответствие страницы/формы/блока ожидаемой спецификации {spec}")
//    public void compareCurrentPageWithBase(String spec) throws IOException {
//        checkLayoutAccordingToSpec(spec, null);
//    }

    @Step("Проверить соответствие страницы/формы/блока ожидаемой спецификации {spec} с аннотацией {tag}")
    public static void compareCurrentPageWithBase(String spec, String tag) throws IOException {
        List<String> tags = new ArrayList<>();
        tags.add(tag);
        checkLayoutAccordingToSpec(spec, tags);
    }

    private static void checkLayoutAccordingToSpec(String spec, List<String> tags) throws IOException {
        LayoutReport report = Galen.checkLayout(getWebDriver(), System.getProperty("user.dir") + "/src/main/resources/" + spec, tags);
        report.getFileStorage().copyAllFilesTo(new File(System.getProperty("user.dir") + "/build/results-img/"));
        if (report.errors() > 0) {
            embedScreenshotAndFail(report);
            fail(report.getValidationErrorResults().toString());
        }
    }

    private static void embedScreenshotAndFail(LayoutReport report) {
        Map<String, File> screenshots = report.getFileStorage().getFiles();
        screenshots.forEach((key, value) -> {
            if (key.contains("map") || key.contains("expected") || key.contains("actual")) {
                try {
                    attachGalenScreenshotToReport(key, value);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Attachment(value = "{message}", type = "image/png")
    private static byte[] attachGalenScreenshotToReport(String message, File file) throws IOException {
        return FileUtils.readFileToByteArray(file);
    }
}