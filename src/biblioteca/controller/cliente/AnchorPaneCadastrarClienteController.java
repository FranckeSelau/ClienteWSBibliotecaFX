package biblioteca.controller.cliente;

import biblioteca.model.Cliente;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import biblioteca.negocio.NegocioException;
import webService.ClienteClient;

public class AnchorPaneCadastrarClienteController implements Initializable {

    @FXML
    private TextField textFieldNomeCliente;
    @FXML
    private TextField textFieldTelefone;
    @FXML
    private Button btnCadastrarCliente;

    ClienteClient cliente = new ClienteClient();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void HandleBtnCadastrarCliente(ActionEvent event) throws IOException, NegocioException {

        if (!textFieldNomeCliente.getText().isEmpty() && !textFieldTelefone.getText().isEmpty()) {
            Cliente novo = new Cliente(textFieldNomeCliente.getText(), textFieldTelefone.getText());
            cliente.adicionarCliente(novo);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Confirmação de Cadastro");
            alert.setHeaderText("CONFIRMAÇÃO");
            alert.setContentText("O Cliente " + textFieldNomeCliente.getText() + " foi cadastrado com sucesso!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Campos de cadastramento não podem ficar em branco!");
            alert.show();
        }
    }
}
