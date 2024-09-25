package school.sptech.projeto_06_service.exception;

public class EndidadeNaoEncontradaException extends RuntimeException{

    public EndidadeNaoEncontradaException() {
        super();
    }

    public EndidadeNaoEncontradaException(String message) {
        super(String.format("%s não encontrado(a)", message));
    }
}
