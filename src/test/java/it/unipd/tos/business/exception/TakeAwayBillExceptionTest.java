package it.unipd.tos.business.exception;

import org.junit.Test;
import static org.junit.Assert.*;

public class TakeAwayBillExceptionTest {
    @Test
    public void TakeAwayBillException_Test()
    {
        TakeAwayBillException expection = new TakeAwayBillException("Troppi elementi ordinati");
        assertEquals("Troppi elementi ordinati", expection.getMessage());
    }
}