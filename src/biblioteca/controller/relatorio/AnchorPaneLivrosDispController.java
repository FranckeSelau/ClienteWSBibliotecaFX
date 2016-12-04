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
import javafx.scene.control.Label;
import javax.ws.rs.core.GenericType;
import webService.LivrosDispClient;

public class AnchorPaneLivrosDispController implements Initializable {

    @FXML
    private TableView<Livro> tableViewLivro;
    @FXML
    private TableColumn<Livro, String> tableColumnLivroISBN;
    @FXML
    private TableColumn<Livro, String> tableColumnLivroTitulo;
    @FXML
    private Label LabelLivroISBN;
    @FXML
    private Label LabelLivroTitulo;
    @FXML
    private Label LabelLivroAutor;
    @FXML
    private Label LabelLivroEditora;
    @FXML
    private Label LabelLivroAno;

    private List<Livro> listaLivros;
    private ObservableList<Livro> observableListLivros;
    LivrosDispClient livro = new LivrosDispClient();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTableViewLivros();
        this.tableViewLivro.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selecionarItensTableViewLivros(newValue));
    }

    public void carregarTableViewLivros() {
        this.tableColumnLivroISBN.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        this.tableColumnLivroTitulo.setCellValueFactory(new PropertyValueFactory<>("nome"));

        listaLivros = livro.getLivrosDisp(new GenericType<List<Livro>>(){});

        observableListLivros = FXCollections.observableArrayList(listaLivros);
        this.tableViewLivro.setItems(observableListLivros);
    }

    public void selecionarItensTableViewLivros(Livro livroSelecionado) {
        if (livroSelecionado != null) {
            LabelLivroISBN.setText(livroSelecionado.getIsbn());
            this.LabelLivroTitulo.setText(livroSelecionado.getNome());
            this.LabelLivroAutor.setText(livroSelecionado.getAutor());
            this.LabelLivroEditora.setText(livroSelecionado.getEditora());
            this.LabelLivroAno.setText(livroSelecionado.getAno());

        } else {
            LabelLivroISBN.setText("");
            this.LabelLivroTitulo.setText("");
            this.LabelLivroAutor.setText("");
            this.LabelLivroEditora.setText("");
            this.LabelLivroAno.setText("");
        }
    }

}
