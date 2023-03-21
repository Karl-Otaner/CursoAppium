package br.com.carlos.appium.page;

import br.com.carlos.appium.core.DSL;

public class MenuPage {

    private DSL dsl = new DSL();


    public void acessarFormulario(){
        dsl.clicarPorTexto("Formulário");
    }

}
