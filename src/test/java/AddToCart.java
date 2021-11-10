import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AddToCart {
    public static void main(String[] args) {
        // Setting up Chrome driver path.
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");

        // Launching Chrome browser.
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        // Maximize the window
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, 30);

        // Opening the website
        driver.get("https://www.webstaurantstore.com/");

        System.out.println("Browser Opened");
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);
        System.out.println("Page Title " + driver.getTitle());

        // Searching the item stainless work table
        driver.findElement(By.id("searchval")).sendKeys("stainless work table");


        // Clicking the Search Button
        driver.findElement(By.xpath("//*[@id=\"searchForm\"]/div/button")).click();

        /* This is how we selected the list of items on the 9th page. */
        driver.findElement(By.linkText("9")).click();
        List<WebElement> listOfAllProductsIn9thPage = driver.findElements(By.cssSelector("div#details a[data-testid='itemDescription']"));
        System.out.println(listOfAllProductsIn9thPage.size());

        /*Need to iterate for other pages*/
        /*Checking if all the product contains the word "Table" in 9th page*/
        for(WebElement e : listOfAllProductsIn9thPage) {
            String productName = e.getText();
            if (productName.contains("Table")){
                System.out.println("This product contains the word - Table");
            }
        }

        /*Selecting the last item on the 9th page*/
        WebElement lastProductOfThePage = listOfAllProductsIn9thPage.get(listOfAllProductsIn9thPage.size()-1);
        lastProductOfThePage.click();

        driver.findElement(By.cssSelector("div#buyButtonContainer input#buyButton")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.success-container button.btn.btn-primary")));

        driver.findElement(By.cssSelector("div.success-container button.btn.btn-primary")).click();

        driver.findElement(By.linkText("Empty Cart")).click();

        driver.findElement(By.cssSelector("div.modal-footer button.btn.btn-primary")).click();

        //driver.findElement(By.cssSelector("div>div.modal--collapse.show button.btn.btn-light")).click();

        driver.close();

    }
}
