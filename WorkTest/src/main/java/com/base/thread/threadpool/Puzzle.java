package com.base.thread.threadpool;

import java.util.Set;

/**
 * use for : 谜题抽象类
 * P 代表位置类
 * M 代表移动类
 * @author zoukh
 * Created in:  2020/9/25 9:42
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public interface Puzzle<P,M> {
    P initialPosition();
    boolean isGoal (P position);
    Set<M> legalMoves (P position);
    P move(P position,M move);
}
