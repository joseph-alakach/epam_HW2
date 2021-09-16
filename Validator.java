import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public List<Error> validate(CustomerDto o) throws IllegalAccessException ,NullPointerException{
        List<Error> list = new ArrayList<>();
        Field[] fields =o.getClass().getDeclaredFields();
        
        
        Pattern pattern = Pattern.compile("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
        
        for(Field field: fields){
            field.setAccessible(true);
            //System.out.println(field.isAnnotationPresent(length.class));
            

            if (field.isAnnotationPresent(length.class)) {
                
                length l=field.getAnnotation(length.class);
                //int minimum =(int) l.min();
                String nameValue = (String) field.get(o);
                int min = l.min();

                if (min> nameValue.length() ) {

                    list.add(new Error("name value's length is smaller than the min limit"));

                }
                if (l.max()<nameValue.length()) {
                    list.add(new Error("name value's length is greater than the max limit"));
                }
            }else if(field.isAnnotationPresent(Email.class)){

                String emailValue = (String) field.get(o);
                Matcher m = pattern.matcher(emailValue);

                if (!m.find()) {
                    list.add(new Error("email value's is not appropriate"));
                }

            }else if(field.isAnnotationPresent(Adulthood.class)){

                LocalDate birhtDateValue = (LocalDate) field.get(o);
                
                if (birhtDateValue.isAfter(LocalDate.now().minusYears(18))) {
                    list.add(new Error("the user is under 18"));
                }

            }else{

                Min min = field.getAnnotation(Min.class);
                Max max = field.getAnnotation(Max.class);
                int minLimit = min.value();
                int maxLimit = max.value();
                int rateValue = (int) field.get(o);

                if (rateValue<minLimit || rateValue> maxLimit) {
                    list.add(new Error("the discountRate is not valid"));
                }

            }
        }
        
        return list;
        
    }
    
}
