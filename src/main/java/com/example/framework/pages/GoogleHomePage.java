package com.example.framework.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;

public class GoogleHomePage extends BasePage {

    private Locator searchBox;

    public GoogleHomePage(Page page) {
        super(page);
        searchBox = page.locator("textarea[name='q']");
    }

    public void enterSearchText(String text) {
        searchBox.fill(text);
    }

    public String getSearchBoxValue() {
        return searchBox.inputValue();
    }

}
