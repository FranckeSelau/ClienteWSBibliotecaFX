package biblioteca.model;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Francke
 */
@XmlRootElement // elemento xml para  consumir web service
public class Retiradas implements Serializable {  
    
    private Long id;
    private Livro livro;
    private Cliente cliente;  
    private Date dataRetirada;
    private Date dataDevolucao;

    public Retiradas() {

    }

    public Retiradas(Cliente cliente, Livro livro, Date dataRetirada, Date dataDevolucao) {
        this.cliente = cliente;
        this.livro = livro;
        this.dataRetirada = dataRetirada;
        this.dataDevolucao = dataDevolucao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(Date dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
    
    public String getRetiradaFormatada() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(dataRetirada);
    }
    
    public String getEntregaFormatada() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(dataDevolucao);
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Retiradas)) {
            return false;
        }
        Retiradas other = (Retiradas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Retiradas[ id=" + id + " ]";
    }
    
}
