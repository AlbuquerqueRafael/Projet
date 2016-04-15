package models;

/**
 * Created by rafael on 10/04/16.
 */

public class Endereco {

    private String rua;
    private String bairro;


    public Endereco(String rua, String bairro){
        this.rua = rua;
        this.bairro = bairro;
    }

    public Endereco(){

    }

    public String getRua(){
        return rua;
    }

    public void setRua(String rua){
        this.rua = rua;
    }

    public String getBairro(){
        return rua;
    }

    public void setBairro(String bairro){
        this.bairro = bairro;
    }
}
