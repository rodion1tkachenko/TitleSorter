package util;

import exception.KeyIsNullException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;

class PropertiesUtilTest {
//    @InjectMocks
//    PropertiesUtil propertiesUtil;
    @Test
    void enterUrlKey(){
        Assertions.assertThat(PropertiesUtil.get("db.url")).isEqualTo("jdbc:postgresql://localhost:5432/postgres");
    }
    @Test
    void enterPasswordKey(){
        Assertions.assertThat(PropertiesUtil.get("db.password")).isEqualTo("229rodi4))228");
    }
    @Test
    void enterUserKey(){
        Assertions.assertThat(PropertiesUtil.get("db.user")).isEqualTo("postgres");
    }
    @Test
    void keyIsNull(){
        assertThrows(KeyIsNullException.class,()-> PropertiesUtil.get(null));
    }
    @Test
    void noSuchKey(){
        assertEquals(null,PropertiesUtil.get("db.dummy"));
    }


}