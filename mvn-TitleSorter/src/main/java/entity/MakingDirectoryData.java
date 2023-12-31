package entity;

import foldersManipulation.folderInformation.FolderInformationImp;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.nio.file.Path;
import java.util.List;
@Getter
@AllArgsConstructor(staticName = "of")
public class MakingDirectoryData {
    private List<Photo> photoList;
    private FolderInformationImp folderInformation;
    private Path path;
}
