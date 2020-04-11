package com.example.geosolve.dataModel;

public class Node {
    private float x, y;
    private boolean move;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Node(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void moveNode(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void containsRadius(float x, float y) {
        boolean xBool = this.x - 40 < x && x < this.x + 40;
        boolean yBool = this.y - 40 < y && y < this.y + 40;
        move = xBool && yBool;
    }

    public boolean isMove() {
        return move;
    }

    public boolean stopMove() {
        if (move) {
            move = false;
            return true;
        }else
            return false;
    }
}
