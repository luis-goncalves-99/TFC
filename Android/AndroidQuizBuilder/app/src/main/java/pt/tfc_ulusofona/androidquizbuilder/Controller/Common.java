package pt.tfc_ulusofona.androidquizbuilder.Controller;

public class Common {
    static final String ip = "192.168.1.69:80"; // trocar se estiver fora de casa
    static final String SERVICE_API_URL = "http://"+ip+"/TFC/AndroidQuizBuilder-master/php/www/user.php/";
    static final int RESULT_SUCCESS = 0;
    static final int RESULT_ERROR= 1;
    static final int RESULT_USER_EXISTS = 2;
    static final int RESULT_ADMIN=3;
}
