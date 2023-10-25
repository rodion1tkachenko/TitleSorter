package entity;

import foldersManipulation.folderInformation.FolderInformationImp;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
@Getter
@AllArgsConstructor(staticName = "of")
public class MakingDirectoryData {
    private List<Photo> photoList;
    private FolderInformationImp folderInformation;
}
