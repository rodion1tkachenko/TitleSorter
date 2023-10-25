package entity;

import dao.PhotoDao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
public class DatabaseData {
    private Photo photo;
    private String tableTitle;

}
