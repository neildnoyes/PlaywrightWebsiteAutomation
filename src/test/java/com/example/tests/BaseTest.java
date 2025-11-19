package com.example.tests;

import com.example.framework.DriverFactory;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class BaseTest {
    protected BrowserContext context;
    protected Page page;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();       // initialize driver for this thread
        page = DriverFactory.getPage();   // get Page for this test
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // Capture screenshot if test fails
        if (!result.isSuccess()) {
            DriverFactory.captureScreenshot(result.getName());
        }

        // Close driver for this thread
        DriverFactory.quitDriver();
    }

    protected void takeScreenshot(String name) {
        DriverFactory.captureScreenshot(name);
    }
}
