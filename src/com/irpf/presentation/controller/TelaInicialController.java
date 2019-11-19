package com.irpf.presentation.controller;

import com.irpf.business.IrpfFacade;
import com.irpf.repository.dto.ContribuinteDTO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaInicialController implements Initializable {

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
    private Button cadastrar;

    @FXML
    private TextField valorIrpf;

    @FXML
    private MenuButton menu;

    private TelaHistoricoController telaHistoricoController;

    private IrpfFacade irpfFacade;

    public TelaInicialController() {
        irpfFacade = new IrpfFacade();
        telaHistoricoController = new TelaHistoricoController();
    }

    @FXML
    private void calcular() {
        BigDecimal valorTotal = irpfFacade.calcularIrpf(getContribuinteDTO());
        valorIrpf.setText(valorTotal.toString());
        cadastrar.setVisible(true);
    }

    @FXML
    private void cadastrar() {
        irpfFacade.salvarContribuinte(getContribuinteDTO());
    }

    private ContribuinteDTO getContribuinteDTO() {
        return ContribuinteDTO.ContribuinteDTOBuilder.aContribuinteDTO()
                .withContribuicaoOficial(new BigDecimal(contribuicaoOficial.getText()))
                .withIdade(Integer.valueOf(idade.getText()))
                .withNome(nomeCompleto.getText())
                .withDependentes(Integer.valueOf(numeroDependentes.getValue().toString()))
                .withRendimentoTotal(new BigDecimal(rendimentoTotal.getText()))
                .withValorIRPF(valorIrpf.getText() == null || valorIrpf.getText().equals("") ? null : new BigDecimal(valorIrpf.getText()))
                .build();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        numeroDependentes.getItems().addAll(0, 1, 2, 3, 4, 5);
        numeroDependentes.getSelectionModel().select(0);
        cadastrar.setVisible(false);
        MenuItem menuItem = new MenuItem("Historico de cadastros");
        menuItem.setOnAction(event -> {
            try {
                telaHistoricoController.start(new Stage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        menu.getItems().clear();
        menu.getItems().add(menuItem);
    }
}
