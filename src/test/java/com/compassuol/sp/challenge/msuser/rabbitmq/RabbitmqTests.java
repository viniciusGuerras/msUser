package com.compassuol.sp.challenge.msuser.rabbitmq;

import com.compassuol.sp.challenge.msuser.domain.exceptions.MessageConversionException;
import com.compassuol.sp.challenge.msuser.domain.rabbitMq.Notification;
import com.compassuol.sp.challenge.msuser.domain.rabbitMq.RabbitProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.compassuol.sp.challenge.msuser.commons.RabbitConstants.NOTIFICATION;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RabbitmqTests {

    @Mock
    private RabbitProducer rabbitProducer;

    @Test
    public void sendMessage_Ok(){
        doNothing().when(rabbitProducer).sendMessage(any());
        assertDoesNotThrow(() -> rabbitProducer.sendMessage(NOTIFICATION));
    }

    @Test
    public void sendMessage_Error(){
        doThrow(MessageConversionException.class)
                .when(rabbitProducer)
                .sendMessage(any(Notification.class));

        assertThrows(MessageConversionException.class, () -> rabbitProducer.sendMessage(NOTIFICATION));
    }
}
