package com.springboot.empc.utils;

public class ApiConstant {

    /**
     * ----------------- ALL SERVICES -----------------
     */
    public static final String EMP_SERVICE = "empService";

    /**
     * -----------------END POINTS------------------
     */
    // LOGIN CONTROLLER
    public static final String WELCOME = "/welcome";
    public static final String EMP_LOGIN = "/login";

    // ADMIN CONTROLLER

    /**
     * ----------------- CONTROLLERS -----------------
     */

    /**
     * COMMON CONTROLLER
     */
    public static final String API = "/api/v1";

    /**
     * Common CONTROLLER
     */
    public static final String ADD = "/add";
    public static final String UPDATE = "/update";
    public static final String LIST = "/list";
    public static final String REMOVE = "/remove";
    public static final String DETAIL = "/detail";

    /**
     * REQUEST CODE
     */
    public static final int INVALID_REQUEST_CODE = -1;
    // public static final int BAD_REQUEST_CODE = 400;
    // public static final int BAD_TOKEN_CODE = 401;
    // public static final int FORBIDDEN_CODE = 403;
    // public static final int SUCCESS_INSERT_CODE = 201;
    public static final int SUCCESS_CODE = 1;
    // public static final int SERVER_ERROR_CODE = 500;

    /**
     * OTHER
     */
    public static final String APPLICATION_JSON = "application/json";

}
