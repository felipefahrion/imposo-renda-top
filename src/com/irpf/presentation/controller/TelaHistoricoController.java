package com.irpf.presentation.controller;

import com.irpf.business.IrpfFacade;
import com.irpf.repository.dto.ContribuinteDTO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TelaHistoricoController implements Initializable {

    @FXML
    private MenuButton menu;

    @FXML
    private TextField nomeParcial;

    @FXML
    private ListView listaContribuintes;

    private IrpfFacade irpfFacade;

    public TelaHistoricoController() {
        irpfFacade = new IrpfFacade();
    }

    @FXML
    private void buscar() {
        listaContribuintes.getItems().clear();
        List<ContribuinteDTO> contribuinteDTOS = irpfFacade.buscarContribuinte(nomeParcial.getText());
        listaContribuintes.getItems().addAll(contribuinteDTOS);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MenuItem menuItem = new MenuItem("Cadastro de IRPF");
        menu.getItems().clear();
        menu.getItems().add(menuItem);
        List<ContribuinteDTO> contribuinteDTOS = irpfFacade.buscarContribuintes();
        listaContribuintes.getItems().addAll(contribuinteDTOS);
    }

    public void start(Stage stage) throws IOException {
        File f = new File("src/com/irpf/presentation/view/TelaHistorico.fxml");
        Parent fxmlParent = FXMLLoader.load(f.toURI().toURL());

        stage.setScene(new Scene(fxmlParent, 400, 350));
        stage.show();
    }
}
