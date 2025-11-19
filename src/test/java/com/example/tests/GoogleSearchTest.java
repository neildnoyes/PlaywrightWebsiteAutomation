package com.example.tests;

import com.example.framework.ConfigReader;
import com.example.framework.pages.GoogleHomePage;
import com.example.framework.pages.GoogleResultsPage;
import com.microsoft.playwright.Page;
import org.testng.annotations.Test;

import java.nio.file.Paths;

import static org.testng.Assert.assertEquals;

public class GoogleSearchTest extends BaseTest {

    private ConfigReader config = new ConfigReader();

    @Test
    public void googleSearchTest() {

        String baseUrl = config.getProperty("base.url");
        String searchText = config.getProperty("search.text");

        page.navigate(baseUrl);

        GoogleHomePage homePage = new GoogleHomePage(page);
        homePage.enterSearchText(searchText);

        String enteredText = homePage.getSearchBoxValue();
        System.out.println("Entered text: " + enteredText);

        assertEquals(enteredText, searchText, "The search box should contain the entered text");

    }
}
