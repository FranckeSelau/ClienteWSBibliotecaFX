package biblioteca.controller.cliente;

import biblioteca.model.Cliente;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.ws.rs.core.GenericType;
import webService.ClienteClient;

public class AnchorPaneVisualizarClienteController implements Initializable {

    @FXML
    private TableView<Cliente> tableViewCliente;
    @FXML
    private TableColumn<Cliente, String> tableColumnClienteMatricula;
    @FXML
    private TableColumn<Cliente, String> tableColumnClienteNome;
    @FXML
    private TableColumn<Cliente, String> tableColumnClienteTelefone;
    @FXML
    private AnchorPane anchorPaneVisualizar;

    private List<Cliente> listaClientes;
    private ObservableList<Cliente> observableListClientes;
    ClienteClient cliente = new ClienteClient();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTableViewClientes();
    }

    public void carregarTableViewClientes() {
        this.tableColumnClienteMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        this.tableColumnClienteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        this.tableColumnClienteTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        listaClientes = cliente.getClientes(new GenericType<List<Cliente>>(){});

        observableListClientes = FXCollections.observableArrayList(listaClientes);
        this.tableViewCliente.setItems(observableListClientes);
    }
}
