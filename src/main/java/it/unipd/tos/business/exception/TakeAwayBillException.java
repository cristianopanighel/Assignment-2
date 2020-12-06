////////////////////////////////////////////////////////////////////
// CRISTIANO PANIGHEL 1201284
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business.exception;

public class TakeAwayBillException extends Throwable {

    private final String messageError;

    public TakeAwayBillException(String messageError) {
        this.messageError = messageError;
    }

    @Override
    public String getMessage() {
        return messageError;
    }
}
