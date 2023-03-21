package br.com.carlos.appium.page;

import br.com.carlos.appium.core.DSL;
import io.appium.java_client.MobileBy;

public class FormularioPage {

    DSL dsl = new DSL();

    public void escreverNome(String nome){
        dsl.escrever(MobileBy.AccessibilityId("nome"), nome);
    }

    public String obterNome(){
        return dsl.obterTexto(MobileBy.AccessibilityId("nome"));
    }
}
