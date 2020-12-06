////////////////////////////////////////////////////////////////////
// CRISTIANO PANIGHEL 1201284
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

import java.util.List;

public class OrderAmount implements TakeAwayBill{
    @Override
    public double getOrderPrice(List<MenuItem> items, User utente) throws TakeAwayBillException
    {
        double res = 0;
        for(MenuItem item : items)
        {
            res += item.getPrice();
        }
        return res;
    }
}
