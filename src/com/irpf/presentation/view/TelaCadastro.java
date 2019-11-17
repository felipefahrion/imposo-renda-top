package com.irpf.presentation.view;

import com.irpf.presentation.controller.HomeController;
import com.irpf.presentation.model.CadastroModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaCadastro implements Initializable {

    @FXML
    private TextField nomeCompleto;

    @FXML
    private TextField idade;

    @FXML
    private ChoiceBox numeroDependentes;

    @FXML
    private TextField contribuicaoOficial;

    @FXML
    private TextField rendimentoTotal;

    @FXML
    private Button calcular;

    @FXML
    private Button cadastrar;

    @FXML
    private TextField valorIrpf;

    @FXML
    private void calcular() {
        CadastroModel cadastroModel = new CadastroModel();
        cadastroModel.setContribuicaoOficial(new BigDecimal(contribuicaoOficial.getText()));
        cadastroModel.setIdade(Integer.valueOf(idade.getText()));
        cadastroModel.setNomeCompleto(nomeCompleto.getText());
        cadastroModel.setNumeroDependentes(Integer.valueOf(numeroDependentes.getValue().toString()));
        new HomeController().calcularIrpf(cadastroModel);
        System.out.println("Calculo " + cadastroModel);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
