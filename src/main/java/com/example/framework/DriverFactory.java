package com.example.framework;

import com.microsoft.playwright.*;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DriverFactory {

    private static ThreadLocal<Playwright> playwrightThread = new ThreadLocal<>();
    private static ThreadLocal<Browser> browserThread = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> contextThread = new ThreadLocal<>();
    private static ThreadLocal<Page> pageThread = new ThreadLocal<>();

    private static ConfigReader configReader = new ConfigReader();
    private static boolean headless;

    /** Initialize Playwright, Browser, Context, and Page for current thread */
    public static void initDriver() {
        Playwright playwright = Playwright.create();
        playwrightThread.set(playwright);

        headless = configReader.getBooleanProperty("headless");
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));
        browserThread.set(browser);

        BrowserContext context = browser.newContext();
        contextThread.set(context);

        Page page = context.newPage();
        pageThread.set(page);
    }

    /** Getters for the current thread */
    public static Page getPage() {
        return pageThread.get();
    }

    public static BrowserContext getContext() {
        return contextThread.get();
    }

    public static Browser getBrowser() {
        return browserThread.get();
    }

    public static Playwright getPlaywright() {
        return playwrightThread.get();
    }

    /** Close everything for current thread */
    public static void quitDriver() {
        Page page = pageThread.get();
        if (page != null) page.close();

        BrowserContext context = contextThread.get();
        if (context != null) context.close();

        Browser browser = browserThread.get();
        if (browser != null) browser.close();

        Playwright playwright = playwrightThread.get();
        if (playwright != null) playwright.close();

        pageThread.remove();
        contextThread.remove();
        browserThread.remove();
        playwrightThread.remove();
    }

    /** Robust screenshot capture method (works for CI + local + manual) */
    public static void captureScreenshot(String testName) {
        if (!configReader.getBooleanProperty("screenshot.onFailure")) return;

        Page page = getPage();
        if (page == null) return;

        try {
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

            String folder = headless
                    ? "target/screenshots/ci/"
                    : "target/screenshots/local/";

            String finalPath = folder + testName + "_" + timestamp + ".png";

            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get(finalPath))
                    .setFullPage(true));

            System.out.println("Saved screenshot: " + finalPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Ensure screenshot directories exist */
    private static void createScreenshotDirectories() {
        new File("target/screenshots/ci").mkdirs();
        new File("target/screenshots/local").mkdirs();
    }
}