package com.playtika;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;


public class Kot {
    public static BrowserWebDriverContainer browser = new BrowserWebDriverContainer();


    @DataProvider(name = "browser", parallel = true)
    public Object[][] provideInvalidIds() {
        return new Object[][]{{"c"}, {"f"}};
    }

    @AfterTest
    public static void tearDown() throws Exception {
        browser.stop();
    }

    @Test(dataProviderClass = Kot.class, dataProvider = "browser")
    public void test(String browser) throws InterruptedException {
        openBrowser(browser);
        Selenide.open("http://ukr.net");
    }

    public void openBrowser(String param) {
        if (param.equalsIgnoreCase("c")) {
            browser.withDesiredCapabilities(DesiredCapabilities.chrome())
                    .withRecordingMode(BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL, new File("./target/video"));
            System.out.println("AAAAAAAAAAAAAAAAAAA");
        }
        if (param.equalsIgnoreCase("f")) {
            browser.withDesiredCapabilities(DesiredCapabilities.firefox())
                    .withRecordingMode(BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL, new File("./target/video2"));
            System.out.println("BBBBB");
        }
        browser.start();
        RemoteWebDriver driver = browser.getWebDriver();
        WebDriverRunner.setWebDriver(driver);
    }


}
