package biblioteca.controller.retirada;

import biblioteca.model.Retiradas;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;
import javafx.util.Callback;
import javax.ws.rs.core.GenericType;
import webService.RetiradasClient;

public class AnchorPaneVisualizarRetiradaController implements Initializable {

    @FXML
    private TableView<Retiradas> tableViewRetiradas;
    @FXML
    private TableColumn<Retiradas, String> tableColumnRetiradasTitulo;
    @FXML
    private TableColumn<Retiradas, String> tableColumnRetiradasCliente;
    @FXML
    private Label LabelRetiradasID;
    @FXML
    private Label LabelRetiradasTitulo;
    @FXML
    private Label LabelRetiradasCliente;
    @FXML
    private Label LabelRetiradasRet;
    @FXML
    private Label LabelRetiradasEntrega;

    private List<Retiradas> listaRetiradas;
    private ObservableList<Retiradas> observableListRetiradas;
    RetiradasClient retirada = new RetiradasClient();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTableViewRetiradas();
        this.tableViewRetiradas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selecionarItensTableViewRetiradas(newValue));
    }

    public void carregarTableViewRetiradas() {       
        
        //call back para mostrar a coluna título do livro
        tableColumnRetiradasTitulo.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Retiradas, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Retiradas, String> cell) {
                final Retiradas liv = cell.getValue();
                final SimpleObjectProperty<String> simpleObject = new SimpleObjectProperty(liv.getLivro().getNome());
                return simpleObject;
            }

        });

        //call back para mostrar a coluna nome do cliente
        tableColumnRetiradasCliente.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Retiradas, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Retiradas, String> cell) {
                final Retiradas cli = cell.getValue();
                final SimpleObjectProperty<String> simpleObject = new SimpleObjectProperty(cli.getCliente().getNome());
                return simpleObject;
            }

        });

        listaRetiradas = retirada.getRetiradas(new GenericType<List<Retiradas>>() {});

        observableListRetiradas = FXCollections.observableArrayList(listaRetiradas);
        this.tableViewRetiradas.setItems(observableListRetiradas);
    }

    public void selecionarItensTableViewRetiradas(Retiradas retiradaSelecionado) {
        if (retiradaSelecionado != null) {
            LabelRetiradasID.setText(String.valueOf(retiradaSelecionado.getId()));
            this.LabelRetiradasTitulo.setText(retiradaSelecionado.getLivro().getNome());
            this.LabelRetiradasCliente.setText(retiradaSelecionado.getCliente().getNome());
            this.LabelRetiradasRet.setText(retiradaSelecionado.getRetiradaFormatada());
            this.LabelRetiradasEntrega.setText(retiradaSelecionado.getEntregaFormatada());

        } else {
            LabelRetiradasID.setText("");
            this.LabelRetiradasTitulo.setText("");
            this.LabelRetiradasCliente.setText("");
            this.LabelRetiradasRet.setText("");
            this.LabelRetiradasEntrega.setText("");
        }
    }
}
