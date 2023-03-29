package br.com.carlos.appium.page;

import br.com.carlos.appium.core.DSL;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public class FormularioPage {

    DSL dsl = new DSL();

    public void escreverNome(String nome){
        dsl.escrever(MobileBy.AccessibilityId("nome"), nome);
    }

    public String obterNome(){
        return dsl.obterTexto(MobileBy.AccessibilityId("nome"));
    }

    public void selecionarCombo(String valor){
        dsl.selecionarCombo(MobileBy.AccessibilityId("console"), valor);
    }

    public String obterValorCombo(){
        return dsl.obterTexto(By.xpath("//android.widget.Spinner/android.widget.TextView"));
    }

    public void clicarCheck() {
        dsl.clicar(By.className("android.widget.CheckBox"));
    }

    public void clicarSwitch() {
        dsl.clicar(MobileBy.AccessibilityId("switch"));
    }

    public void salvar(){
        dsl.clicarPorTexto("SALVAR");

    }
    public boolean isCheckMarcado() {

        return dsl.isCheckMarcado(By.className("android.widget.CheckBox"));
    }

    public boolean isSwitchMarcado() {
        return dsl.isCheckMarcado(MobileBy.AccessibilityId("switch"));
    }

    public String obterNomeCadastrado(){

        return dsl.obterTexto(By.xpath("//android.widget.TextView[starts-with(@text, 'Nome:')]"));
    }
    public String obterConsole(){
        return dsl.obterTexto(By.xpath("//android.widget.TextView[starts-with(@text, 'Console:')]"));
    }
    public String obterCheck(){
     return dsl.obterTexto(By.xpath("//android.widget.TextView[starts-with(@text, 'Checkbox:')]"));

    }
    public String obterSwitch(){
        return dsl.obterTexto(By.xpath("//android.widget.TextView[starts-with(@text, 'Switch:')]"));
    }

}
