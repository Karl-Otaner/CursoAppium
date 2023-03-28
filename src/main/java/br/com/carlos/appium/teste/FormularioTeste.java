package br.com.carlos.appium.teste;


import br.com.carlos.appium.core.DSL;
import br.com.carlos.appium.core.DriverFactory;
import br.com.carlos.appium.page.FormularioPage;
import br.com.carlos.appium.page.MenuPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import java.net.MalformedURLException;

import static org.junit.Assert.assertEquals;

public class FormularioTeste {

    private DSL dsl = new DSL();
    private MenuPage menu = new MenuPage();
    private FormularioPage formulario = new FormularioPage();


    @Before
    public void  inicializarAppium() throws MalformedURLException {
        menu.acessarFormulario();
    }

    @After
    public void tearDown(){
        DriverFactory.killDriver();

    }

    @Test
    public void devePreencherCampoTexto() throws MalformedURLException {
        //Escrever nome
        formulario.escreverNome("Carlos");
        assertEquals("Carlos", formulario.obterNome());
    }

    @Test
    public void deveInteragirCombo() throws MalformedURLException {

        //Clicar no combo
        formulario.selecionarCombo("Nintendo Switch");

        //Selecionar opção desejada
        assertEquals("Nintendo Switch", formulario.obterValorCombo());

    }

    @Test
    public void deveInteragirSwitchCheckBox() throws MalformedURLException {

        //Verificar status dos elementos
        Assert.assertFalse(formulario.isCheckMarcado());
        Assert.assertTrue(formulario.isSwitchMarcado());

        //Clicar nos elementos
        formulario.clicarCheck();
        formulario.clicarSwitch();

        //Verificar estados alterados
        Assert.assertTrue(formulario.isSwitchMarcado());
        Assert.assertFalse(formulario.isSwitchMarcado());
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
