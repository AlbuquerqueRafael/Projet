package models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import play.data.validation.Constraints;
import play.db.ebean.*;
import com.avaje.ebean.Model;
import java.io.*;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario extends Model{

    private static final long serialVersionUID = 1L;

    @Id
    protected Long id;

    private String nome;

    private String matricula;

    private String telefone;

    private String email;

    private String senha;

    @ManyToOne(cascade = CascadeType.ALL)
    private Endereco endereco;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Notificacao> novasNotificacoes;

    @ManyToOne
    private Carona carona;


    public Usuario(String nome, String matricula, String telefone, String email, String senha, Endereco endereco) {
        this.nome = nome;
        this.matricula = matricula;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
        this.novasNotificacoes = new ArrayList<Notificacao>();
    }

    //necessário para mapeação JSON
    public Usuario(){
        this.novasNotificacoes = new ArrayList<Notificacao>();
    }

    public Usuario(String email, String telefone){
        this.telefone = telefone;
        this.email = email;
        this.novasNotificacoes = new ArrayList<Notificacao>();
    }

    public Usuario(String nome, Endereco endereco, String email){
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.novasNotificacoes = new ArrayList<Notificacao>();
    }

    public Usuario(String nome, String email, String telefone){
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.novasNotificacoes = new ArrayList<Notificacao>();
    }


    public static Model.Finder<Long,Usuario> find = new Model.Finder<Long,Usuario>(Long.class, Usuario.class);


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

    public void adicionaNotificacao(Notificacao message){
        System.out.println(this.novasNotificacoes);
        this.novasNotificacoes.add(message);
        System.out.println("Notificou");
    }

    public void removeNotificacao(String message){
        for (Notificacao n: novasNotificacoes){
            if (n.getMessage().equals(message)){
                this.novasNotificacoes.remove(n);
                break;
            }
        }
    }

    public void setNovasNotificacoes(List<Notificacao> lista){
        this.novasNotificacoes = lista;
    }

    public List<Notificacao> getNovasNotificacoes(){
        return novasNotificacoes;
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Usuario)){
            return false;
        }

        Usuario usu = (Usuario) obj;

        return usu.getEmail().equals(this.getEmail());

    }

}
