package com.compassuol.sp.challenge.msuser.commons;

import com.compassuol.sp.challenge.msuser.domain.enums.EventTypeEnumeration;
import com.compassuol.sp.challenge.msuser.domain.rabbitMq.Notification;

public class RabbitConstants {

        public static final Notification NOTIFICATION = new Notification(
                "vinicius@gmail.com",
                EventTypeEnumeration.LOGIN);

}
