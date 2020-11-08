package com.doruk.blacklist.domain;

import com.doruk.blacklist.interfaces.request.AddBlacklistRequest;
import com.doruk.blacklist.interfaces.request.UpdateBlacklistRequest;

public interface BlacklistFactory {
    Blacklist createFrom(final AddBlacklistRequest request);

    Blacklist reConstituteFrom(final Blacklist blacklist, final UpdateBlacklistRequest request);
}
