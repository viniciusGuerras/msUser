package com.compassuol.sp.challenge.msuser.domain.rabbitMq;

import com.compassuol.sp.challenge.msuser.domain.enums.EventTypeEnumeration;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;


@Data
@NoArgsConstructor

public class Notification {
        private String email;
        @Enumerated(EnumType.STRING)
        private EventTypeEnumeration event;
        private Date date;

        public Notification(String email, EventTypeEnumeration event) {
                this.email = email;
                this.event = event;
                this.date = Date.from(Instant.now());
        }
}
