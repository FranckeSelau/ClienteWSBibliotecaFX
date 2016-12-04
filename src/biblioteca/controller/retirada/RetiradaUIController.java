package biblioteca.controller.retirada;

import biblioteca.Biblioteca;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RetiradaUIController implements Initializable {

    @FXML
    private Button BtnVoltarPrincipal;
    @FXML
    private Button MenuItemListaRetirada;
    @FXML
    private AnchorPane AnchorPaneRetirada;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(Biblioteca.class.getResource("/biblioteca/view/retirada/AnchorPaneFundoRetirada.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(RetiradaUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AnchorPaneRetirada.getChildren().setAll(root);
    }

    @FXML
    public void HandleBtnVoltar(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/biblioteca/view/MainUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(RetiradaUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void HandleMenuItemVisualizar(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Biblioteca.class.getResource("/biblioteca/view/retirada/AnchorPaneVisualizarRetirada.fxml"));
        AnchorPaneRetirada.getChildren().setAll(root);
    }
}
