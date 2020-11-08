package com.doruk.blacklist.domain;

import com.doruk.blacklist.application.BlacklistService;
import com.doruk.blacklist.interfaces.request.AddBlacklistRequest;
import com.doruk.blacklist.interfaces.request.UpdateBlacklistRequest;
import com.doruk.blacklist.interfaces.response.IsBlacklistResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class DefaultBlacklistService implements BlacklistService {

    private final BlacklistRepository blacklistRepository;
    private final BlacklistFactory blacklistFactory;

    public DefaultBlacklistService(BlacklistRepository blacklistRepository, BlacklistFactory blacklistFactory) {
        this.blacklistRepository = blacklistRepository;
        this.blacklistFactory = blacklistFactory;
    }

    @Override
    public void AddBlackList(AddBlacklistRequest request) {
        final Blacklist blacklist = blacklistFactory.createFrom(request);
        blacklistRepository.save(blacklist);
    }

    @Override
    public IsBlacklistResponse isInBlackList(String identity) {
        final Optional<Blacklist> blacklist = blacklistRepository.get(identity);

        if (blacklist.isEmpty())
            return IsBlacklistResponse.builder().isBlacklist(Boolean.FALSE).build();

        final Boolean isActive = blacklist.get().getIsActive();

        return IsBlacklistResponse.builder().isBlacklist(isActive).build();
    }

    @Override
    public Blacklist getBlacklistInformation(String identity) {
        return blacklistRepository.get(identity).orElseThrow(() -> BlacklistNotExistException.create(identity));
    }

    @Override
    public void updateBlackList(UpdateBlacklistRequest request) {
        Blacklist blacklist =
                blacklistRepository.get(request.getIdentity()).orElseThrow(() -> BlacklistNotExistException.create(request.getIdentity()));

        blacklist = blacklistFactory.reConstituteFrom(blacklist, request);

        blacklistRepository.update(blacklist);
    }
}
