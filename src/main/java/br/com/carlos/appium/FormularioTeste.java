package br.com.carlos.appium;

import br.com.carlos.core.DriverFactory;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import java.net.MalformedURLException;

public class FormularioTeste {

    private AndroidDriver<MobileElement> driver;

    @Before
    public void  inicializarAppium() throws MalformedURLException {
       driver = DriverFactory.getDriver();

        //Selecionar formulario
        driver.findElement(By.xpath("//*[@text='Formulário']")).click();
    }

    @After
    public void tearDown(){
        DriverFactory.killDriver();
    }

    @Test
    public void devePreencherCampoTexto() throws MalformedURLException {

        //Escrever nome
        MobileElement campoNome = driver.findElement(MobileBy.AccessibilityId("nome"));
        campoNome.sendKeys("Carlos");

        //Checar nome escrito
        String text = campoNome.getText();
        Assert.assertEquals("Carlos", text);

    }

    @Test
    public void deveInteragirCombo() throws MalformedURLException {

        //Selecionar formulario
        driver.findElement(By.xpath("//android.widget.TextView[@text='Formulário']")).click();

        //Clicar no combo
        driver.findElement(MobileBy.AccessibilityId("console")).click();

        //Selecionar opção desejada
        driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='Nintendo Switch']")).click();

        //Verificar opção selecionada
        String text = driver.findElement(By.xpath("//android.widget.Spinner/android.widget.TextView")).getText();
        Assert.assertEquals("Nintendo Switch", text);

    }

    @Test
    public void deveInteragirSwitchCheckBox() throws MalformedURLException {

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
    }


    @Test
    public void deveFazerUmCadastro() throws MalformedURLException {

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

        driver.findElement(By.xpath("//*[@text='SALVAR']")).click();


        //Resolução: Verificações
        MobileElement nome = driver.findElement(By.xpath("//android.widget.TextView[@text='Nome: Carlos']"));
        Assert.assertEquals("Nome: Carlos", nome.getText());

        MobileElement combo = driver.findElement(By.xpath("//android.widget.TextView[starts-with(@text, 'Console:')]"));
        Assert.assertEquals("Console: ps4", combo.getText());

        MobileElement swit = driver.findElement(By.xpath("//android.widget.TextView[starts-with(@text, 'Switch:')]"));
        Assert.assertTrue(swit.getText().endsWith("Off"));

        MobileElement checkid = driver.findElement(By.xpath("//android.widget.TextView[starts-with(@text, 'Checkbox:')]"));
        Assert.assertTrue(checkid.getText().endsWith("Marcado"));

    }
}
