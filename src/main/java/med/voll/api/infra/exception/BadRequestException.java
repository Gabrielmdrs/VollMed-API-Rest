package med.voll.api.infra.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String menssage){
        super(menssage);
    }
}
