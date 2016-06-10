package web_services_connections_handlers;

public class WebServicesUrl {

    private static String ip="192.168.1.6";
//
//    public static String ADD_USER ="http://" + ip + ":8080/AgriMarket_v2.1/service/adduser";
//    public static String GET_MAIN_CATEGORIES ="http://" + ip + ":8080/AgriMarket_v2.1/service/getmaincategories";
//    public static String LOG_OUT ="http://" + ip + ":8080/AgriMarket_v2.1/service/logout";
//    public static String UPDAT_USER ="http://" + ip + ":8080/AgriMarket_v2.1/service/updateuser";
//    public static String GET_CHILDREN = "http://" + ip + ":8080/AgriMarket_v2.1/service/getchildren";
//    public static String GET_OFFERS ="http://" + ip + ":8080/AgriMarket_v2.1/service/getoffers";
//    public static String SEARCH_BY_OFFER_LOCATION = "http://"+ ip +":8080/AgriMarket_v2.1/service/search/offer/location";
//    public static String REMOVE_OFFER = "http://"+ ip +":8080/AgriMarket_v2.1/service/removeoffer";
//    public static String GET_USER_OFFERS = "http://"+ ip +":8080/AgriMarket_v2.1/service/getuseroffers";
//    public static String USER_CHECK = "http://"+ ip +":8080/AgriMarket_v2.1/service/usercheck";


    public static String Image_URL = "http://"+ ip +":8084/AgriMarket_v2.2/";

    public static String IMAGE = "http://"+ip+":8084/AgriMarket_v2.2/images/categories/1.jpg";
    public static String REMOVE_OFFER = "http://"+ ip +":8084/AgriMarket_v2.2/service/removeoffer";
    public static String ADD_USER ="http://"+ip+":8084/AgriMarket_v2.2/service/adduser";                       //done
    public static String GET_MAIN_CATEGORIES ="http://" + ip + ":8084/AgriMarket_v2.2/service/getmaincategories";  //done
    public static String LOG_OUT ="http://" + ip + ":8084/AgriMarket_v2.2/service/logout";                         //done
    public static String UPDAT_USER ="http://" + ip + ":8084/AgriMarket_v2.2/service/updateuser";                  //done
    public static String GET_CHILDREN = "http://" + ip + ":8084/AgriMarket_v2.2/service/getchildren";
    public static String GET_USER_OFFERS = "http://"+ ip +":8084/AgriMarket_v2.2/service/getuseroffers";
    public static String USER_CHECK = "http://"+ ip +":8084/AgriMarket_v2.2/service/usercheck";
    public static String GET_OFFERS_LIMITEd = "http://"+ ip +":8084/AgriMarket_v2.2/service/getofferslimited";
    public static String SEARCH_OFFERS_LIMITED = "http://"+ ip +":8084/AgriMarket_v2.2/service/search/offerslimited/product";
    public static String GET_USER = "http://"+ ip +":8084/AgriMarket_v2.2/service/getuser";
}
