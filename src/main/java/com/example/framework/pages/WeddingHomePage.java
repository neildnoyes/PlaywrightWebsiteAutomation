package com.example.framework.pages;

import com.example.framework.DriverFactory;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class WeddingHomePage extends BasePage {
    private Locator homeTab;
    private Locator accommodationsTab;
    private Locator weekendTab;
    private Locator faqTab;
    private Locator rsvpTab;

    public WeddingHomePage(Page page) {
        super(page);
        homeTab = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Home"));
        accommodationsTab = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Accommodations & Travel"));
        weekendTab = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("The Weekend"));
        faqTab = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("FAQ"));
        rsvpTab = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("RSVP"));
    }

    public void performNavigation() {

        homeTab.click();
        assertThat(page).hasURL(Pattern.compile(".*/index\\.html$"));
        DriverFactory.captureScreenshot("home_navigation");

        accommodationsTab.click();
        assertThat(page).hasURL(Pattern.compile(".*/accommodations\\.html$"));
        DriverFactory.captureScreenshot("accommodations_navigation");

        weekendTab.click();
        assertThat(page).hasURL(Pattern.compile(".*/weekend\\.html$"));
        DriverFactory.captureScreenshot("weekend_navigation");

        faqTab.click();
        assertThat(page).hasURL(Pattern.compile(".*/faq\\.html$"));
        DriverFactory.captureScreenshot("faq_navigation");

        rsvpTab.click();
        assertThat(page).hasURL(Pattern.compile(".*/rsvp\\.html$"));
        DriverFactory.captureScreenshot("rsvp_navigation");



    }

}
