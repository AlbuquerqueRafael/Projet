package models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Usuario{


    private String nome, matricula, telefone, email, senha;
    @JsonProperty
    private Endereco endereco;
    @JsonBackReference
    private List<Carona> caronasMotorista;
    private List<Carona> caronasPassageiro;
    private List<Solicitacao> solicitacoesEnviadas;
    private List<Solicitacao> solicitacoesRecebidas;


    public Usuario(String nome, String matricula, String telefone, String email, String senha, Endereco endereco) {
        this.nome = nome;
        this.matricula = matricula;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
        this.caronasMotorista = new ArrayList<Carona>();
        this.caronasPassageiro = new ArrayList<Carona>();
        this.solicitacoesEnviadas = new ArrayList<Solicitacao>();
        this.solicitacoesRecebidas = new ArrayList<Solicitacao>();
    }

    public Usuario(){
        this.caronasMotorista = new ArrayList<Carona>();
        this.caronasPassageiro = new ArrayList<Carona>();
        this.solicitacoesEnviadas = new ArrayList<Solicitacao>();
        this.solicitacoesRecebidas = new ArrayList<Solicitacao>();
    }



    public List<Solicitacao> getSolicitacoesRecebidas(){
        return solicitacoesRecebidas;
    }

    public List<Solicitacao> getSolicitacoesEnviadas(){
        return solicitacoesEnviadas;
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

    public void setCaronasMotorista(Carona carona){
        this.caronasMotorista.add(carona);
    }

    public List<Carona> getCaronasMotorista() {
        return caronasMotorista;
    }

    public List<Carona> getCaronasPassageiro() {
        return caronasPassageiro;
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
