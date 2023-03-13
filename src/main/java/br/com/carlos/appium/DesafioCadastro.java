package br.com.carlos.appium;

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
        driver.findElement(By.xpath("//android.widget.TextView[@text='Formulário']")).click();

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

        //Verificar CheckBox e Switch - Resolução da aula
        //driver.findElement(By.className("android.widget.EditText")).sendKeys("Carlos");
        //driver.findElement(By.className("android.widget.CheckBox")).click();
        //driver.findElement(By.className("android.widget.Switch")).click();
        //driver.findElement(By.className("android.widget.Spinner")).click();
        //driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='PS4']")).click();



        //Salvar Formulario
        //driver.findElement(By.className("SALVAR")).clic;k(); - erro
        driver.findElement(By.xpath("//*[@text='SALVAR']")).click();


        //Verificar estados alterados
        // String nome = campoNome.getText();
        //Assert.assertEquals("Carlos", campoNome);

        //String ps4 = driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='PS4']")).getText();
        //Assert.assertEquals("PS4", ps4);

        //String switOff = driver.findElement(By.className("Switch: Off")).getText();
        // String checkMarcado = driver.findElement(By.className("Checkbox: Marcado")).getText();

        // Assert.assertEquals("Switch: Off", switOff);
        // Assert.assertEquals("Switch: Off", checkMarcado)


        //Resolução: Verificações
        MobileElement nome = driver.findElement(By.xpath("//android.widget.TextView[@text='Nome: Carlos']"));
        Assert.assertEquals("Nome: Carlos", nome.getText());

        MobileElement combo = driver.findElement(By.xpath("//android.widget.TextView[starts-with(@text, 'Console:')]"));
        Assert.assertEquals("Console: ps4", combo.getText());

        MobileElement swit = driver.findElement(By.xpath("//android.widget.TextView[starts-with(@text, 'Switch:')]"));
        Assert.assertTrue(swit.getText().endsWith("Off"));

        MobileElement checkid = driver.findElement(By.xpath("//android.widget.TextView[starts-with(@text, 'Checkbox:')]"));
        Assert.assertTrue(checkid.getText().endsWith("Marcado"));


        //driver.quit();

    }
}
