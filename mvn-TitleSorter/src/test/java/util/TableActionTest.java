package util;

import exception.NotValidTableNameException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
class TableActionTest {

    @Nested
    @Order(1)
    class CreateMethodTests {
        @Test
        void titleIsValid(){
            assertTrue(TableAction.INSTANCE.create("test123"));
        }
        @Test
        void titleIsNumber(){
            assertThrows(NotValidTableNameException.class,()->TableAction.INSTANCE.create("123"));
        }
        @Test
        void titleContainsSpaces(){
            assertThrows(NotValidTableNameException.class,()->TableAction.INSTANCE.create("table 123"));
        }
        @Test
        void titleContainsDash(){
            assertThrows(NotValidTableNameException.class,()->TableAction.INSTANCE.create("table-123"));
        }
        @Test
        void titleContainsUnderlining(){
            assertTrue(TableAction.INSTANCE.create("table_123"));
        }
        @RepeatedTest(2)
        void createTableTwoTimes(){
            assertTrue(TableAction.INSTANCE.create("this_table_is_created_two_times"));
        }
    }


        @Nested
    @Order(2)
    class DropMethodTests{
        @Test
        void deleteTableWhereTitleIsValid(){
            assertTrue(TableAction.INSTANCE.drop("test123"));
        }
        @Test
        void deleteTableWhereTitleIsNumber(){
            assertThrows(NotValidTableNameException.class,()->TableAction.INSTANCE.drop("123"));
        }
        @Test
        void deleteNonExistentTable(){
            assertTrue(TableAction.INSTANCE.drop("non_existent_table"));
        }
        @RepeatedTest(2)
        void deleteTableTwoTimes(){
            assertTrue(TableAction.INSTANCE.drop("this_table_is_created_two_times"));
        }
        @Test
        void deleteTableWhereTitleContainsUnderlining(){
            assertTrue(TableAction.INSTANCE.drop("table_123"));
        }
    }

}