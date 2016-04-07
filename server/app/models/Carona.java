package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafael on 27/03/16.
 */

public class Carona {
    @Id
    @Constraints.Min(100)
    private Long id;


    private int vagas_disponiveis;

    @OneToMany(cascade= CascadeType.ALL)
    @JsonIgnore
    private List<Usuario> listaPassageiros;

    private Motorista motorista;

    public Carona (Motorista motorista){
      //  this.horario = horario;
        this.motorista = motorista;

        // cria lista de passageiros zeradas
        listaPassageiros = new ArrayList<Usuario>();

        //inicialmente as vagas disponiveis recebem o num de vagas do carro do motorista
        vagas_disponiveis = Integer.parseInt(motorista.getVagas());
    }

 //   public Horario getHorario(){
   //     return horario;
    //}

    public int getVagas_disponiveis() {
        return vagas_disponiveis;
    }

    public List<Usuario> getListaPassageiros() {
        return listaPassageiros;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public boolean cadastraNovoPassageiro (Usuario usr){
        if (vagas_disponiveis > 0){
            listaPassageiros.add(usr);
            vagas_disponiveis--;
            return true;
        }
        return false;
    }

    public void removePassageiro (Usuario usr){
        if (listaPassageiros.remove(usr))
            vagas_disponiveis++;
    }
}
