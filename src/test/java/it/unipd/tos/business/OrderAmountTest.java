package it.unipd.tos.business;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
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

        double res = calculator.getOrderPrice(items,new User("Panighel", "Cristiano", LocalDate.of(1999,10,06)), LocalTime.of(12,30,0));
    }

    @Test
    public void getOrderPriceBaseList_Test() throws TakeAwayBillException
    {
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem(MenuItem.Item.GELATI, "Coppa Nafta", 5.0D));
        items.add(new MenuItem(MenuItem.Item.BUDINI, "Biancaneve", 4.0D));
        items.add(new MenuItem(MenuItem.Item.BEVANDE, "Acqua", 1.5D));

        double res = calculator.getOrderPrice(items,new User("Panighel", "Cristiano", LocalDate.of(1999,10,06)), LocalTime.of(12,30,0));        assertEquals(10.5D, res, 0D);
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

        double res = calculator.getOrderPrice(items, new User("Panighel", "Cristiano", LocalDate.of(1999,10,06)), LocalTime.of(12,30,00));
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

        double result = calculator.getOrderPrice(items, new User("Panighel", "Cristiano", LocalDate.of(1999,10,06)),LocalTime.of(12,30,00));
        assertEquals(54.0D, result, 0D);
    }

    @Test
    public void getOrderPriceBaseListCommissione_Test() throws TakeAwayBillException
    {
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem(MenuItem.Item.GELATI, "Coppa Nafta", 5.0D));

        assertEquals(5.5D, calculator.getOrderPrice(items,new User("Panighel", "Cristiano", LocalDate.of(1999,10,06)), LocalTime.of(12,30,00)), 0);

        items.add(new MenuItem(MenuItem.Item.BUDINI, "Biancaneve", 4.0D));
        items.add(new MenuItem(MenuItem.Item.BEVANDE, "Acqua", 1.5D));

        assertEquals(10.5D, calculator.getOrderPrice(items, new User("Panighel", "Cristiano", LocalDate.of(1999,10,06)), LocalTime.of(12,30,00)), 0);
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
    @Test
    public void getOrderPriceRegalo_Test() throws TakeAwayBillException
    {
        OrderAmount ordine = new OrderAmount();
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem(MenuItem.Item.GELATI, "Banana Split", 5.0D));
        List<User> utenti = Arrays.asList(
                new User("Panighel", "Cristiano", LocalDate.now().minusYears(4)),
                new User("Rossi", "Mario", LocalDate.now().minusYears(8)),
                new User("Prandello", "Fabio", LocalDate.now().minusYears(12)),
                new User("Tedo", "Paolo", LocalDate.now().minusYears(6)),
                new User("Mora", "Francesco", LocalDate.now().minusYears(6)),
                new User("Roco", "Fausto", LocalDate.now().minusYears(7)),
                new User("Busa", "Leonardo", LocalDate.now().minusYears(15)),
                new User("Scaldaferro", "Nicola", LocalDate.now().minusYears(13)),
                new User("Brusamareo", "Cesco", LocalDate.now().minusYears(17)),
                new User("Labbio", "Erik", LocalDate.now().minusYears(5)),
                new User("Maran", "Ivan", LocalDate.now().minusYears(14))
        );

        int regalo = 0;
        for(User user : utenti) {
            double res = ordine.getOrderPrice(items, user, LocalTime.of(18,0,0));
            if(res == 0)
                regalo++;
            assertTrue(res==5.5D || res == 0);
        }
        System.out.println("Gelati regalati: " + regalo);
        assertTrue(regalo <= 10);
    }

    @Test
    public void isMinorenne_Test()
    {
        assertFalse(calculator.isMinorenne(new User("Panighel", "Cristiano", LocalDate.of(1999,10,06))));
        User user = new User("Rossi", "Mario", LocalDate.now().minusYears(17));
        assertTrue(calculator.isMinorenne(user));
        User userToday = new User("Prandello", "Fabio", LocalDate.now().minusYears(18));
        assertFalse(calculator.isMinorenne(userToday));
    }
}