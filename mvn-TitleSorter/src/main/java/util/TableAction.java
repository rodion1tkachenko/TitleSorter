package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    public void create(String title){
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement= connection.prepareStatement(CREATE_TABLE_SQL.formatted(title))) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void drop(String title){
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement= connection.prepareStatement(DROP_TABLE_SQL.formatted(title))) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
