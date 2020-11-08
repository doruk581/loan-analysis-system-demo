package com.doruk.blacklist.interfaces.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class IsBlacklistResponse {
    private Boolean isBlacklist;
}
