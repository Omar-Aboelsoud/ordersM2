package com.example.orderingm2.common.exception;

import java.io.Serial;

public class LogicalException extends BaseException {

    @Serial
    private static final long serialVersionUID = -6527073160488586546L;

    public LogicalException(ServerError err) {
        this(err, err.getMessage());
    }

    public LogicalException(ServerError err, String clientMsg) {
        super(err, clientMsg, null);
    }


    public LogicalException apply(Object... params) {
        this.params = params;
        return this;
    }
}
