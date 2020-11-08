package com.doruk.blacklist.application;

import com.doruk.blacklist.domain.Blacklist;
import com.doruk.blacklist.interfaces.request.AddBlacklistRequest;
import com.doruk.blacklist.interfaces.request.UpdateBlacklistRequest;
import com.doruk.blacklist.interfaces.response.IsBlacklistResponse;

public interface BlacklistService {
    void AddBlackList(final AddBlacklistRequest request);

    Blacklist getBlacklistInformation(final String identity);

    IsBlacklistResponse isInBlackList(final String identity);

    void updateBlackList(final UpdateBlacklistRequest request);
}
