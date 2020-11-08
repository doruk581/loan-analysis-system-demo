package com.doruk.blacklist.domain;

import java.util.Optional;

public interface BlacklistRepository {
    void save(final Blacklist blacklist);

    void update(final Blacklist blacklist);

    Optional<Blacklist> get(final String identity);
}
