package org.api.endpoints;

public class Routes {
    // Set the base URI for your API
    public static String base_url = "https://petstore.swagger.io/v2";
    //User module
    public static String post_url = base_url + "/user";
    public static String get_url = base_url + "/user/{username}";
    public static String update_url = base_url + "/user/{username}";
    public static String delete_url = base_url + "/user/{username}";

    public static String booking_base_url = "https://restful-booker.herokuapp.com";
    //User module
    public static String auth_post_url= booking_base_url + "/auth";
    public static String booking_post_url = booking_base_url + "/booking";
    public static String booking_get_url = booking_base_url + "/booking/{id}";
    public static String booking_update_url = booking_base_url + "/booking/{id}";
    public static String booking_delete_url = booking_base_url + "/booking/{id}";
}
