package com.ral.service.auth;

import com.ral.model.res.Result;

/**
 * Created by victor on 2018/1/25.
 */
public interface IAuthApiService {

    Result login(String userName, String password);

    Result token(String token);

    Result refresh(String token);

    Result loginout(String token);
}
