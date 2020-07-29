package com.kyawt.schooldb.admin.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.kyawt.schooldb.model.AdminModel;

import java.util.List;

@Dao
public interface AdminDAO {

    @Insert
    long insertTask(AdminModel adminModel);

    @Query("DELETE FROM adminmodel WHERE admin_id = :adminId")
    void deleteAdmin(int adminId);

    @Query("UPDATE adminmodel SET Admin_user_name = :user_name, Admin_email = :email, Admin_password = :password WHERE admin_id = :admin_id")
    void updateAdminByID(String user_name, String email, String password, int admin_id);

    @Query("SELECT * FROM adminmodel ORDER BY admin_id asc")
    List<AdminModel> getAdmin();


    @Query("UPDATE adminmodel SET  Admin_password = :password WHERE admin_id = :admin_id")
    void updatePasswordByAdminID(String password, int admin_id);


    @Query("UPDATE adminmodel SET Admin_user_name = :user_name, Admin_email = :email WHERE admin_id = :admin_id")
    void updateAdminInfoByID(String user_name, String email, int admin_id);
}
