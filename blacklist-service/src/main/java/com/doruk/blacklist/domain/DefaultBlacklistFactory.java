package com.doruk.blacklist.domain;

import com.doruk.blacklist.interfaces.request.AddBlacklistRequest;
import com.doruk.blacklist.interfaces.request.UpdateBlacklistRequest;

import java.time.LocalDateTime;

public class DefaultBlacklistFactory implements BlacklistFactory {

    @Override
    public Blacklist createFrom(AddBlacklistRequest request) {
        return Blacklist.builder()
                .addedTime(LocalDateTime.now())
                .identityNumber(request.getIdentityNumber())
                .name(request.getName())
                .surname(request.getSurname())
                .isActive(Boolean.TRUE)
                .build();
    }

    @Override
    public Blacklist reConstituteFrom(Blacklist blacklist, UpdateBlacklistRequest request) {
        if (request.getIsActive() != null)
            blacklist.setIsActive(request.getIsActive());

        if (request.getName() != null)
            blacklist.setName(request.getName());

        if (request.getSurname() != null)
            blacklist.setSurname(request.getSurname());

        blacklist.setChangeTime(LocalDateTime.now());

        return blacklist;
    }
}
