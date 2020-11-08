package com.doruk.blacklist.domain;

import lombok.*;
import org.bson.codecs.pojo.annotations.BsonId;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Blacklist {
    @BsonId
    private String identityNumber;

    private String name;
    private String surname;
    private LocalDateTime addedTime;
    private Boolean isActive;
    private LocalDateTime changeTime;
}
