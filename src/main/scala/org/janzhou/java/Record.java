package org.janzhou.java;

import java.nio.ByteBuffer;



/**
 *  * A record: a serialized key and value along with the associated CRC and other fields
 *   */
public final class Record {

    /**
 *      * The current offset and size for all the fixed-length fields
 *           */
    public static final int CRC_OFFSET = 0;
    public static final int CRC_LENGTH = 4;
    public static final int MAGIC_OFFSET = CRC_OFFSET + CRC_LENGTH;
    public static final int MAGIC_LENGTH = 1;
    public static final int ATTRIBUTES_OFFSET = MAGIC_OFFSET + MAGIC_LENGTH;
    public static final int ATTRIBUTE_LENGTH = 1;
    public static final int TIMESTAMP_OFFSET = ATTRIBUTES_OFFSET + ATTRIBUTE_LENGTH;
    public static final int TIMESTAMP_LENGTH = 8;
    public static final int KEY_SIZE_OFFSET_V0 = ATTRIBUTES_OFFSET + ATTRIBUTE_LENGTH;
    public static final int KEY_SIZE_OFFSET_V1 = TIMESTAMP_OFFSET + TIMESTAMP_LENGTH;
    public static final int KEY_SIZE_LENGTH = 4;
    public static final int KEY_OFFSET_V0 = KEY_SIZE_OFFSET_V0 + KEY_SIZE_LENGTH;
    public static final int KEY_OFFSET_V1 = KEY_SIZE_OFFSET_V1 + KEY_SIZE_LENGTH;
    public static final int VALUE_SIZE_LENGTH = 4;

    /**
 *      * The size for the record header
 *           */
    public static final int HEADER_SIZE = CRC_LENGTH + MAGIC_LENGTH + ATTRIBUTE_LENGTH;

    /**
 *      * The amount of overhead bytes in a record
 *           */
    public static final int RECORD_OVERHEAD = HEADER_SIZE + TIMESTAMP_LENGTH + KEY_SIZE_LENGTH + VALUE_SIZE_LENGTH;

    /**
 *      * The "magic" values
 *           */
    public static final byte MAGIC_VALUE_V0 = 0;
    public static final byte MAGIC_VALUE_V1 = 1;

    /**
 *      * The current "magic" value
 *           */
    public static final byte CURRENT_MAGIC_VALUE = MAGIC_VALUE_V1;

    /**
 *      * Specifies the mask for the compression code. 3 bits to hold the compression codec. 0 is reserved to indicate no
 *           * compression
 *                */
    public static final int COMPRESSION_CODEC_MASK = 0x07;

    /**
 *      * Specify the mask of timestamp type.
 *           * 0 for CreateTime, 1 for LogAppendTime.
 *                */
    public static final byte TIMESTAMP_TYPE_MASK = 0x08;
    public static final int TIMESTAMP_TYPE_ATTRIBUTE_OFFSET = 3;

    /**
 *      * Compression code for uncompressed records
 *           */
    public static final int NO_COMPRESSION = 0;

    /**
 *      * Timestamp value for records without a timestamp
 *           */
    public static final long NO_TIMESTAMP = -1L;

    private final ByteBuffer buffer;
    private final Long wrapperRecordTimestamp;
    private final TimestampType wrapperRecordTimestampType;

    public Record(ByteBuffer buffer) {
        this.buffer = buffer;
        this.wrapperRecordTimestamp = null;
        this.wrapperRecordTimestampType = null;
    }

}
