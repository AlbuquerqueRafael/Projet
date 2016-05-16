package models.enums;

import com.avaje.ebean.annotation.EnumValue;

/**
 * Created by rafael on 27/03/16.
 */
public enum TipoCarona {
    @EnumValue("0")
    IDA,
    @EnumValue("1")
    VOLTA;
}
