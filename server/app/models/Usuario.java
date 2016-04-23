package models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Lob;
import java.io.*;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

public class Usuario{


    private String nome;
    private String matricula;
    private String telefone;
    @Lob
    public File foto;
    private String email;
    private String senha;
    @JsonProperty
    private Endereco endereco;




    public Usuario(String nome, String matricula, String telefone, String email, String senha, Endereco endereco, File foto) {
        this.nome = nome;
        this.matricula = matricula;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
        this.foto = foto;
    }

    //necessário para mapeação JSON
    public Usuario(){
    }

    public Usuario(String email, String telefone){
        this.telefone = telefone;
        this.email = email;
    }

    public Usuario(String nome, Endereco endereco, String email){
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
    }

    public Usuario(String nome, String email, String telefone){
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public File getFoto(){
        return this.foto;
    }

    public void setFoto(File foto){

        this.foto = foto;
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

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Usuario)){
            return false;
        }

        Usuario usu = (Usuario) obj;

        return usu.getEmail().equals(this.getEmail());

    }

}
