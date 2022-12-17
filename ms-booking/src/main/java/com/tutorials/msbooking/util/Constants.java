package com.tutorials.msbooking.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

@UtilityClass
@NoArgsConstructor(access = AccessLevel.NONE)
public final class Constants {
    public static final String REQUEST_LOG_FORMAT = "Request data: [URL: {}, payload: {}]";
    public static final String RESPONSE_LOG_FORMAT = "Response data: [URL:{}, payload: {}]";

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";

    public static final String BOOKINGS_URL = "/bookings";
    public static final String FILES_URL = "/files";
    public static final String ADD_SHEET_URL = "/files/{file-name}";
    public static final String PATH_ID = "/{id}";
    public static final String EXTRACT_JWT_URL = "/jwt/extract";

    public static final String ADMIN = "ADMIN";
}
