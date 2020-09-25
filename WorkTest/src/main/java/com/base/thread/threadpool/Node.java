package com.base.thread.threadpool;

import java.util.LinkedList;
import java.util.List;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2020/9/25 13:36
 * @version 1.0
 * @Modified By:
 * @used in: WhyiseeBench
 */
public class Node<P,M> {
    final P pos;
    final M move;
    final Node<P,M> prev;
    Node(P pos,M move,Node<P,M> prev){
        this.pos = pos;
        this.move = move;
        this.prev = prev;
    }
    List<M> asMoveList(){
        List <M> solution = new LinkedList<M>();
        for (Node <P,M> n = this; n.move != null; n = n.prev){
            solution.add(0,n.move);
        }
        return solution;
    }
}
