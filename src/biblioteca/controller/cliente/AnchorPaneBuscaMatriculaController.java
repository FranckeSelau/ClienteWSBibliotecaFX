package biblioteca.controller.cliente;

import biblioteca.model.Cliente;
import java.io.IOException;
import java.net.URL;
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
import java.util.ArrayList;
import webService.ClienteClient;

public class AnchorPaneBuscaMatriculaController implements Initializable {

    @FXML
    private TextField textFieldBuscaMatricula;
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

    private ObservableList<Cliente> observableListClientes;
    ClienteClient cliente = new ClienteClient();
    private ArrayList listaclientes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }

    public void carregarTableViewClientes(String matricula) {

        Cliente cli = cliente.getCliente(Cliente.class, matricula);
        if (cli != null) {
            ArrayList listaClientesMatricula = new ArrayList<>();
            listaClientesMatricula.add(cli);
            this.listaclientes = listaClientesMatricula;
        }
        if (!listaclientes.isEmpty()) {
            this.tableColumnMatriculaCliente.setCellValueFactory(new PropertyValueFactory<>("matricula"));
            this.tableColumnNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nome"));
            this.tableColumnTelefoneCliente.setCellValueFactory(new PropertyValueFactory<>("telefone"));
            
            observableListClientes = FXCollections.observableArrayList(listaclientes);
            this.tableViewCliente.setItems(observableListClientes);
        } else {
            this.tableViewCliente.setItems(null);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Resultado da Busca");
            alert.setHeaderText("");
            alert.setContentText("Nenhum cliente encontrado com a matrícula " + matricula + " !");
            alert.showAndWait();
        }
    }

    @FXML
    public void HandlebtnPesquisar(ActionEvent event) throws IOException {
        if (!textFieldBuscaMatricula.getText().isEmpty()) {
            String codigo;            
                codigo = textFieldBuscaMatricula.getText();
                this.carregarTableViewClientes(codigo);           
        } else {
            this.tableViewCliente.setItems(null);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERRO!");
            alert.setHeaderText("");
            alert.setContentText("Informe uma matrícula para busca!");
            alert.showAndWait();
        }
    }
}
