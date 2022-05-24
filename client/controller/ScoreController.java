package client.controller;

import java.io.IOException;

import client.model.DAO.Player;
import client.model.DAO.PlayerDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ScoreController {

    @FXML 
    private AnchorPane mainRoot;

    @FXML
    private TableView<Player> table;

    @FXML
    private TableColumn<Player, String> cNickname;

    @FXML 
    private TableColumn<Player, Integer> cScore;

    @FXML
    public void initialize() {
        cNickname.setCellValueFactory(new PropertyValueFactory<Player, String>("Nickname"));
        cScore.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Score"));
        updateTable();
    }

    @FXML
    private void onBack() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../view/Menu.fxml"));
        mainRoot.getChildren().setAll(pane);
    }

    private void updateTable() {
        try {
            ObservableList<Player> players = PlayerDAO.getAll();
            table.setItems(players);
        } catch (Exception e) {
            table.setVisible(false);
        }
    }
}
