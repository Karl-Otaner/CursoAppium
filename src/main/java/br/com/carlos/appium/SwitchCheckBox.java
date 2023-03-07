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

public class SwitchCheckBox {

    @Test
    public void deveInteragirSwitchCheckBox() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "Nexus5-teste");
        desiredCapabilities.setCapability("udid", "emulator-5554");
        desiredCapabilities.setCapability("automationName", "uiautomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, "C:\\Users\\carlos.silva\\WS\\CursoTesteAndroidAppium\\CursoAppium\\src\\main\\resources\\CTAppium_2_0.apk");


        AndroidDriver<MobileElement> driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Selecionar formulario
        driver.findElement(By.xpath("//*[@text='Formulário']")).click();

        //Verificar status dos elementos
        MobileElement check = driver.findElement(By.className("android.widget.CheckBox"));
        MobileElement switc = driver.findElement(MobileBy.AccessibilityId("switch"));
        Assert.assertTrue(check.getAttribute("checked").equals("false"));
        Assert.assertTrue(switc.getAttribute("checked").equals("true"));


        //Clicar nos elementos
        check.click();
        switc.click();


        //Verificar estados alterados
        Assert.assertFalse(check.getAttribute("checked").equals("false"));
        Assert.assertFalse(switc.getAttribute("checked").equals("true"));

        driver.quit();

    }
}
