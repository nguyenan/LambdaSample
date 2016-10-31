package ag.tend.awslambda.util;

import java.nio.ByteBuffer;

/**
 * Created by annguyen on 31/10/16.
 */
public class Utils {
    public static String byteBufferToString(ByteBuffer buffer) {
        byte[] bytes;
        if (buffer.hasArray()) {
            bytes = buffer.array();
        } else {
            bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
        }
        return new String(bytes);
    }
}
