package com.marketinginapp.startup.transpu.utils;

import com.marketinginapp.startup.transpu.utils.enums.ERole;
import com.marketinginapp.startup.transpu.utils.enums.ETypeService;
import com.marketinginapp.startup.transpu.utils.enums.ECompanyStatus;
import com.marketinginapp.startup.transpu.utils.enums.EDay;
import static com.marketinginapp.startup.transpu.utils.enums.ERole.*;

public class ConverterEnum {

    public static ERole stringToERole(String value){
        return switch (value) {
            case "admin" -> ROLE_ADMIN;
            case "mod" -> ROLE_MODERATOR;
            case "driver" -> ROLE_DRIVER;
            default -> ROLE_USER;
        };
    }
    public static String eRoleToString(ERole value){
        return switch (value) {
            case ROLE_ADMIN -> "admin";
            case ROLE_MODERATOR -> "mod";
            case ROLE_DRIVER -> "driver";
            default -> "user";
        };
    }

    // PUBLIC, PRIVATE
    public static ETypeService stringToETypeService(String value){
        return switch (value) {
            case "public" -> ETypeService.PUBLIC;
            default -> ETypeService.PRIVATE;
        };
    }
    public static String eTypeServiceToString(ETypeService value){
        return switch (value) {
            case PUBLIC -> "public";
            default -> "private";
        };
    }
    // ACTIVE, TEST, BLOCKED;
    public static ECompanyStatus stringToECompanyStatus(String value){
        return switch (value) {
            case "active" -> ECompanyStatus.ACTIVE;
            case "blocked" -> ECompanyStatus.BLOCKED;
            default -> ECompanyStatus.TEST;

        };
    }
    public static String eCompanyStatusToString(ECompanyStatus value){
        return switch (value) {
            case ACTIVE -> "active";
            case BLOCKED -> "blocked";
            default -> "test";
        };
    }
    // SUNDAY, MONDAY, TUESDAY, WEDNESDAY, Thursday, FRIDAY, SATURDAY, HOLIDAY;
    public static EDay stringToEDay(String value){
        return switch (value) {
            case "sunday" -> EDay.SUNDAY;
            case "monday" -> EDay.MONDAY;
            case "tuesday" -> EDay.TUESDAY;
            case "wednesday" -> EDay.WEDNESDAY;
            case "thursday" -> EDay.THURSDAY;
            case "friday" -> EDay.FRIDAY;
            case "saturday" -> EDay.SATURDAY;
            default -> EDay.HOLIDAY;
        };
    }
    public static String eDayoString(EDay value){
        return switch (value) {
            case SUNDAY -> "sunday";
            case MONDAY -> "monday";
            case TUESDAY -> "tuesday";
            case WEDNESDAY -> "wednesday";
            case THURSDAY -> "thursday";
            case FRIDAY -> "friday";
            case SATURDAY -> "saturday";
            default -> "holiday";
        };
    }
}
