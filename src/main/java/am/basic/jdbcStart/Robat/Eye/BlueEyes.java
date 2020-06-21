package am.basic.jdbcStart.Robat.Eye;

import am.basic.jdbcStart.Robat.Color;

import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;

public class BlueEyes implements Eye {


    @Override
    public void switchColor() {
        System.out.println(Color.BLUE);
    }
}
