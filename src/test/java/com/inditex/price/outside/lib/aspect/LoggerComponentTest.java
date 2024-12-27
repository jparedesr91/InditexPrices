package com.inditex.price.outside.lib.aspect;

import com.inditex.price.application.ports.out.forfilteringprices.ForObtainPrices;
import com.inditex.price.application.usecases.FindByProduct;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class LoggerComponentTest {

    private final ProceedingJoinPoint proceedingJoinPoint = Mockito.mock(ProceedingJoinPoint.class);

    private final MethodSignature methodSignature = Mockito.mock(MethodSignature.class);

    private final LoggerComponent loggerComponent = new LoggerComponent();

    private final ForObtainPrices forObtainPrices = Mockito.mock(ForObtainPrices.class);

    private final FindByProduct findByProduct = new FindByProduct(forObtainPrices);

    @Test
    public void testErrorPaypal() throws Throwable {
        when(proceedingJoinPoint.getTarget()).thenReturn(findByProduct);
        when(proceedingJoinPoint.getSignature()).thenReturn(methodSignature);
        when(methodSignature.getMethod()).thenReturn(getClass().getDeclaredMethod("someMethod"));
        when(proceedingJoinPoint.getArgs()).thenReturn(new Object[]{"TESTING"});
        when(proceedingJoinPoint.proceed()).thenReturn(new Object());
        Object object = loggerComponent.logArroundExec(proceedingJoinPoint);
        assertNotNull(object);
    }

    public Object someMethod() {
        return new Object();
    }
}
