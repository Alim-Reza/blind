package graph;

import javax.management.Query;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {

    public HashMap<char[][], Integer> testCases() {
        char[][] t1 = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        char[][] t2 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        char[][] t3 ={{'0'}} ;
        char[][] t4 = {
                {'1', '0', '0', '1', '1', '0', '1'}
        };
        char[][] t5 = {
                {'1'},
                {'0'},
                {'1'},
                {'1'},
                {'0'},
                {'1'}
        };
        char[][] t6 = {
                {'0','0','0','0','0','1'}
        };
        HashMap<char[][], Integer> testCases = new HashMap<>();
//        testCases.put(t1, 1);
//        testCases.put(t2, 3);
//        testCases.put(t3, 0);
//        testCases.put(t4, 3);
//        testCases.put(t5, 3);
        testCases.put(t6, 1);

        return testCases;
    }

    public char[][] createMappingMatrix(char[][] originalMatrix) {
        char[][] copy = new char[originalMatrix.length][originalMatrix[0].length];
        for(int i = 0; i < originalMatrix.length ; i++) {
            for(int j = 0; j < originalMatrix[0].length ; j++) {
                copy[i][j] = '0';
            }
        }
        return copy;
    }
    public int numIslands(char[][] grid) {
        char[][] mappingMatrix = createMappingMatrix(grid);

        Queue<Index> q = new LinkedList<>();
        Index source = new Index(0,0);
        int numberOfIslands = 0;
        q.add(source);
        boolean isItUrFirstTime = true;

        boolean ajaira = true;
        for(int i = 0; i < grid.length ; i++) {
            for(int j = 0; j < grid[0].length ; j++) {
                if(grid[i][j] == '1') {
                    ajaira = false;
                    break;
                }
            }
        }

        if(ajaira) return 0;

        while(!q.isEmpty()) {
            Index newSource = q.poll();
            ArrayAndQIndex a = populateQueueAndMap(
                    grid,
                    mappingMatrix,
                    newSource,
                    q
            );
            mappingMatrix = a.grid;
            q = a.q;
            if(isItUrFirstTime) {
                isItUrFirstTime = false;
            }
            if(q.isEmpty()) {
                q = chooseNewSource(
                        grid,
                        mappingMatrix,
                        newSource,
                        q
                );
                numberOfIslands++;
            }
        }

//    for(int i = 0; i < grid.length ; i++) {
//        for(int j = 0; j < grid[0].length ; j++) {
//            System.out.print(mappingMatrix[i][j]+",");
//        }
//        System.out.println();
//    }

        return numberOfIslands;
    }
    public ArrayAndQIndex populateQueueAndMap(char[][] grid, char[][] copyGrid, Index sourceIndex, Queue<Index> q) {
        if(sourceIndex.row-1 >= 0 && grid[sourceIndex.row-1][sourceIndex.column] == '1'
                && copyGrid[sourceIndex.row-1][sourceIndex.column] == '0') {
            q.add(new Index(sourceIndex.row-1,sourceIndex.column));
            copyGrid[sourceIndex.row-1][sourceIndex.column] = '1';
        }
        if(sourceIndex.column-1 >= 0 && grid[sourceIndex.row][sourceIndex.column-1] == '1'
                && copyGrid[sourceIndex.row][sourceIndex.column-1] == '0') {
            q.add(new Index(sourceIndex.row,sourceIndex.column-1));
            copyGrid[sourceIndex.row][sourceIndex.column-1] = '1';
        }
        if(sourceIndex.row+1 < grid.length && grid[sourceIndex.row+1][sourceIndex.column] == '1'
                && copyGrid[sourceIndex.row+1][sourceIndex.column] == '0') {
            q.add(new Index(sourceIndex.row+1,sourceIndex.column));
            copyGrid[sourceIndex.row+1][sourceIndex.column] = '1';
        }
        if(sourceIndex.column+1 < grid[0].length && grid[sourceIndex.row][sourceIndex.column+1] == '1'
                && copyGrid[sourceIndex.row][sourceIndex.column+1] == '0') {
            q.add(new Index(sourceIndex.row,sourceIndex.column+1));
            copyGrid[sourceIndex.row][sourceIndex.column+1] = '1';
        }
        return new ArrayAndQIndex(copyGrid, q);
    }
    public Queue<Index> chooseNewSource(char[][] grid, char[][] copyGrid, Index sourceIndex, Queue<Index> q) {
        int row = sourceIndex.row;
        int col = sourceIndex.column;
        if(row >= grid.length-1 && col >= grid[0].length-1 ) return q;
        if(col < grid[0].length-1) {
            col++;
        }
        else {
            col = 0;
            row++;
        }
        while(true){
            if(
                grid[row][col] != '0' &&
                copyGrid[row][col] != '1'
            ) {
                q.add(new Index(row, col));
                break;
            } else {
                if(col < grid[0].length-1) {
                    col++;
                }
                else {
                    col = 0;
                    row++;
                    if(row >= grid.length) break;
                }
            }
        }
        return q;
    }

    public static void main(String[] args) {
        NumberOfIslands n = new NumberOfIslands();
        n.testCases().forEach((key, val) -> {
            int result = n.numIslands(key);
            System.out.println("result is: " + result + " result: " + (result == val));
        });
    }
}
class Index {
    int row;
    int column;

    Index(int row, int column) {
        this.row = row;
        this.column = column;
    }
}
class ArrayAndQIndex {
    char[][] grid;
    Queue<Index> q;
    ArrayAndQIndex(char[][] grid, Queue<Index> q) {
        this.grid = grid;
        this.q = q;
    }
}
//        for(int i = 0; i < grid.length ; i++) {
//        for(int j = 0; j < grid[0].length ; j++) {
//        System.out.print(mappingMatrix[i][j]+",");
//            }
//                    System.out.println();
//        }

//         for(int i = 0; i < grid.length ; i++) {
//        for(int j = 0; j < grid[0].length ; j++) {
//        System.out.print(mappingMatrix[i][j]+",");
//            }
//                    System.out.println();
//        }