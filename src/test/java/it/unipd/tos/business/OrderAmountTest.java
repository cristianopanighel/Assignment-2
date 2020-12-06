package it.unipd.tos.business;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OrderAmountTest {

    private final OrderAmount calculator = new OrderAmount();

    @Test
    public void getOrderPriceBaseList_Test() throws TakeAwayBillException
    {
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem(MenuItem.Item.GELATI, "Coppa Nafta", 5.0D));
        items.add(new MenuItem(MenuItem.Item.BUDINI, "Biancaneve", 4.0D));
        items.add(new MenuItem(MenuItem.Item.BEVANDE, "Acqua", 1.5D));

        double res = calculator.getOrderPrice(items, new User("Panighel", "Cristiano", LocalDate.of(1999,10,06)));
        assertEquals(10.5D, res, 0D);
    }
}