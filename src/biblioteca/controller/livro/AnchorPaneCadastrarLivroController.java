package biblioteca.controller.livro;

import biblioteca.model.Livro;
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
import webService.LivroClient;

public class AnchorPaneCadastrarLivroController implements Initializable {

    @FXML
    private TextField textFieldIsbnLivro;
    @FXML
    private TextField textFieldNomeLivro;
    @FXML
    private TextField textFieldAutorLivro;
    @FXML
    private TextField textFieldEditoraLivro;
    @FXML
    private TextField textFieldAnoLivro;
    @FXML
    private Button btnCadastrarLivro;

    LivroClient livro = new LivroClient();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void HandleBtnCadastrarLivro(ActionEvent event) throws IOException, NegocioException {

        if (!textFieldNomeLivro.getText().isEmpty() && !textFieldIsbnLivro.getText().isEmpty() && !textFieldAutorLivro.getText().isEmpty() && !textFieldEditoraLivro.getText().isEmpty() && !textFieldAnoLivro.getText().isEmpty()) {
            Livro novo = null;
            novo = new Livro(textFieldIsbnLivro.getText(), textFieldNomeLivro.getText(), textFieldAutorLivro.getText(), textFieldEditoraLivro.getText(), textFieldAnoLivro.getText());
            livro.adicionarLivro(novo);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Confirmação de Cadastro");
            alert.setHeaderText("CONFIRMAÇÃO");
            alert.setContentText("O Livro " + textFieldNomeLivro.getText() + " foi cadastrado com sucesso!");
            alert.showAndWait();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Campos de cadastramento não podem ficar em branco!");
            alert.show();
        }
    }

}
