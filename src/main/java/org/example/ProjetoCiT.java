package org.example;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Locale;

public class ProjetoCiT {
    //usuario: testetrabalhocituna senha: 123456
    @Test
    public void verificarMensagemRetornoPesquisaInvalida() {
        /* Funcionalidade: Busca no Banco de Questões
           Cenário: Busca por questão inexistente
           Dado que navego para a página de busca do banco de questões
           E digito 'Science: Computers' no campo de busca
           Quando clico no botão de buscar
           Então visualizo uma mensagem de erro com o texto 'No questions found.' */
        ChromeDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 30);

        driver.navigate().to("https://opentdb.com/");

        String question = "Science: Computers";
        String validationMessage = "No questions found.";

        WebElement browse = driver.findElementByXPath("//a[@class='btn btn-primary']");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='btn btn-primary']")));

        browse.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='query']")));
        WebElement searchBox = driver.findElementById("query");
        WebElement searchButton = driver.findElementByXPath("//button[@role='button']");

        searchBox.click();
        searchBox.sendKeys(question);
        searchButton.click();

        WebElement returnMessage = driver.findElementByXPath("//div[@class='alert alert-danger']");

        Assert.assertEquals(validationMessage, returnMessage.getText());
    }
    @Test
    public void validarListagem25Itens() {
        /*Funcionalidade: Validação da listagem e paginação das questões.
          Cenário: Verificar a listagem de perguntas.
          Dado que navego para a página de busca do banco de questões
          E valido os elementos da página
          Então verifico a existência e funcionamento do elemento.*/
        ChromeDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 30);

        driver.navigate().to("https://opentdb.com/");

        WebElement browse = driver.findElementByXPath("//a[@class='btn btn-primary']");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='btn btn-primary']")));

        browse.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='page-top']/div[2]/table/tbody/tr[25]")));
        WebElement validatePageNumber = driver.findElementByXPath("//*[@id='page-top']/div[2]/table/tbody/tr[25]");

        Assert.assertNotNull(validatePageNumber);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class='pagination pagination-lg']")));
        WebElement validatePagination = driver.findElementByXPath("//ul[@class='pagination pagination-lg']");

        Assert.assertNotNull(validatePagination);
    }
    @Test
    public void validarLoginSistema() {
        /*Funcionalidade: Login no Open TDB
          Cenário: Processo de login no site.
          Dado que navego para a página de login
          E realizo o login com informações válidas
          Então verifico se o acesso foi feito corretamente.*/
        ChromeDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 30);

        String username = "testetrabalhocituna";
        String password = "123456";

        driver.navigate().to("https://opentdb.com/");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://opentdb.com/login.php']")));
        WebElement loginButton = driver.findElementByXPath("//a[@href='https://opentdb.com/login.php']");

        loginButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("username")));
        WebElement loginBox = driver.findElementById("username");
        WebElement passwordBox = driver.findElementById("password");
        WebElement submitButton = driver.findElementByXPath("//button[@type='submit']");

        loginBox.sendKeys(username);
        passwordBox.sendKeys(password);
        submitButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='menu-item dropdown']/a[@class='dropdown-toggle']")));
        WebElement loginText = driver.findElementByXPath("//li[@class='menu-item dropdown']/a[@class='dropdown-toggle']");

        Assert.assertEquals(username.toUpperCase(Locale.ROOT), loginText.getText());
    }
}