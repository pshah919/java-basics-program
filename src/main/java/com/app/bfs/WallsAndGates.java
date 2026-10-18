package com.app.bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
You are given an m x n grid rooms initialized with these three possible values.

-1 A wall or an obstacle.
0 A gate.
INF Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may
assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate,
 it should be filled with INF.
 */
public class WallsAndGates {

    private final List<int[]> DIRECTIONS = Arrays.asList(new int[]{0, 1}, new int[]{1, 0}, new int[]{0, -1},
            new int[]{-1, 0});

    public void wallsAndGates(int[][] rooms) {
        Queue<int[]> queue = new LinkedList<>();
           int m = rooms.length;
           if(rooms.length == 0) return;
           int n = rooms[0].length;
           for(int row=0; row<m ; row++){
               for (int col=0;col<n;col++){
                   if(rooms[row][col] == 'G'){
                       queue.add(new int[]{row,col});
                   }
               }
           }
           while(!queue.isEmpty()){
               int[] pos = queue.poll();
               int row= pos[0];
               int col=pos[1];
               for(int[] dir : DIRECTIONS){
                   int currRow = row + dir[0];
                   int currCol = col + dir[1];
                   if(currRow<0 || currCol<0 || currRow >m || currCol>n || rooms[currRow][currCol]==0){
                       continue;
                   }
                   rooms[currRow][currCol]= rooms[row][col]+1;
                   queue.add(new int[]{currRow,currCol});
               }
           }

    }

}
