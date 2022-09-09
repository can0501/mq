//package com.example.mq.算法;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author 钟金灿
// * @since 2022/9/8
// */
//public class 岛屿 {
//
//    int [] gx={0,0,-1,1};
//    int [] gy={-1,1,0,0};
//
//    int xx=0;
//    int yy = 0;
//
//
//    public int numIslands(char[][] grid) {
//
//        xx= grid.length;
//        yy = grid[0].length;
//        Map s = new HashMap();
//        s.put()
//
//        int res= 0;
//
//        for(int i = 0 ; i < xx; i++){
//            for(int j = 0 ; j < yy ; j++){
//                if(grid[i][j]=='1'){
//                    res++;
//                    dfs(i,j,grid);
//
//                }
//
//
//            }
//
//        }
//        return res;
//    }
//
//    void dfs(int i, int j, char[][] grid){
//        if(grid[i][j]=='0')
//            return;
//        grid[i][j]='0';
//        for(int ii = 0 ; ii <4; ii++){
//
//
//            if(( (0<i+gx[ii])&&(i+gx[ii]<xx)    )&&
//                    ( (0<j+gy[ii])&&(j+gy[ii]<yy)    )
//            )
//                dfs(i+gx[ii],j+gy[ii],grid);
//
//        }
//    }
//}
