package it.unipd.tos.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class MenuItemTest {
    @Test
    public void MenuItem_Test()
    {
        MenuItem item = new MenuItem(MenuItem.Item.GELATI, "Coppa Nafta", 5.0D);
        assertEquals(MenuItem.Item.GELATI, item.getItemType());
        assertEquals("Coppa Nafta", item.getName());
        assertEquals(5.0D, item.getPrice(), 0);
    }
}