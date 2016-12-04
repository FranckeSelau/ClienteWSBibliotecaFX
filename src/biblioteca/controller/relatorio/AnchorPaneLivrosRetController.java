package biblioteca.controller.relatorio;

import biblioteca.model.Livro;
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
import webService.LivrosRetClient;

public class AnchorPaneLivrosRetController implements Initializable {

    @FXML
    private TableView<Livro> tableViewLivro;
    @FXML
    private TableColumn<Livro, String> tableColumnLivroISBN;
    @FXML
    private TableColumn<Livro, String> tableColumnLivroNome;
    @FXML
    private TableColumn<Livro, String> tableColumnLivroAno;
    @FXML
    private TableColumn<Livro, String> tableColumnLivroRetiradas;
    @FXML
    private AnchorPane anchorPaneVisualizar;

    private List<Livro> listaLivros;
    private ObservableList<Livro> observableListLivros;
    LivrosRetClient livro = new LivrosRetClient();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTableViewLivrosRetiram();
    }

    public void carregarTableViewLivrosRetiram() {
        this.tableColumnLivroISBN.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        this.tableColumnLivroNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        this.tableColumnLivroAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
        this.tableColumnLivroRetiradas.setCellValueFactory(new PropertyValueFactory<>("retiradas"));

        listaLivros = livro.getLivrosRet(new GenericType<List<Livro>>(){});

        observableListLivros = FXCollections.observableArrayList(listaLivros);
        this.tableViewLivro.setItems(observableListLivros);
    }
}
