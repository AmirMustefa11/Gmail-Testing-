package autotest;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Scanner;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Autotest {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        Scanner input = new Scanner(System.in);

        String baseUrl = "https://www.gmail.com";

        System.out.print("Enter your email: ");
        String myUsername = input.next();

        System.out.print("Enter your password: ");
        String myPassword = input.next();

        driver.get(baseUrl);

        //signing into my email
        driver.findElement(By.id("identifierId")).sendKeys(myUsername);
        driver.findElement(By.id("identifierNext")).click();
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='password']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']")));
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(myPassword);
        driver.findElement(By.id("passwordNext")).click();

        //retrieving unread emails
        List<WebElement> unreadEmails = driver.findElements(By.className("zE"));

        //declaring a string builder to store the unread emails
        StringBuilder emails;
        emails = new StringBuilder();

        String noOfUnreadEmails = "Number of Unread Emails: " + unreadEmails.size() + "\n\n";
        emails.append(noOfUnreadEmails);

        //traversing each row and appending values
        for (WebElement message:
             unreadEmails) {
            emails.append(message.getText());
        }

        //writing to a file
        writeToFile(emails.toString());
    }

    private static void writeToFile(String content) {
        String fileNameToStoreEmails = ".\\src\\Emails.txt";

        try {
            Writer writer = new FileWriter(fileNameToStoreEmails);
            writer.write(content);
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
