package exception;

/**
 * Created by rafael on 22/05/16.
 */
public class SolicitacaoIdInvalidoException extends NullPointerException {
    public SolicitacaoIdInvalidoException() {
        super("Id Inválido");
    }
}
