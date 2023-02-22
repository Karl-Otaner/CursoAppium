package br.com.carlos.cursoAppium;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DesafioCadastro {

    @Test
    public void deveFazerUmCadastro() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "Nexus5-teste");
        desiredCapabilities.setCapability("udid", "emulator-5554");
        desiredCapabilities.setCapability("automationName", "uiautomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, "C:\\Users\\carlos.silva\\WS\\CursoTesteAndroidAppium\\CursoAppium\\src\\main\\resources\\CTAppium_2_0.apk");


        AndroidDriver<MobileElement> driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Selecionar formulario
        driver.findElement(By.xpath("//android.widget.TextView[@Text='Formulário']")).click();

        //Preencher campos
        //Campo nome
        MobileElement campoNome = driver.findElement(MobileBy.AccessibilityId("nome"));
        campoNome.sendKeys("Carlos");

        //Abrir ComboBox
        driver.findElement(MobileBy.AccessibilityId("console")).click();

        //Escolher opção ComboBox
        driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='PS4']")).click();

        //Verificar CheckBox e Switch
        MobileElement check = driver.findElement(MobileBy.AccessibilityId("check"));
        MobileElement switc = driver.findElement(MobileBy.AccessibilityId("switch"));
        Assert.assertTrue(check.getAttribute("checked").equals("false"));
        Assert.assertTrue(switc.getAttribute("checked").equals("true"));

        //Marca CheckBox e Switch
        check.click();
        switc.click();


        //Salvar
        driver.findElement(By.className("SALVAR")).click();






        //Salvar Formulario

        //Verificar estados alterados


        //Validar campos

        driver.quit();

    }
}
