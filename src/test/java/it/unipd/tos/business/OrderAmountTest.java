package it.unipd.tos.business;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class OrderAmountTest {

    private final OrderAmount calculator = new OrderAmount();

    @Test(expected = TakeAwayBillException.class)
    public void più30Elementi() throws TakeAwayBillException
    {
        MenuItem item = new MenuItem(MenuItem.Item.GELATI, "Limone", 5.0D);
        Stream<MenuItem> gelati = Stream.generate(() -> item);
        List<MenuItem> items = gelati.limit(31).collect(Collectors.toList());

        double res = calculator.getOrderPrice(items,new User("Panighel", "Cristiano", LocalDate.of(1999,10,06)));
    }

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

    @Test
    public void getOrderPriceBaseList6Gelati_Test() throws TakeAwayBillException
    {
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem(MenuItem.Item.GELATI, "Coppa Nafta", 5.5D));
        items.add(new MenuItem(MenuItem.Item.GELATI, "Fragola", 4.0D));
        items.add(new MenuItem(MenuItem.Item.GELATI, "Banana Split", 8.5D));
        items.add(new MenuItem(MenuItem.Item.GELATI, "Limone", 8.0D));
        items.add(new MenuItem(MenuItem.Item.GELATI, "Pompelmo", 5.0D));
        items.add(new MenuItem(MenuItem.Item.GELATI, "Nocciola", 6.0D));

        double res = calculator.getOrderPrice(items, new User("Panighel", "Cristiano", LocalDate.of(1999,10,06)));
        assertEquals(35.0D, res, 0D);
    }

    @Test
    public void getOrderPriceBaseList10PerCento_Test() throws TakeAwayBillException
    {
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem(MenuItem.Item.GELATI, "Banana Split", 10.0D));
        items.add(new MenuItem(MenuItem.Item.GELATI, "Coppa Nafta", 10.0D));
        items.add(new MenuItem(MenuItem.Item.GELATI, "Banana Split", 10.0D));
        items.add(new MenuItem(MenuItem.Item.BUDINI, "BiancaNeve", 10.0D));
        items.add(new MenuItem(MenuItem.Item.BUDINI, "BiancaNeve", 10.0D));
        items.add(new MenuItem(MenuItem.Item.BUDINI, "BiancaNeve", 10.0D));

        double result = calculator.getOrderPrice(items, new User("Panighel", "Cristino", LocalDate.of(1999,10,06)));
        assertEquals(54.0D, result, 0D);
    }

    @Test
    public void SeiGelati_Test()
    {
        MenuItem item = new MenuItem(MenuItem.Item.GELATI, "Coppa Nafta", 5.0D);
        Stream<MenuItem> gelati = Stream.generate(() -> item);
        List<MenuItem> items = gelati.limit(6).collect(Collectors.toList());

        assertTrue(calculator.SeiGelati(items));
        assertFalse(calculator.SeiGelati(items.subList(0,5)));
    }

    @Test
    public void gelatoMenoCostoso_Test()
    {
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem(MenuItem.Item.GELATI, "Coppa Nafta", 5.0D));
        items.add(new MenuItem(MenuItem.Item.GELATI, "Fragola", 4.0D));
        items.add(new MenuItem(MenuItem.Item.GELATI, "Banana Split", 8.5D));
        items.add(new MenuItem(MenuItem.Item.GELATI, "Limone", 8.0D));

        assertEquals(4.0D, calculator.gelatoMenoCostoso(items), 0);
    }

    @Test
    public void gelatiBudiniSconto_Test()
    {
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem(MenuItem.Item.GELATI, "Coppa Nafta", 5.0D));
        items.add(new MenuItem(MenuItem.Item.GELATI, "Fragola", 4.0D));
        items.add(new MenuItem(MenuItem.Item.GELATI, "Banana Split", 8.5D));
        items.add(new MenuItem(MenuItem.Item.GELATI, "Limone", 8.0D));
        items.add(new MenuItem(MenuItem.Item.BUDINI, "Biancaneve", 9.5D));
        items.add(new MenuItem(MenuItem.Item.BUDINI, "Pinguino", 10.0D));
        items.add(new MenuItem(MenuItem.Item.BUDINI, "Vanilla", 9.0D));

        assertTrue(calculator.gelatiBudiniSconto(items));
        assertFalse(calculator.gelatiBudiniSconto(items.subList(0,6)));

        List<MenuItem> ordine = new ArrayList<>();
        items.add(new MenuItem(MenuItem.Item.GELATI, "Coppa Nafta", 5.0D));
        items.add(new MenuItem(MenuItem.Item.GELATI, "Fragola", 4.0D));
        items.add(new MenuItem(MenuItem.Item.GELATI, "Banana Split", 8.5D));
        items.add(new MenuItem(MenuItem.Item.BUDINI, "Biancaneve", 9.5D));
        items.add(new MenuItem(MenuItem.Item.BUDINI, "Pinguino", 10.0D));
        items.add(new MenuItem(MenuItem.Item.BUDINI, "Vanilla", 9.0D));
        items.add(new MenuItem(MenuItem.Item.BEVANDE, "Coca Cola", 8.0D));

        assertFalse(calculator.gelatiBudiniSconto(ordine));

        List<MenuItem> order = new ArrayList<>();
        items.add(new MenuItem(MenuItem.Item.GELATI, "Coppa Nafta", 50.0D));
        items.add(new MenuItem(MenuItem.Item.BEVANDE, "Fanta", 1.0D));
        assertFalse(calculator.gelatiBudiniSconto(order));
    }
}