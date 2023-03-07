package br.com.carlos.appium;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class InstalacaoApk {

        @Test
        public void deveInstalarApk() throws MalformedURLException {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability("platformName", "Android");
            desiredCapabilities.setCapability("deviceName", "Nexus5-teste");
            desiredCapabilities.setCapability("udid", "emulator-5554");
            //desiredCapabilities.setCapability("automationName", "uiautomator2");
            //desiredCapabilities.setCapability("appPackage", "com.android.calculator2");
            desiredCapabilities.setCapability(MobileCapabilityType.APP, "C:\\Users\\carlos.silva\\WS\\CursoTesteAndroidAppium\\CursoAppium\\src\\main\\resources\\CTAppium_2_0.apk");

            AndroidDriver<MobileElement> driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);

            driver.quit();
        }
}
