import java.time.LocalDate;

public class CustomerDto {
    
    @length(min=2 , max=30)
    private String name;
    
    @Email
    private String email;

    @Adulthood
    private LocalDate birthDay;

    @Min(0)
    @Max(100)
    private int discountRate;



    public void setName(String s){
        this.name = s;
        
    }
    public void setEmail(String s){
        this.email= s;
    }
    public void setBirthDay(LocalDate s){
        this.birthDay = s;
    }
    public void setDiscountRate(int x){
        this.discountRate = x;
    }
}
