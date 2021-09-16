import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main{
    public static void main(String[] args) throws IllegalAccessException {
        CustomerDto c = new CustomerDto();
        Validator v = new Validator();

        c.setName("abc");
        c.setBirthDay(LocalDate.now().minusYears(18));
        c.setDiscountRate(40);
        c.setEmail("joseph@hotmail.com");
        
        System.out.println(v.validate(c)); 
    }
}