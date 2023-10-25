package util;

import dao.PhotoDao;
import entity.DatabaseData;
import entity.Photo;
import exception.NotValidTableNameException;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TableAction {
    private TableAction() {
    }
    public static final TableAction INSTANCE=new TableAction();

    private static final String CREATE_TABLE_SQL= """
            create table if not exists %s
            (
                id serial primary key,
                variant int,
                numbers varchar(30),
                fileName varchar(100)
            )
            """;
    private static final String DROP_TABLE_SQL= """
            drop table if exists %s
            """;
    public boolean create(String title){
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement= connection.prepareStatement(CREATE_TABLE_SQL.formatted(title))) {
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new NotValidTableNameException();
        }
    }
    public boolean drop(String title){
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement= connection.prepareStatement(DROP_TABLE_SQL.formatted(title))) {
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new NotValidTableNameException();
        }
    }


//    public void savePhotosAtDatabase(DatabaseData data) {
//        String newTitle=Helper.createTableNameWithoutSpaces(data.getTableTitle());
//        data.setTableTitle(newTitle);
//        INSTANCE.create(newTitle);
//        PhotoDao photoDao = PhotoDao.getInstance();
//        for(Photo photo: data.get()){
//            photoDao.save(DatabaseData.);
//        }
//    }

    public void savePhotosAtDatabase(List<Photo> allPhotos, String title) {
        String newTitle=Helper.createTableNameWithoutSpaces(title);
        INSTANCE.create(newTitle);
        PhotoDao photoDao = PhotoDao.getInstance();
        for(Photo photo: allPhotos){
            photoDao.save(DatabaseData.of(photo,newTitle));
        }
    }
}
