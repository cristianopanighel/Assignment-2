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
        if(items.size()>30) {
            throw new TakeAwayBillException("L'ordine ha più di 30 elementi");
        }
        double res = 0;
        for(MenuItem item : items)
        {
            res += item.getPrice();
        }
        if(SeiGelati(items)) {
            res -= (gelatoMenoCostoso(items) / 2);
        }
        if(gelatiBudiniSconto(items)) {
            double sconto10 = res / 10;
            res -= sconto10;
        }
        if(res<10) {
            res+=0.50;
        }
        return res;
    }

    boolean SeiGelati(List<MenuItem> items) {
        int c = 0;
        for(MenuItem item : items) {
            if(item.getItemType() == MenuItem.Item.GELATI) {
                c++;
            }
        }
        return c >= 6;
    }

    double gelatoMenoCostoso(List<MenuItem> items)
    {
        double prezzo = Double.MAX_VALUE;
        for(MenuItem item : items) {
            if(prezzo > item.getPrice() && item.getItemType() == MenuItem.Item.GELATI) {
                prezzo = item.getPrice();
            }
        }
        return prezzo;
    }

    boolean gelatiBudiniSconto(List<MenuItem> items) {
        double price = 0;
        for(MenuItem item : items) {
            if(item.getItemType() == MenuItem.Item.GELATI ||
                    item.getItemType() == MenuItem.Item.BUDINI) {
                price += item.getPrice();
            }
        }
        return price > 50;
    }
}
