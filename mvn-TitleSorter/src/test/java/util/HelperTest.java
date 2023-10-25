package util;

import entity.Photo;
import exception.FileHasNoExtensionException;
import exception.FileIsNullException;
import exception.ListOfPhotoIsEmptyException;
import exception.ListOfPhotosIsNullException;
import lombok.Getter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Getter

class HelperTest {
    private static final Photo PHOTO_ONE=Photo.of(Path.of("dummy"),"dummy",1, List.of(1));
    private static final Photo PHOTO_TWO=Photo.of(Path.of("dummy"),"dummy",2, List.of(1,2));
    private static final Photo PHOTO_WITH_NEGATIVE_VALUE =Photo.of(Path.of("dummy"),"dummy",-1, List.of(1,-1));
    @Nested
    class GetFileExtensionMethodTests{
        @Test
        void fileIsNull(){
            assertThrows(FileIsNullException.class,()->Helper.getFileExtension(null));
        }
        @Test
        void fileExtensionIsJpg(){
            Assertions.assertThat(Helper.getFileExtension(new File("test.jpg"))).isEqualTo("jpg");
        }
        @Test
        void fileExtensionIsZip(){
            Assertions.assertThat(Helper.getFileExtension(new File("test.zip"))).isEqualTo("zip");
        }
        @Test
        void fileIsFolder(){
            assertThrows(FileHasNoExtensionException.class,()->Helper.getFileExtension(new File( "test")));
        }

    }
    @Nested
    class FindOutAmountOfNumbersMethodTests{
        @Test
        void maxNumberIsOne(){
            int amountOfNumbers = Helper.findOutAmountOfNumbers(List.of(PHOTO_ONE));
            assertEquals(amountOfNumbers,1);
        }
        @Test
        void maxNumberIsTwo(){
            int amountOfNumbers = Helper.findOutAmountOfNumbers(List.of(PHOTO_TWO));
            assertEquals(amountOfNumbers,2);
        }
        @Test
        void oneNumberIsNegative(){
            int amountOfNumbers = Helper.findOutAmountOfNumbers(List.of(PHOTO_WITH_NEGATIVE_VALUE));
            assertEquals(amountOfNumbers,1);
        }
        @Test
        void ListOfNumbersIsNull(){
            assertThrows(ListOfPhotosIsNullException.class,()->Helper.findOutAmountOfNumbers(null));
        }
        @Test
        void ListOfNumbersIsEmpty(){
            assertThrows(ListOfPhotoIsEmptyException.class,()->Helper.findOutAmountOfNumbers(List.of()));
        }
    }

    @Nested
    class FindOutAmountOfVariantsMethodTests{
        @Test
        void maxVariantIsOne(){
            int amountOfVariants = Helper.findAmountOfVariants(List.of(PHOTO_ONE));
            org.assertj.core.api.Assertions.assertThat(amountOfVariants).isEqualTo(1);
        }
        @Test
        void maxVariantIsTwo(){
            int amountOfVariants = Helper.findAmountOfVariants(List.of(PHOTO_ONE,PHOTO_TWO));
            org.assertj.core.api.Assertions.assertThat(amountOfVariants).isEqualTo(2);
        }
        @Test
        void ListOfVariantsIsNull(){
            assertThrows(ListOfPhotosIsNullException.class,()-> Helper.findAmountOfVariants(null));
        }
        @Test
        void variantsListHasNegativeVariant(){
            int amountOfVariants = Helper.findAmountOfVariants(List.of(PHOTO_ONE, PHOTO_WITH_NEGATIVE_VALUE));
            org.assertj.core.api.Assertions.assertThat(amountOfVariants).isEqualTo(1);
        }
        @Test
        void ListOfVariantsIsEmpty(){
            assertThrows(ListOfPhotoIsEmptyException.class,()->Helper.findAmountOfVariants(List.of()));
        }

    }


}