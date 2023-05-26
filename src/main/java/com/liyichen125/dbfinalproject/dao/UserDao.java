package com.liyichen125.dbfinalproject.dao;

import com.liyichen125.dbfinalproject.dto.UserRegisterRequest;
import com.liyichen125.dbfinalproject.model.User;

public interface UserDao {
    //返回值 依據名稱對應資料庫動作（參數類型 參數名稱）

    User getUserById(Integer user_id);
    Integer createUser(UserRegisterRequest userRegisterRequest);

}
