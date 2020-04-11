package com.example.geosolve;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.RecycleViewHolder> {

    private static List<StepSlove> stepSloveList;
    private static Context context;

    public static Context getContext() {
        return context;
    }

    public RecycleAdapter(Context context) {
        RecycleAdapter.context = context;
        stepSloveList = new ArrayList<StepSlove>();
    }

    public static void addItem(StepSlove stepSlove) {
        stepSloveList.add(stepSlove);
    }

    public static void clear() {
        stepSloveList.clear();
    }

    public static void addAll(List<StepSlove> steps) {
        stepSloveList.addAll(steps);
    }

    static class RecycleViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView rule;
        ImageView openButton;
        FrameLayout open_layout;
        boolean open = false;

        RecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.text);
            open_layout = itemView.findViewById(R.id.open_layout);
            rule = itemView.findViewById(R.id.rule);
            openButton = itemView.findViewById(R.id.openButton);
        }
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.step_card, parent, false);
        return new RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecycleViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.open) {
                    holder.open_layout.setVisibility(View.GONE);
                    holder.openButton.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                    holder.open = false;
                } else {
                    holder.open_layout.setVisibility(View.VISIBLE);
                    holder.openButton.setImageResource(R.drawable.ic_clear_black_24dp);
                    holder.open = true;
                }
            }
        });

        if (position == stepSloveList.size() - 1)
            holder.itemView.findViewById(R.id.rect).setVisibility(View.GONE);

        holder.name.setText(stepSloveList.get(position).template);
        holder.rule.setText(stepSloveList.get(position).rule);
    }


    @Override
    public int getItemCount() {
        return stepSloveList.size();
    }
}