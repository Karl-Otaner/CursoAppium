package br.com.carlos.appium.teste;


import br.com.carlos.appium.core.DriverFactory;
import br.com.carlos.appium.page.FormularioPage;
import br.com.carlos.appium.page.MenuPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;

import static org.junit.Assert.assertEquals;

public class FormularioTeste {

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
        Assert.assertTrue(formulario.isCheckMarcado());
        Assert.assertFalse(formulario.isSwitchMarcado());
    }


    @Test
    public void deveRealizarCadastro() throws MalformedURLException {

        //Preencher campos
        formulario.escreverNome("Carlos");
        formulario.clicarCheck();
        formulario.clicarSwitch();
        formulario.selecionarCombo("Nintendo Switch");

        //salvar
        formulario.salvar();

        //Resolução: Verificações
        assertEquals("Nome: Carlos", formulario.obterNomeCadastrado());
        assertEquals("Console: switch", formulario.obterConsole());
        Assert.assertTrue(formulario.obterSwitch().endsWith("Off"));
        Assert.assertTrue(formulario.obterCheck().endsWith("Marcado"));

    }
}
