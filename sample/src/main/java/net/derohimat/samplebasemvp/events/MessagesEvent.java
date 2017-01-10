package net.derohimat.samplebasemvp.events;

import android.support.annotation.Nullable;

public class MessagesEvent {
    @Nullable
    private final boolean mSuccess;
    @Nullable
    private final String message;

    public MessagesEvent(boolean mSuccess, @Nullable String message) {
        this.mSuccess = mSuccess;
        this.message = message;
    }

    @Nullable
    public boolean ismSuccess() {
        return mSuccess;
    }

    @Nullable
    public String getMessage() {
        return message;
    }
}
