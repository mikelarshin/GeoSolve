package com.example.geosolve.ActivityFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.geosolve.R;
import com.example.geosolve.RecycleAdapter;

public class StepListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler);

        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(new RecycleAdapter(view.getContext()));

        return view;
    }
}
