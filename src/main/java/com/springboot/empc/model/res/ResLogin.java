package com.springboot.empc.model.res;

import com.springboot.empc.base.ResponseBase;
import com.springboot.empc.model.res.data.LoginData;

public class ResLogin extends ResponseBase {
    LoginData loginData;

    public LoginData getLoginData() {
        return loginData;
    }

    public void setLoginData(LoginData loginData) {
        this.loginData = loginData;
    }

}
