package entity;

import enums.Grade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Getter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class StatisticForDatabase {
    private int variant;
    private List<Integer> solvedNumbers;
    private Grade grade;

}
