package jaframework.implementators.exceptions;

import java.io.IOException;

/**
 * Created by usuario on 15/06/14.
 */
public class IORuntimeException extends RuntimeException {
    public IORuntimeException(IOException e) {
        super(e);
    }
}
