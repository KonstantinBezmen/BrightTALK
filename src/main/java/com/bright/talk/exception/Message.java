package com.bright.talk.exception;

//Assume that error messages can be used without i18n
public enum Message {
    INVALID_ARGUMENT("InvalidArgument"),
    INVALID_REALM_NAME("InvalidRealmName"),
    REALM_NOT_FOUND("RealmNotFound"),
    DUPLICATE_REALM_NAME("DuplicateRealmName");

    private String text;

    Message(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
