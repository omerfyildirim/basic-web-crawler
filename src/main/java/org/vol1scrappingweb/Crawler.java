package org.vol1scrappingweb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import java.util.List;

public class Crawler {

    public static void main(String[] args) {
        String baseUrl = "https://homele.com/tr/properties?city_id=3";
        int pageCount = 10; // Toplam sayfa sayısı örneği, burada istenilen URL'in sayfa sayısı kadar girdisi ayarlanmalıdır.

        WebDriver driver = new SafariDriver();
        driver.manage().window().maximize();

        for (int page = 1; page <= pageCount; page++) {
            String url = baseUrl + "&page=" + page;
            driver.get(url);

            WebElement listingsContainer = driver.findElement(By.className("utf-listings-container-area"));
            List<WebElement> listings = listingsContainer.findElements(By.className("card-divider"));

            for (WebElement listing : listings) {
                WebElement titleElement = listing.findElement(By.className("text-truncate"));
                WebElement priceElement = listing.findElement(By.className("text-right"));
                WebElement descriptionElement = listing.findElement(By.className("address"));

                String id = listing.getAttribute("data-listing-id");
                String title = titleElement.getText();
                String price = priceElement.getText();
                String description = descriptionElement.getText();

                //ID ile description isteğe göre yer değiştirebilir.
                System.out.println("Page " + page);
                System.out.println("ID         : " + id);
                System.out.println("Title      : " + title);
                System.out.println("Price      : " + price);
                System.out.println("Description: " + description);
                System.out.println("------------------------------");
            }
        }

        driver.quit();
    }
}
