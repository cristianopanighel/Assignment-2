////////////////////////////////////////////////////////////////////
// CRISTIANO PANIGHEL 1201284
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

import java.time.LocalTime;
import java.util.List;

public interface TakeAwayBill {
    double getOrderPrice(List<MenuItem> itemsOrdered, User utente, LocalTime time) throws
            TakeAwayBillException;
}
