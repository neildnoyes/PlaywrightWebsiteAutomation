package com.example.framework.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;

public class GoogleResultsPage extends BasePage {

    private Locator firstResult;

    public GoogleResultsPage(Page page) {
        super(page);
        firstResult = page.locator("h3").first();
    }

    public String getFirstResultText() {
        return firstResult.textContent();
    }
}
