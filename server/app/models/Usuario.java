package models;

import com.avaje.ebean.Model;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;
import play.data.validation.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario extends Model {

    private static final long serialVersionUID = 1L;

    @Id
    @Constraints.Min(100)
    private Long id;
    @Constraints.Required
    private String nome;
    @Constraints.Required
    private String matricula;
    @Constraints.Required
    private String telefone;
    @Constraints.Required
    private String email;
    @Constraints.Required
    private String senha;

    private Endereco endereco;

    private List<Carona> caronas;


  /*  @ManyToOne(cascade= CascadeType.ALL)
    private Carona carona;
*/
    public static Finder<Long, Usuario> find = new Finder<Long, Usuario>(
            Long.class, Usuario.class
    );

    public Usuario(String nome, String matricula, String telefone, String email, String senha, Endereco endereco) {
        this.nome = nome;
        this.matricula = matricula;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
        this.caronas = new ArrayList<Carona>();
    }

    public Usuario(){
        this.caronas = new ArrayList<Carona>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Carona> getCaronas() {
        return caronas;
    }

    public void setCaronas(List<Carona> caronas) {
        this.caronas = caronas;
    }


    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco(){
        return endereco;
    }

    public void setEndereco(Endereco endereco){
        this.endereco = endereco;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }

}
