
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.File;
import java.util.Objects;

import static java.lang.Thread.sleep;


public class mainClass {

//System.setProperty("webdriver.chrome.driver","C:\\Users\\casper\\Desktop\\se\\ChromeDriver\\chromedriver.exe");
    protected static String ChromePath = "C:\\Users\\casper\\Desktop\\se\\ChromeDriver\\chromedriver.exe";

    private static ChromeDriverService service;
    private static WebDriver driver;

    public mainClass(String driver) {
    }


    public static void main(String[] args) throws InterruptedException {

        Setup();
        testThePage();
        searchProduct();
        selectProduct();
        addBasket();
        checkBasketPrice();

        //Add second one.
        turnBackItem();
        addBasket();
        itemNum();

        //Delete items.
        deleteItems();
        emptyMessage();



    }

    @BeforeAll
    public static void Setup() {
        //Start and run Chrome Driver;
        service = new ChromeDriverService.Builder().usingDriverExecutable(new File(ChromePath)).usingAnyFreePort().build();

        try{ service.start();
        } catch (Exception e) {
            e.printStackTrace();
        }


        driver.get("https://www.beymen.com/");
        driver.manage().window().maximize();

    }

    @AfterAll
    public static void Stop() {
        driver.quit();
        service.stop();

    }

    public static void scrollPageDown(){
        ((JavascriptExecutor)driver).executeScript("scroll(0,400)");

    }

    @Test
    public static void searchProduct() throws InterruptedException {
        //Search "pantolon" word.
        driver.findElement(By.className("default-input o-header__search--input")).sendKeys("Pantolon");
        //Wait a second after write "pantolon".
        Thread.sleep(1000);
        //Click the search button.
        driver.findElement(By.className("o-header__search--btn bmi-search")).click();
        //Scroll
        scrollPageDown();
        Thread.sleep(1000);
        //Show more
        driver.findElement(By.className("o-productMoreContent__btn btn")).click();

    }

    @Test
    public static void selectProduct(){
        //Choose an item.
        driver.findElement(By.className("m-productCard__title")).click();

    }

    @Test
    public static void addBasket(){
        //Pick the color -white-
        driver.findElement(By.className("m-variation__item -active")).click();
        //Pick the size.
        driver.findElement(By.name("34")).click();
        //Add basket.
        driver.findElement(By.id("addBasket")).click();
    }

    @Test
    public static void controlHomePage() throws NoSuchFieldException {
        Assertions.assertTrue(Objects.equals(driver, homePage.class.getField("hp")), "Something wrong!");


    }

    @Test
    public static void testThePage(){
        try {
            Assertions.assertTrue(Objects.isNull(By.className("icon icon-account")));

            Assertions.assertTrue(Objects.isNull(By.className("o-header__userInfo--item bwi-account-o -customer")));

            Assertions.assertTrue(Objects.isNull(By.className("o-header__userInfo--item bwi-cart-o -cart")));
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Test
    public static void deleteItems() {
        driver.findElement(By.className("m-basket__remove btn-text")).click();

    }

    @Test
    public static void checkBasketPrice(){
        Assertions.assertEquals(driver.findElement(By.className("m-price__new")).getText(),driver.findElement(By.className("m-productPrice__salePrice")).getText(),"There is difference between price.");

    }

    @Test
    public static void itemNum(){
        Assertions.assertEquals(driver.findElement(By.className("quantitySelect0")).getText(),2,"Check the number.");

    }

    @Test
    public static void turnBackItem(){
        driver.findElement(By.className("m-basket__productInfoCategory")).click();

    }

    @Test
    public static void emptyMessage(){
        Assertions.assertTrue(driver.findElement(By.className("m-empty__messageTitle")).isDisplayed());

    }
    

}
