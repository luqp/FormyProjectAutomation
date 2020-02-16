package org.jalasoft;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FormPage {

    public static void submitForm(WebDriver driver) {
        driver.findElement(By.id("first-name")).sendKeys("Lucero");
        driver.findElement(By.id("last-name")).sendKeys("Quiroga");
        driver.findElement(By.id("job-title")).sendKeys("Developer");
        driver.findElement(By.id("radio-button-3")).click();
        driver.findElement(By.id("checkbox-3")).click();
        driver.findElement(By.id("select-menu")).click();
        driver.findElement(By.cssSelector("#select-menu option[value='2']")).click();
        WebElement date = driver.findElement(By.id("datepicker"));
        date.sendKeys("02/01/2020");
        date.sendKeys(Keys.RETURN);

        driver.findElement(By.cssSelector("a[role='button']")).click();
    }
}
