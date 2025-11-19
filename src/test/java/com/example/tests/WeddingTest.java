package com.example.tests;

import com.example.framework.ConfigReader;
import com.example.framework.pages.GoogleHomePage;
import com.example.framework.pages.WeddingHomePage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class WeddingTest extends BaseTest {

    private ConfigReader config = new ConfigReader();

    @Test
    public void weddingNavigationTest() {

        //navigate to wedding page
        String baseUrl = config.getProperty("wedding.base.url");
        page.navigate(baseUrl);

        //perform navigation test
        WeddingHomePage weddingHomePage = new WeddingHomePage(page);
        weddingHomePage.performNavigation();

    }
}
