

package com.project.modules.security.password;

/**
 *
 *
 *
 */
public class PasswordUtils {
    private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public static String encode(String str){
        return passwordEncoder.encode(str);
    }


    /**
     * Compare passwords for equality
     * @param str
     * @param  password
     * @return         ture:success   false:failure
     */
    public static boolean matches(String str, String password){
        return passwordEncoder.matches(str, password);
    }


    public static void main(String[] args) {
        String str = "admin";
        String password = encode(str);

        System.out.println(password);
        System.out.println(matches(str, password));
    }

}
