package com.marketinginapp.startup.transpu.utils;

public interface Constant {

    // ALL
    public static final String MUST_NOT_BE_NULL_OR_EMPTY = "must not be null or empty";
    public static final String MUST_BE_ONE_VALID_EMAIL = "must be one valid email";
    public static final String LATITUDE_MUST_NOT_BE_NULL = "latitude must not be null";
    public static final String LONGITUDE_MUST_NOT_BE_NULL = "longitude must not be null";
    public static final String DAY_PATTERN = "2024-01-01T";

    // EXCEPTION TOKEN
    public static final String EXCEPTION_TOKEN_EXPIRATION = "token expiration: %s";
    public static final String EXCEPTION_TOKEN_NOT_SUPPORTED = "Token is not support: %s";
    public static final String EXCEPTION_TOKEN_INVALID_FORMAT = "token is invalid format: %s";
    public static final String EXCEPTION_TOKEN_UNAUTHORIZED = "Unauthorized: %s";

    // MAX LENGTH
    public static final String MAXIMUM_LENGTH_10_CHARACTERS = "maximum length 10 characters";
    public static final String MAXIMUM_LENGTH_40_CHARACTERS = "maximum length 40 characters";
    public static final String MAXIMUM_LENGTH_50_CHARACTERS = "maximum length 50 characters";
    public static final String MAXIMUM_LENGTH_70_CHARACTERS = "maximum length 70 characters";
    public static final String MAXIMUM_LENGTH_80_CHARACTERS = "maximum length 80 characters";
    public static final String MAXIMUM_LENGTH_120_CHARACTERS = "maximum length 120 characters";
    public static final String MAXIMUM_LENGTH_200_CHARACTERS = "maximum length 200 characters";
    public static final String MAXIMUM_LENGTH_250_CHARACTERS = "maximum length 250 characters";

    // BETWEEN
    public static final String MUST_BE_BETWEEN_1_AND_10_CHARACTERS = "must be between 1 and 10 characters";
    public static final String MUST_BE_BETWEEN_3_AND_15_CHARACTERS = "must be between 3 and 15 characters";
    public static final String MUST_BE_BETWEEN_3_AND_20_CHARACTERS = "must be between 3 and 20 characters";
    public static final String MUST_BE_BETWEEN_3_AND_50_CHARACTERS = "must be between 3 and 50 characters";
    public static final String MUST_BE_BETWEEN_6_AND_30_CHARACTERS = "must be between 6 and 30 characters";

    // INFO
    public static final String INFO_INVALID_FIELDS = "invalid field(s)";
    public static final String INFO_UNAUTHORIZED = "Unauthorized";

    // EXCEPTION
    public static final String EXCEPTION_MESSAGE = "message exception: %s";
    public static final String EXCEPTION_ID_NOT_FOUND = "register not found by id: %s";
    public static final String EXCEPTION_NAME_NOT_FOUND = "register not found by name: %s";
    public static final String EXCEPTION_EMAIL_NOT_FOUND = "register not found by email: %s";
    public static final String EXCEPTION_EMAIL_ALREADY_REGISTERED = "e-mail already registered, e-mail: %s";
    public static final String EXCEPTION_THE_REQUEST_CANT_ACCESS_TO_RESOURCE = "the request can't access the resources";
    public static final String USER_ID_MUST_NOT_BE_NULL = "user id must not be null";
    public static final String EXCEPTION_STATUS_NOT_UPDATED = "status not updated";

    // ROLE-EXCEPTION
    public static final String EXCEPTION_ENTITY_NOT_FOUND_BY_ID = "%s not found by id: %s"; // name entity, id
    public static final String EXCEPTION_ENTITY_NOT_FOUND_BY_EMAIL = "%s not found by email: %s"; // name entity, email
    public static final String EXCEPTION_ENTITY_NOT_FOUND = "%s not found"; // name entity
    public static final String EXCEPTION_ENTITY_ALREADY_REGISTERED_BY_EMAIL = "%s already registered by email: %s"; // name entity, email
    public static final String EXCEPTION_ENTITY_ALREADY_REGISTERED_BY_NAME = "%s already registered exception: %s"; // name entity, name
    public static final String EXCEPTION_ENTITY_NOT_REGISTERED_BY_NAME = "%s not registered exception: %s"; // name entity, name
    public static final String EXCEPTION_ENTITY_NOT_REGISTERED = "%s not registered exception: %s"; // name entity, exception
    public static final String EXCEPTION_ENTITY_NOT_UPDATED = "%s not updated";
    public static final String EXCEPTION_ENTITY_NOT_UPDATED_EXCEPTION = "%s not updated exception: %s";
    public static final String EXCEPTION_NAME_ALREADY_REGISTERED = "name already registered, name: %s";


    public static final String COMPANY_ID_MUST_NOT_BE_NULL = "company id must not be null";
    public static final String ROUTE_ID_MUST_NOT_BE_NULL = "route id must not be null";
    public static final String DAY_ID_MUST_NOT_BE_NULL = "route id must not be null";
    public static final String PATH_ID_MUST_NOT_BE_NULL = "path id must not be null";
    public static final String DEPARTURE_TIME_ID_MUST_NOT_BE_NULL = "departure id must not be null";
    public static final String POINT_ID_MUST_NOT_BE_NULL = "point id must not be null";
}
