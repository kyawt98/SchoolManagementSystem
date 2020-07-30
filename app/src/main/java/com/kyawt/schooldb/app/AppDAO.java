package com.kyawt.schooldb.app;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.kyawt.schooldb.model.AppModel;
import java.util.List;

@Dao
public interface AppDAO {
    @Insert
    long insertTask(AppModel appModel);

    @Query("UPDATE appmodel SET App_name = :app_name WHERE app_id = :app_id")
    void updateAppNameByID(String app_name, int app_id);

    @Query("SELECT * FROM appmodel ORDER BY app_id asc")
    List<AppModel> getApp();

    @Query("DELETE FROM appmodel WHERE app_id = :appId")
    void deleteAppByID(int appId);
}
