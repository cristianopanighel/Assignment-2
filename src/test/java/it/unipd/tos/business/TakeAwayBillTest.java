package it.unipd.tos.business;

import it.unipd.tos.business.exception.TakeAwayBillException;
import org.junit.Test;

import static org.junit.Assert.*;

public class TakeAwayBillTest {
    @Test
    public void TakeAwayBillException_Test()
    {
        TakeAwayBillException expection = new TakeAwayBillException("Troppi elementi ordinati");
        assertEquals("Troppi elementi ordinati", expection.getMessage());
    }

}