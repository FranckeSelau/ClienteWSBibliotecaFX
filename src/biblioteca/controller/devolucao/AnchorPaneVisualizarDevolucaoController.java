package biblioteca.controller.devolucao;

import biblioteca.model.Devolucao;
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
import webService.DevolucaoClient;

public class AnchorPaneVisualizarDevolucaoController implements Initializable {

    @FXML
    private TableView<Devolucao> tableViewDevolucao;
    @FXML
    private TableColumn<Devolucao, String> tableColumnDevolucaoTitulo;
    @FXML
    private TableColumn<Devolucao, String> tableColumnDevolucaoCliente;
    @FXML
    private Label LabelDevolucaoID;
    @FXML
    private Label LabelDevolucaoTitulo;
    @FXML
    private Label LabelDevolucaoCliente;
    @FXML
    private Label LabelDevolucaoRet;
    @FXML
    private Label LabelDevolucaoEntrega;
    @FXML
    private Label LabelDevolucaoDevolvido;

    private List<Devolucao> listaDevolucao;
    private ObservableList<Devolucao> observableListDevolucao;
    DevolucaoClient devolucao = new DevolucaoClient();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTableViewDevolucao();
        this.tableViewDevolucao.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selecionarItensTableViewDevolucao(newValue));
    }

    public void carregarTableViewDevolucao() {       
        
        //call back para mostrar a coluna título do livro
        tableColumnDevolucaoTitulo.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Devolucao, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Devolucao, String> cell) {
                final Devolucao liv = cell.getValue();
                final SimpleObjectProperty<String> simpleObject = new SimpleObjectProperty(liv.getLivro().getNome());
                return simpleObject;
            }

        });

        //call back para mostrar a coluna nome do cliente
        tableColumnDevolucaoCliente.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Devolucao, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Devolucao, String> cell) {
                final Devolucao cli = cell.getValue();
                final SimpleObjectProperty<String> simpleObject = new SimpleObjectProperty(cli.getCliente().getNome());
                return simpleObject;
            }

        });

        listaDevolucao = devolucao.getDevolucao(new GenericType<List<Devolucao>>() {});

        observableListDevolucao = FXCollections.observableArrayList(listaDevolucao);
        this.tableViewDevolucao.setItems(observableListDevolucao);
    }

    public void selecionarItensTableViewDevolucao(Devolucao devolucaoSelecionada) {
        if (devolucaoSelecionada != null) {
            LabelDevolucaoID.setText(String.valueOf(devolucaoSelecionada.getId()));
            this.LabelDevolucaoTitulo.setText(devolucaoSelecionada.getLivro().getNome());
            this.LabelDevolucaoCliente.setText(devolucaoSelecionada.getCliente().getNome());
            this.LabelDevolucaoRet.setText(devolucaoSelecionada.getRetiradaFormatada());
            this.LabelDevolucaoEntrega.setText(devolucaoSelecionada.getEntregaFormatada());
            this.LabelDevolucaoDevolvido.setText(devolucaoSelecionada.getDevolvidoFormatada());

        } else {
            LabelDevolucaoID.setText("");
            this.LabelDevolucaoTitulo.setText("");
            this.LabelDevolucaoCliente.setText("");
            this.LabelDevolucaoRet.setText("");
            this.LabelDevolucaoEntrega.setText("");
            this.LabelDevolucaoDevolvido.setText("");
        }
    }
}
