package com.app.bfs;

import java.util.*;

public class OpenLock {

    public int openLock(String[] deadends, String target) {
Map<Character,Character> nextSlot= Map.of('0','1','1','2','2','3','3','4',
        '4','5','5','6','6','7','7','8','8','9','9','0');
        Map<Character, Character> prevSlot = Map.of(
                '0', '9',
                '1', '0',
                '2', '1',
                '3', '2',
                '4', '3',
                '5', '4',
                '6', '5',
                '7', '6',
                '8', '7',
                '9', '8'
        );
        String initalPos="0000";
        Set<String> visitedComb = new HashSet<>(Arrays.asList(deadends));
        Queue<String> pendingComb = new LinkedList<>();
        int turn=0;
        if(visitedComb.contains("0000")){
            return -1;
        }
        visitedComb.add("0000");
        pendingComb.add("0000");
        while(!pendingComb.isEmpty()){
            int size = pendingComb.size();
            for (int i=0;i<size;i++) {
                String currentPos = pendingComb.poll();
                for (int wheel = 0; wheel < 4; wheel++) {
                    StringBuilder sb = new StringBuilder(currentPos);
                    sb.setCharAt(wheel, nextSlot.get(sb.charAt(wheel)));
                    if(sb.equals(target)){
                        return turn;
                    }
                    
                }
            }
        }
        return turn;
    }
}
