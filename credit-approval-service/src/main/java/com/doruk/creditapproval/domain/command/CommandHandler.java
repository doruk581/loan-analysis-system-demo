package com.doruk.creditapproval.domain.command;

public interface CommandHandler<T> {
    void handle(T t);
}
