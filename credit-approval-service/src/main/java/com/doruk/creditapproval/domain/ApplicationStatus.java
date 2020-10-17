package com.doruk.creditapproval.domain;

import lombok.Getter;

@Getter
public enum ApplicationStatus {
    APPROVED,
    REJECTED,
    IN_PROCESS;
}
