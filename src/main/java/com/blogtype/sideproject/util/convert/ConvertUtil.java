package com.blogtype.sideproject.util.convert;


import com.blogtype.sideproject.util.security.UserDetailsImpl;

public class ConvertUtil {

    public static Long findUserId(UserDetailsImpl userDetails){
        return userDetails.getUser().getId();
    }


}
