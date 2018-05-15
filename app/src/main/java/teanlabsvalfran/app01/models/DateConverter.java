package teanlabsvalfran.app01.models;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by Valfran on 31/01/2018.
 */

public class DateConverter {

    @TypeConverter
    public Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public Long dateToTimestamp(Date date) {
        if (date == null) {
            return null;
        } else {
            return date.getTime();
        }
    }
}

