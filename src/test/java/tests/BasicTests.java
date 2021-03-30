package tests;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import config.WebConfig;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class BasicTests {

    @Test
    @Tag("web")
    @Story("Main page navigation")
    @Feature("Solution list")
    @DisplayName("Test.01.Find the SAAS in solution list")
    void findSAASInSolutionsTest() {

        final WebConfig config = ConfigFactory.create(WebConfig.class, System.getProperties());

        step("Open main page", () -> open(config.getBaseURL()));

        step("Navigate to SaaS page", () -> {
            $("#navigation").$(byText("Solutions")).click();
            $("#header-SaaS").click();
        });

        step("Verify the SaaS page is opened", () -> {
            $(byText("Defining SaaS")).shouldBe(Condition.visible);
            $(byText("Benefits of SaaS")).shouldBe(Condition.visible);
        });

    }

    @Test
    @Tag("web")
    @Story("multi-language functionality")
    @Feature("Country-selector")
    @DisplayName("Test.02. Choose Deutschland (Deutsch)")
    void chooseDeutschLanguageTest() {

        final WebConfig config = ConfigFactory.create(WebConfig.class, System.getProperties());

        step("Open main page", () -> open(config.getBaseURL()));

        step("Choose Deutsch language in header", () -> {
            $("#navigation").$(".selected-country").click();
            $(".country-selector").$(byText("Deutschland (Deutsch)")).click();
        });

        step("Verify that language have been set", () -> {
            $(byText("Unternehmen")).shouldBe(Condition.visible);
        });
    }

    @Test
    @Tag("web")
    @Story("Social media")
    @Feature("Social media links")
    @DisplayName("Test.03.Verify correct social media links on main page")
    void verifyCorrectSocialMediaLinkOnMainPage() {

        final WebConfig config = ConfigFactory.create(WebConfig.class, System.getProperties());

        step("Open main page", () -> open(config.getBaseURL()));

        step("Verify correct social media links in footer", () -> {
            $(".social").$(byAttribute("href", "https://www.facebook.com/ingrammicrocloud")).shouldBe(visible);
            $(".social").$(byAttribute("href", "https://twitter.com/IngramCloud")).shouldBe(visible);
            $(".social").$(byAttribute("href", "https://www.linkedin.com/company/im_cloud/")).shouldBe(visible);
            $(".social").$(byAttribute("href", "https://www.youtube.com/user/IngramMicroServices")).shouldBe(visible);
        });
    }
}
