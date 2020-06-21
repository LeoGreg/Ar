package am.basic.jdbcStart.Robat.Eye;

import am.basic.jdbcStart.Robat.Color;

public class GreenEye implements Eye {

    @Override
    public void switchColor() {
        System.out.println(Color.GREEN);
    }
}
