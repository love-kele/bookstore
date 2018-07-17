package Test;

import org.junit.Test;

import java.text.SimpleDateFormat;

public class Time {
    @Test
    public void Tset(){
        SimpleDateFormat time=new SimpleDateFormat("MM/dd");
        System.out.println(time.format(System.currentTimeMillis()));
    }
}
