package Wincom_Autocomplete_Test;
import org.junit.jupiter.api.Assertions;
import Main.Ex_W_Autocomplete;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Test_Ex_Word {
    @Test
    public void test_Word(){
        //Given
        String a = "";
        //When
        List<String> b = new ArrayList<String>(Arrays.asList("","","",""));
        //Then
        Assertions.assertTrue(Main.Ex_W_Autocomplete.Autocomple_Word(a).equals(b));
    }

}
