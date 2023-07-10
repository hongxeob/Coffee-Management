package com.example.gccoffee;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Utils {

    public static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        return timestamp != null ? timestamp.toLocalDateTime() : null;
    }
}
