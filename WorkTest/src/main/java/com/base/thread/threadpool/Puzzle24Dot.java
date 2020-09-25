package com.base.thread.threadpool;

import java.util.Set;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/9/25 15:00
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class Puzzle24Dot<P,M> implements Puzzle<P, M> {


    @Override
    public P initialPosition() {
        return null;
    }

    @Override
    public boolean isGoal(P position) {
        return false;
    }

    @Override
    public Set<M> legalMoves(P position) {
        return null;
    }

    @Override
    public P move(P position, M move) {
        return null;
    }

    public class Pinfo1{
        private int value;
    }


}
