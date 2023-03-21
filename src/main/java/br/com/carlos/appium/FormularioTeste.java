package br.com.carlos.appium;


import br.com.carlos.appium.core.DSL;
import br.com.carlos.appium.core.DriverFactory;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import java.net.MalformedURLException;

import static org.junit.Assert.assertEquals;

public class FormularioTeste {

    private AndroidDriver<MobileElement> driver;
    private DSL dsl = new DSL();


    @Before
    public void  inicializarAppium() throws MalformedURLException {
        driver = DriverFactory.getDriver();
        dsl.clicarPorTexto("Formulário");
    }

    @After
    public void tearDown(){
        DriverFactory.killDriver();

    }

    @Test
    public void devePreencherCampoTexto() throws MalformedURLException {
        //Escrever nome
        dsl.escrever(MobileBy.AccessibilityId("nome"), "Carlos");
        assertEquals("Carlos", dsl.obterTexto(MobileBy.AccessibilityId("nome")));
    }

    @Test
    public void deveInteragirCombo() throws MalformedURLException {

        //Clicar no combo
        dsl.selecionarCombo(MobileBy.AccessibilityId("console"), "Nintendo Switch");

        //Selecionar opção desejada
        String text = dsl.obterTexto(By.xpath("//android.widget.Spinner/android.widget.TextView"));
        assertEquals("Nintendo Switch", text);

    }

    @Test
    public void deveInteragirSwitchCheckBox() throws MalformedURLException {

        //Verificar status dos elementos
        Assert.assertFalse(dsl.isCheckMarcado(By.className("android.widget.CheckBox")));
        Assert.assertTrue(dsl.isCheckMarcado(MobileBy.AccessibilityId("switch")));

        //Clicar nos elementos
        dsl.clicar(By.className("android.widget.CheckBox"));
        dsl.clicar(MobileBy.AccessibilityId("switch"));

        //Verificar estados alterados
        Assert.assertTrue(dsl.isCheckMarcado(By.className("android.widget.CheckBox")));
        Assert.assertFalse(dsl.isCheckMarcado(MobileBy.AccessibilityId("switch")));
    }


    @Test
    public void deveRealizarCadastro() throws MalformedURLException {

        //Preencher campos
        dsl.escrever(By.className("android.widget.EditText"), "Carlos");
        dsl.clicar(By.className("android.widget.CheckBox"));
        dsl.clicar(By.className("android.widget.Switch"));
        dsl.selecionarCombo(By.className("android.widget.Spinner"), "Nintendo Switch");

        //salvar
        dsl.clicarPorTexto("SALVAR");

        //Resolução: Verificações
        assertEquals("Nome: Carlos", dsl.obterTexto(By.xpath("//android.widget.TextView[@text='Nome: Carlos']")));
        assertEquals("Console: switch", dsl.obterTexto(By.xpath("//android.widget.TextView[starts-with(@text, 'Console:')]")));
        Assert.assertTrue(dsl.obterTexto(By.xpath("//android.widget.TextView[starts-with(@text, 'Switch:')]")).endsWith("Off"));
        Assert.assertTrue(dsl.obterTexto(By.xpath("//android.widget.TextView[starts-with(@text, 'Checkbox:')]")).endsWith("Marcado"));

    }
}
