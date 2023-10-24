package util;

import entity.Photo;
import exception.ListOfPhotosIsNullException;
import exception.PathIsNullException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith({
    MockitoExtension.class
})
class StatisticTest {
    private static final Photo PHOTO_ONE=Photo.of(Path.of("dummy"),"dummy",1, List.of(1));
    private static final Photo PHOTO_ALSO_VARIANT_ONE =Photo.of(Path.of("dummy"),"dummy",1, List.of(2,4));
    private static final Photo PHOTO_TWO=Photo.of(Path.of("dummy"),"dummy",2, List.of(1,2));
    private static final Photo PHOTO_WITH_NEGATIVE_VALUE =Photo.of(Path.of("dummy"),"dummy",-1, List.of(1,-1));
    private static final Path ARBITRARY_PATH = Paths.get("C:\\testInformation");
    @InjectMocks
    HashMap<Integer, HashSet<Integer>> map;



   @Nested
    class GetStatisticMethodTests{
       @Test
       void listOfPhotosIsNull(){
           Assertions.assertThrows(ListOfPhotosIsNullException.class,
                   ()-> Statistic.getStatistic(null,ARBITRARY_PATH));
       }
       @Test
       void pathIsNull(){
           Assertions.assertThrows(PathIsNullException.class,
                   ()-> Statistic.getStatistic(List.of(PHOTO_ONE),null));
       }
       @Test
       void returnMapWithOneElement(){
           map.put(1,new HashSet<>(List.of(1)));
           org.assertj.core.api.Assertions.assertThat(Statistic
                           .getStatistic(List.of(PHOTO_ONE),ARBITRARY_PATH))
                   .isEqualTo(map);
       }
       @Test
       void returnTwoPhotoStatistic(){
           HashMap<Integer, HashSet<Integer>>correctMap=new HashMap<>();
           correctMap.put(1,new HashSet<>(List.of(1)));
           correctMap.put(2,new HashSet<>(List.of(1,2)));
           org.assertj.core.api.Assertions.assertThat(Statistic
                           .getStatistic(List.of(PHOTO_ONE,PHOTO_TWO),ARBITRARY_PATH))
                   .isEqualTo(correctMap);
       }
       @Test
       void enterRepeatablePhotos(){
           HashMap<Integer, HashSet<Integer>>correctMap=new HashMap<>();
           correctMap.put(1,new HashSet<>(List.of(1)));
           org.assertj.core.api.Assertions.assertThat(Statistic
                           .getStatistic(List.of(PHOTO_ONE,PHOTO_ONE),ARBITRARY_PATH))
                   .isEqualTo(correctMap);
       }
       @Test
       void getStatisticOnFirstVariantWithTwoPhotos() {
           HashMap<Integer, HashSet<Integer>> correctMap = new HashMap<>();
           correctMap.put(1, new HashSet<>(List.of(1, 2, 4)));
           org.assertj.core.api.Assertions.assertThat(Statistic
                           .getStatistic(List.of(PHOTO_ONE, PHOTO_ALSO_VARIANT_ONE), ARBITRARY_PATH))
                   .isEqualTo(correctMap);
       }
    }
    @Nested
    class GetLuckyVariantsMethodTests{
        @Test
        void luckiestVariantIsOne(){
            map.put(1,new HashSet<>( List.of(1,6,3)));
            Assertions.assertEquals( List.of(1),Statistic.getLuckyVariants(map,ARBITRARY_PATH));
        }
        @Test
        void luckiestVariantIsTwo(){
            map.put(1,new HashSet<>( List.of(1,2)));
            map.put(2,new HashSet<>( List.of(1,3,6)));
            map.put(3,new HashSet<>( List.of(6)));

            Assertions.assertEquals( List.of(2),Statistic.getLuckyVariants(map,ARBITRARY_PATH));
        }
        @Test
        void luckiestVariantIsOneAndTwo(){
            map.put(1,new HashSet<>( List.of(2,3,4)));
            map.put(2,new HashSet<>( List.of(1,2,3)));
            map.put(3,new HashSet<>( List.of(6)));
            Assertions.assertEquals( List.of(1,2),Statistic.getLuckyVariants(map,ARBITRARY_PATH));
        }
    }
    @Nested
    class GetUnluckyVariantsMethodTest{
        @Test
        void unluckiestVariantIsOne(){
            map.put(1,new HashSet<>( List.of(1,6,3)));
            Assertions.assertEquals( List.of(1),Statistic.getUnluckyVariants(map,ARBITRARY_PATH));
        }
        @Test
        void unluckiestVariantIsTwo(){
            map.put(1,new HashSet<>( List.of(1,2)));
            map.put(2,new HashSet<>( List.of(1)));
            map.put(3,new HashSet<>( List.of(1,2,3)));

            Assertions.assertEquals( List.of(2),Statistic.getUnluckyVariants(map,ARBITRARY_PATH));
        }
        @Test
        void unluckiestVariantIsOneAndTwo(){
            map.put(1,new HashSet<>( List.of(1)));
            map.put(2,new HashSet<>( List.of(2)));
            map.put(3,new HashSet<>( List.of(3,4)));
            Assertions.assertEquals( List.of(1,2),Statistic.getUnluckyVariants(map,ARBITRARY_PATH));
        }
        @Test
        void listOfVariantsIsNull(){
            Assertions.assertThrows(NullPointerException.class,()->Statistic.getUnluckyVariants(null,ARBITRARY_PATH));
        }
    }
}
