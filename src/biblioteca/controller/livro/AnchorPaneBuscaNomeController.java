package biblioteca.controller.livro;

import biblioteca.model.Livro;
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
import webService.LivroNomeClient;

public class AnchorPaneBuscaNomeController implements Initializable {

    @FXML
    private TextField textFieldBuscaNome;
    @FXML
    private Button btnBusca;
    @FXML
    private TableView<Livro> tableViewLivro;
    @FXML
    private TableColumn<Livro, String> tableColumnIsbnLivro;
    @FXML
    private TableColumn<Livro, String> tableColumnTituloLivro;
    @FXML
    private TableColumn<Livro, String> tableColumnAutorLivro;
    @FXML
    private TableColumn<Livro, String> tableColumnEditoraLivro;
    @FXML
    private TableColumn<Livro, String> tableColumnAnoLivro;

    private List<Livro> listaLivros;
    private ObservableList<Livro> observableListLivros;
    private LivroNomeClient livro;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        livro = new LivroNomeClient();
    }

    public void carregarTableViewLivros(String nome) {

        listaLivros = livro.getLivroNome(new GenericType<List<Livro>>(){}, nome);
        if (!listaLivros.isEmpty()) {
            this.tableColumnIsbnLivro.setCellValueFactory(new PropertyValueFactory<>("isbn"));
            this.tableColumnTituloLivro.setCellValueFactory(new PropertyValueFactory<>("nome"));
            this.tableColumnAutorLivro.setCellValueFactory(new PropertyValueFactory<>("autor"));
            this.tableColumnEditoraLivro.setCellValueFactory(new PropertyValueFactory<>("editora"));
            this.tableColumnAnoLivro.setCellValueFactory(new PropertyValueFactory<>("ano"));
            
            observableListLivros = FXCollections.observableArrayList(listaLivros);
            tableViewLivro.setItems(observableListLivros);
            
        } else {
            tableViewLivro.setItems(null);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Resultado da Busca");
            alert.setHeaderText("");
            alert.setContentText("Nenhum Livro foi encontrado com o Título " + nome + " !");
            alert.showAndWait();
        }
    }

    @FXML
    public void HandlebtnPesquisar(ActionEvent event) throws IOException {
        if (!textFieldBuscaNome.getText().isEmpty()) {
            this.carregarTableViewLivros(textFieldBuscaNome.getText());
        } else {
            this.tableViewLivro.setItems(null);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Falha na Busca por Título");
            alert.setContentText("ERRO! Você precisa informar um título para busca!");
            alert.show();
        }
    }
}
