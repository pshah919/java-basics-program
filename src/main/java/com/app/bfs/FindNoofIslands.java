package com.app.bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FindNoofIslands {

    final List<int[]> dir = Arrays.asList(new int[]{0,1},new int[]{0,-1},new int[]{1,0},new int[]{-1,0});
    public int findIslands(int[][] grid){
        int m = grid.length;
        int islands = 0;
        if(m==0) return islands;
        int n= grid[0].length;
        for(int row=0;row<m;row++){
            for(int col=0;col<n;col++){
                if(grid[row][col]==1){
                    ++islands;
                    grid[row][col]=0;
                    Queue<int[]> queue = new LinkedList<>();
                    queue.add(new int[]{row,col});

                    while(!queue.isEmpty()){
                        int[] pos = queue.poll();
                        int r = pos[0];
                        int l = pos[1];
                        for(int[] di: dir){
                            int currRow = r + di[0];
                            int currCol = l+ di[1];
                            if(currCol>=0 && currRow >= 0 && grid[currRow][currCol] ==1
                                    && currCol<m && currRow<n){
                                grid[currRow][currCol]=0;
                                queue.add(new int[]{currRow,currCol});
                            }
                        }
                    }
                }
            }
        }
return islands;
    }
}
