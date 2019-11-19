package com.irpf.presentation.controller;

import com.irpf.business.IrpfFacade;
import com.irpf.presentation.model.CadastroModel;
import com.irpf.repository.dto.ContribuinteDTO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
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

    private CadastroModel cadastroModel;
    private IrpfFacade irpfFacade;

    public TelaInicialController() {
        irpfFacade = new IrpfFacade();
        cadastroModel = new CadastroModel();
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
        ContribuinteDTO contribuinteDTO = new ContribuinteDTO();
        contribuinteDTO.setContribuicaoOficial(new BigDecimal(contribuicaoOficial.getText()));
        contribuinteDTO.setIdade(Integer.valueOf(idade.getText()));
        contribuinteDTO.setNome(nomeCompleto.getText());
        contribuinteDTO.setDependentes(Integer.valueOf(numeroDependentes.getValue().toString()));
        contribuinteDTO.setRendimentoTotal(new BigDecimal(rendimentoTotal.getText()));
        return contribuinteDTO;
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


    public void start(Stage stage) throws IOException {
        File f = new File("src/com/irpf/presentation/view/TelaHistorico.fxml");
        Parent fxmlParent = FXMLLoader.load(f.toURI().toURL());

        stage.setScene(new Scene(fxmlParent, 400, 350));
        stage.show();
    }
}
