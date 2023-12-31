package dao;

import entity.DatabaseData;
import entity.Photo;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PhotoDao implements Dao {
   private PhotoDao() {

   }
   private static final PhotoDao INSTANCE=new PhotoDao();
   public static PhotoDao getInstance(){
      return INSTANCE;
   }
   private static final  String SAVE_SQL= """
           insert into %s
           (variant, numbers, fileName)
           values (?,?,?);
           """;

   private static final String DELETE_SQL= """
           delete from photos
           where id = ?
           """;
   private static final String PRINT_ALL_PHOTO_FIELDS_SQL ="""
   select * from photos
""";
   private static final String FIND_BY_FILENAME_SQL="""
      select from photos
      where filename=?
""";
//   public void checkConnection(){
//      try (Connection connection = ConnectionManager.get();
//           PreparedStatement statement = connection.prepareStatement(PRINT_ALL_PHOTO_FIELDS_SQL)) {
//         ResultSet resultSet = statement.executeQuery();
//         while(resultSet.next()){
//            System.out.print("id: "+resultSet.getString("id")+" ");
//            System.out.print("variant : "+resultSet.getString("variant")+" ");
//            System.out.print("fileName : "+resultSet.getString("fileName")+" ");
//            System.out.println("numbers: "+resultSet.getString("numbers")+" ");
//         }
//      } catch (SQLException e) {
//          throw new RuntimeException(e);
//      }
//   }

   public void save(DatabaseData databaseData){
      try (Connection connection = ConnectionManager.get();
           PreparedStatement preparedStatement = connection
                   .prepareStatement(SAVE_SQL.formatted(databaseData.getTableTitle()))) {
         setData(databaseData.getPhoto(), preparedStatement);
         preparedStatement.executeUpdate();
      } catch (SQLException e) {
         throw new RuntimeException(e);
      }
   }
   public  void delete(Integer id){
      try (Connection connection = ConnectionManager.get();
           PreparedStatement preparedStatement= connection.prepareStatement(DELETE_SQL)) {
         preparedStatement.setInt(1,id);
         preparedStatement.executeUpdate();
      } catch (SQLException e) {
          throw new RuntimeException(e);
      }
   }

   private void setData(Photo photo, PreparedStatement preparedStatement) throws SQLException {
      preparedStatement.setInt(1, photo.getVariant());
      preparedStatement.setString(2, photo.getSolvedNumbers().toString()
              .substring(1, photo.getSolvedNumbers().toString().length() - 1));
      preparedStatement.setString(3, photo.getFileName());
   }
   private boolean isContain(Photo photo){
      try (Connection connection = ConnectionManager.get();
           PreparedStatement preparedStatement= connection.prepareStatement(FIND_BY_FILENAME_SQL)) {
         preparedStatement.setString(1, photo.getFileName());
         ResultSet resultSet = preparedStatement.executeQuery();
         if(resultSet.next()){
            return true;
         }
         else{
            return false;
         }
      } catch (SQLException e) {
          throw new RuntimeException(e);
      }
   }

   @Override
   public List findAll() {
      return null;
   }

   @Override
   public Optional findById(Object id) {
      return Optional.empty();
   }

   @Override
   public boolean delete(Object id) {
      return false;
   }

   @Override
   public void update(Object entity) {

   }

   @Override
   public Object save(Object entity) {
      return null;
   }
}
