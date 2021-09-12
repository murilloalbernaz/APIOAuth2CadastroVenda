package udemy.spring.angular.vendas.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BigDecimalConverter {
    public BigDecimal bigDecimal(String s){
        if(s == null)
            return null;
        s = s.replace(".","").replace(",", ".");
        return new BigDecimal(s);
    }
}
