package biblioteca.controller.livro;

import biblioteca.model.Cliente;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import biblioteca.negocio.NegocioException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.ws.rs.core.GenericType;
import webService.LivroClient;

public class AnchorPaneAtualizarLivroController implements Initializable {

    @FXML
    private TableView<Livro> tableViewLivro;
    @FXML
    private TableColumn<Livro, String> tableColumnIsbnLivro;
    @FXML
    private TableColumn<Livro, String> tableColumnTituloLivro;
    @FXML
    private TextField textFieldTituloLivro;
    @FXML
    private TextField textFieldAutorLivro;
    @FXML
    private TextField textFieldEditoraLivro;
    @FXML
    private TextField textFieldAnoLivro;
    @FXML
    private Button btnAtualizarLivro;
    @FXML
    private Livro LivroAtualizar;

    private List<Livro> listaLivros;
    private ObservableList<Livro> observableListLivros;
    LivroClient livro = new LivroClient();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTableViewLivros();
        this.tableViewLivro.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> selecionarItensTableViewLivros(newValue));
    }

    public void carregarTableViewLivros() {
        this.tableColumnIsbnLivro.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        this.tableColumnTituloLivro.setCellValueFactory(new PropertyValueFactory<>("nome"));
        listaLivros = livro.getLivros(new GenericType<List<Livro>>(){});

        observableListLivros = FXCollections.observableArrayList(listaLivros);
        this.tableViewLivro.setItems(observableListLivros);
    }

    public void selecionarItensTableViewLivros(Livro liv) {
        //formatando data ANO
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        if (liv != null) {
            this.textFieldTituloLivro.setText(liv.getNome());
            this.textFieldAutorLivro.setText(liv.getAutor());
            this.textFieldEditoraLivro.setText(liv.getEditora());
            this.textFieldAnoLivro.setText(liv.getAno());
            this.setLivroSelecionado(liv);
        } else {
            this.textFieldTituloLivro.setText("");
            this.textFieldAutorLivro.setText("");
            this.textFieldEditoraLivro.setText("");
            this.textFieldAnoLivro.setText("");
        }
    }

    public void setLivroSelecionado(Livro LivSelecionado) {
        this.LivroAtualizar = LivSelecionado;
    }

    @FXML
    public void HandleBtnAtualizarLivro(ActionEvent event) throws IOException, ParseException {
       // System.out.println(LivroAtualizar.toString());
        if (LivroAtualizar != null) {
            if (!this.textFieldTituloLivro.getText().isEmpty() && !this.textFieldAutorLivro.getText().isEmpty() && !this.textFieldEditoraLivro.getText().isEmpty()) {
                if (!LivroAtualizar.getNome().equalsIgnoreCase(textFieldTituloLivro.getText())) {
                    this.LivroAtualizar.setNome(textFieldTituloLivro.getText());
                }
                if (!LivroAtualizar.getAutor().equalsIgnoreCase(textFieldAutorLivro.getText())) {
                    this.LivroAtualizar.setAutor(textFieldAutorLivro.getText());
                }
                if (!LivroAtualizar.getEditora().equalsIgnoreCase(textFieldEditoraLivro.getText())) {
                    this.LivroAtualizar.setEditora(textFieldEditoraLivro.getText());
                }
                if (!LivroAtualizar.getAno().equals(textFieldAnoLivro.getText())) {
                    this.LivroAtualizar.setAno(textFieldAnoLivro.getText());
                }
                String id = String.valueOf(LivroAtualizar.getId());//converte long em string
                livro.alterarLivro(LivroAtualizar, id);
                this.carregarTableViewLivros();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Atualização");
                alert.setHeaderText("Livro Atualizado");
                alert.setContentText("O Livro " + textFieldTituloLivro.getText() + " foi atualizado com sucesso!");
                alert.showAndWait();
                this.textFieldTituloLivro.setText("");
                this.textFieldAutorLivro.setText("");
                LivroAtualizar = null;
            } else {
                Alert alertVazio = new Alert(AlertType.ERROR);
                alertVazio.setHeaderText("ERRO!");
                alertVazio.setContentText("Não pode haver campos em branco!");
                alertVazio.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Escolha um Livro para atualizar!");
            alert.show();
        }
    }
}
