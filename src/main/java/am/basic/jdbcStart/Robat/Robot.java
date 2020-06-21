package am.basic.jdbcStart.Robat;

import am.basic.jdbcStart.Robat.Eye.Eye;
import am.basic.jdbcStart.Robat.Feet.Feet;
import am.basic.jdbcStart.Robat.Hand.Hand;
import am.basic.jdbcStart.Robat.Head.Head;

public class Robot {
    private Eye eye;
    private Feet feet;
    private Hand hand;
    private Head head;

    public Robot(Eye eye, Feet feet, Hand hand, Head head) {
        this.eye = eye;
        this.feet = feet;
        this.hand = hand;
        this.head = head;
    }
    public void hit() {
        hand.hit();
    }


    public void think() {
        head.think();
    }


    public void walk() {
        feet.walk();
    }
    public void setEye() {
        eye.switchColor();
    }


}
