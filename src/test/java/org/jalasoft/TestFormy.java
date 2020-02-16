package org.jalasoft;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class TestFormy {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setup() {
        String pathToDriver = "lib/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", pathToDriver);
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void AutocompleteTest() {
        driver.get("https://formy-project.herokuapp.com/autocomplete");

        WebElement autocomplete = driver.findElement(By.id("autocomplete"));
        autocomplete.sendKeys("Cochabamba");

        WebElement autocompleteResult = wait.until(presenceOfElementLocated(By.className("pac-item")));
        autocompleteResult.click();

        assertEquals("Cochabamba, Bolivia", autocomplete.getAttribute("value"));
        driver.quit();
    }

    @Test
    public void AutocompleteExplicitTimeTest() {
        driver.get("https://formy-project.herokuapp.com/autocomplete");

        WebElement autocomplete = driver.findElement(By.id("autocomplete"));
        autocomplete.sendKeys("Cochabamba");

        WebElement autocompleteResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("pac-item")));
        autocompleteResult.click();

        assertEquals("Cochabamba, Bolivia", autocomplete.getAttribute("value"));
        driver.quit();
    }

    @Test
    public void AutocompleteImplicitTimeTest() {
        driver.get("https://formy-project.herokuapp.com/autocomplete");

        WebElement autocomplete = driver.findElement(By.id("autocomplete"));
        autocomplete.sendKeys("Cochabamba");

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        WebElement autocompleteResult = driver.findElement(By.className("pac-item"));
        autocompleteResult.click();

        assertEquals("Cochabamba, Bolivia", autocomplete.getAttribute("value"));
        driver.quit();
    }

    @Test
    public void ScrollToElementTest() {
        driver.get("https://formy-project.herokuapp.com/scroll");

        WebElement name = driver.findElement(By.id("name"));
        Actions actions = new Actions(driver);
        actions.moveToElement(name);
        name.sendKeys("Lucero Quiroga");

        WebElement date = driver.findElement(By.id("date"));
        date.sendKeys("16/02/2020");

        driver.quit();
    }

    @Test
    public void SwitchToActiveWindowTest() {
        driver.get("https://formy-project.herokuapp.com/switch-window");
        WebElement newTabButton = driver.findElement(By.id("new-tab-button"));
        newTabButton.click();

        String originalHandle = driver.getWindowHandle();

        for(String handle: driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        WebElement buttons = driver.findElement(By.linkText("Buttons"));
        buttons.click();

        driver.switchTo().window(originalHandle);

        driver.quit();
    }

    @Test
    public void SwitchToAlertTest() {
        driver.get("https://formy-project.herokuapp.com/switch-window");
        WebElement alertButton = driver.findElement(By.id("alert-button"));
        alertButton.click();

        Alert alert = driver.switchTo().alert();
        alert.accept();

        driver.quit();
    }

    @Test
    public void ExecuteJavascrptTest() {
        driver.get("https://formy-project.herokuapp.com/modal");

        WebElement modalButton = driver.findElement(By.id("modal-button"));
        modalButton.click();

        WebElement closeButton = wait.until(presenceOfElementLocated(By.id("close-button")));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", closeButton);

        driver.quit();
    }

    @Test
    public void DragAndDropTest() {
        driver.get("https://formy-project.herokuapp.com/dragdrop");

        WebElement image = driver.findElement(By.id("image"));

        WebElement box = driver.findElement(By.id("box"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(image, box).build().perform();

        WebElement title = driver.findElement(By.cssSelector("h1"));
        title.click();

        driver.quit();
    }

    @Test
    public void RadioButtonsTest() throws InterruptedException {
        driver.get("https://formy-project.herokuapp.com/radiobutton");

        WebElement radioButton1 = driver.findElement(By.id("radio-button-1"));
        radioButton1.click();
        Thread.sleep(1000);

        WebElement radioButton2 = driver.findElement(By.cssSelector("input[value='option2']"));
        radioButton2.click();
        Thread.sleep(1000);

        WebElement radioButton3 = driver.findElement(By.xpath("/html/body/div/div[3]/input"));
        radioButton3.click();
        Thread.sleep(1000);

        driver.quit();
    }

    @Test
    public void CheckBoxesTest() throws InterruptedException {
        driver.get("https://formy-project.herokuapp.com/checkbox");

        WebElement checkbox1 = driver.findElement(By.id("checkbox-1"));
        WebElement checkbox2 = driver.findElement(By.cssSelector("input[id$='2']"));
        WebElement checkbox3 = driver.findElement(By.cssSelector("input#checkbox-3"));

        checkbox3.click();
        Thread.sleep(1000);
        checkbox1.click();
        Thread.sleep(1000);
        checkbox2.click();
        Thread.sleep(1000);

        driver.quit();
    }

    @Test
    public void DatePickerTest() {
        driver.get("https://formy-project.herokuapp.com/datepicker");

        WebElement dateField = driver.findElement(By.id("datepicker"));
        dateField.sendKeys("01/15/2020");
        dateField.sendKeys(Keys.RETURN);

        driver.quit();
    }

    @Test
    public void DropdownTest() {
        driver.get("https://formy-project.herokuapp.com/dropdown");
        WebElement dropDownMenu = driver.findElement(By.id("dropdownMenuButton"));
        dropDownMenu.click();

        WebElement autoCompleteItem = driver.findElement(By.id("autocomplete"));
        autoCompleteItem.click();

        driver.quit();
    }

    @Test
    public void FileUploadTest() {
        driver.get("https://formy-project.herokuapp.com/fileupload");

        WebElement fileUploadField = driver.findElement(By.id("file-upload-field"));
        fileUploadField.sendKeys("H:\\Trabajos\\conexionesNoPresenciales.txt");

        driver.quit();
    }

    @Test
    public void FormTest() {
        driver.get("https://formy-project.herokuapp.com/form");
        FormPage.submitForm(driver);
        assertEquals("The form was successfully submitted!", ConfirmationPage.getTextBanner(wait));
        driver.quit();
    }
}
