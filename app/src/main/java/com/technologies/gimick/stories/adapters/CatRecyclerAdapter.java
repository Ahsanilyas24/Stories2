package com.technologies.gimick.stories.adapters;
/*
This is adapter class for recycler view
*/
import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.technologies.gimick.stories.R;
import com.technologies.gimick.stories.activities.StoriesByCatActivity;
import com.technologies.gimick.stories.databinding.CatRowBinding;
import com.technologies.gimick.stories.models.CatDTO;

import java.util.ArrayList;


public class CatRecyclerAdapter extends RecyclerView.Adapter<CatRecyclerAdapter.CatHolder> {
    private final ArrayList<CatDTO> data;
    private final Activity activity;


    public CatRecyclerAdapter(Activity activity, ArrayList<CatDTO> data) {
        this.activity = activity;
        this.data = data;
    }

    @Override
    public CatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CatHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_row, null));
    }

    @Override
    public void onBindViewHolder(CatHolder holder, int position) {
        holder.binding.setCatDto(data.get(position));
    }

    public CatDTO getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class CatHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CatRowBinding binding;

        public CatHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            binding = DataBindingUtil.bind(itemView);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), StoriesByCatActivity.class);
            intent.putExtra("id", getItem(getAdapterPosition()).catId);
            activity.startActivity(intent);
        }
    }
}
