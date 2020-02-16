package org.jalasoft;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConfirmationPage {

    public static String getTextBanner(WebDriverWait wait) {
        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert")));
        return alert.getText();
    }
}
