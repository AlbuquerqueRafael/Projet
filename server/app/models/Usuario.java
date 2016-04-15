package models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class Usuario extends Model {


    private String nome, matricula, telefone, email, senha;
    private Endereco endereco;
    public List<Carona> caronasMotorista;
    public List<Carona> caronasPassageiro;
    public List<Solicitacao> solicitacoesEnviadas;
    public List<Solicitacao> solicitacoesRecebidas;


    public Usuario(String nome, String matricula, String telefone, String email, String senha, Endereco endereco) {
        this.nome = nome;
        this.matricula = matricula;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
        this.caronasMotorista = new ArrayList<Carona>();
        this.caronasPassageiro = new ArrayList<Carona>();
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

    public List<Carona> getCaronasMotorista() {
        return caronasMotorista;
    }

    public List<Carona> getCaronasPassageiro() {
        return caronasPassageiro;
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

}
