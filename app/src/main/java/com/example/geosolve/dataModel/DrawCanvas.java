package com.example.geosolve.dataModel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class DrawCanvas extends View {
    private static Figure figure;
    private static final Paint mPaintNode;
    private static final Paint mPaintLine;

    static {
        figure = new Figure();

        mPaintNode = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintNode.setStrokeWidth(10);
        mPaintNode.setColor(Color.parseColor("#a04343")); // R.color.boldThemeColor

        mPaintLine = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintLine.setStrokeWidth(5);
        mPaintLine.setColor(Color.parseColor("#ceb2b2")); // R.color.lightThemeColor
    }

    public DrawCanvas(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (figure.getNodes().size() > 1) {
            ArrayList<Node> nodes = figure.getNodes();
            for (int i = 0; i < nodes.size() - 1; i++) {
                canvas.drawLine(nodes.get(i).getX(), nodes.get(i).getY(),
                        nodes.get(i + 1).getX(), nodes.get(i + 1).getY(), mPaintLine);
            }}
        for (Node node : figure.getNodes()) {
            canvas.drawCircle(node.getX(), node.getY(), 20, mPaintNode);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float mx = event.getX();
        float my = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                for (Node node : figure.getNodes()) {
                    if(node.inRadius(mx, my))
                        break;
                }
                break;

            case MotionEvent.ACTION_MOVE:
                for (Node node : figure.getNodes()) {
                    if (node.isMove())
                        node.moveNode(mx, my);
                }
                break;

            case MotionEvent.ACTION_UP:
                if (!figure.stopAllNode())
                    figure.addNode(new Node(mx, my));
                break;
        }
        invalidate();
        return true;
    }
}
