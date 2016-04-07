package models;

import com.avaje.ebean.Model;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;
import play.data.validation.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", length = 1, discriminatorType = DiscriminatorType.STRING)
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
    private String bairro;
    @Constraints.Required
    private String email;
    @Constraints.Required
    private String rua;
    @Constraints.Required
    private String senha;


    @OneToMany(mappedBy = "usuario",cascade= CascadeType.ALL)
    @JsonIgnore
    private List<Horario> horarios;


    public static Finder<Long, Usuario> find = new Finder<Long, Usuario>(
            Long.class, Usuario.class
    );

    public Usuario(String nome, String matricula, String telefone, String bairro, String email, String rua, String senha) {
        this.nome = nome;
        this.matricula = matricula;
        this.telefone = telefone;
        this.bairro = bairro;
        this.email = email;
        this.rua = rua;
        this.senha = senha;
        this.horarios = new ArrayList<Horario>();
    }

    public Usuario(){

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

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
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

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios.addAll(horarios);
    }

    public void setHorario(Horario horario) {
        this.horarios.add(horario);
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }

}
