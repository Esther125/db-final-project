package com.liyichen125.dbfinalproject.constant;

public enum RecordSituation {
    BORROW("BORROW"),
    RETURN("RETURN"),
    UN_RESERVE("UN_RESERVE"),
    UN_BORROW("UN_BORROW"),
    RESERVE("RESERVE");







    private final String displayName;

    RecordSituation(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    }
