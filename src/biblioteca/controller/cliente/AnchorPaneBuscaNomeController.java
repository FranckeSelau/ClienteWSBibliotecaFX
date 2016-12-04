package biblioteca.controller.cliente;

import biblioteca.model.Cliente;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.ws.rs.core.GenericType;
import webService.ClienteNomeClient;

public class AnchorPaneBuscaNomeController implements Initializable {

    @FXML
    private TextField textFieldBuscaNome;
    @FXML
    private Button btnBusca;
    @FXML
    private TableView<Cliente> tableViewCliente;
    @FXML
    private TableColumn<Cliente, String> tableColumnMatriculaCliente;
    @FXML
    private TableColumn<Cliente, String> tableColumnNomeCliente;
    @FXML
    private TableColumn<Cliente, String> tableColumnTelefoneCliente;

    private List<Cliente> listaClientes;
    private ObservableList<Cliente> observableListClientes;
    ClienteNomeClient cliente = new ClienteNomeClient();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }

    public void carregarTableViewClientes(String nome) {

        listaClientes = cliente.getClienteNome(new GenericType<List<Cliente>>(){}, nome);
        if (!listaClientes.isEmpty()) {
            this.tableColumnMatriculaCliente.setCellValueFactory(new PropertyValueFactory<>("matricula"));
            this.tableColumnNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nome"));
            this.tableColumnTelefoneCliente.setCellValueFactory(new PropertyValueFactory<>("telefone"));

            observableListClientes = FXCollections.observableArrayList(listaClientes);
            this.tableViewCliente.setItems(observableListClientes);
        } else {
            this.tableViewCliente.setItems(null);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Resultado da Busca");
            alert.setHeaderText("");
            alert.setContentText("Nenhum Cliente foi encontrado com o nome " + nome + " !");
            alert.showAndWait();
        }
    }
    
    @FXML
    public void HandlebtnPesquisar(ActionEvent event) throws IOException {
        if (!textFieldBuscaNome.getText().isEmpty()) {
            this.carregarTableViewClientes(textFieldBuscaNome.getText());
        } else {
            this.tableViewCliente.setItems(null);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Falha na Busca por Nome");
            alert.setContentText("ERRO! Você precisa informar um nome para busca!");
            alert.show();
        }
    }
}
