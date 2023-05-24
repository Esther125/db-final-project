package com.liyichen125.dbfinalproject.dao;

import com.liyichen125.dbfinalproject.model.User;

public interface UserDao {
    //返回值 依據名稱對應資料庫動作（參數類型 參數名稱）
    String CreateUser(User user);
}
