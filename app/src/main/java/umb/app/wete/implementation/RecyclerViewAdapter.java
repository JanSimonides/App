package umb.app.wete.implementation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


import umb.app.wete.R;
import umb.app.wete.SolvingActivity;
import umb.app.wete.model.Problem;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private Context context;
    private List<Problem> ProblemList;

    public RecyclerViewAdapter(Context context, List<Problem> ProblemList) {
        this.context = context;
        this.ProblemList = ProblemList;
    }

    public RecyclerViewAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Problem problem = ProblemList.get(position);
        holder.rowName.setText(problem.getName());
        if (problem.isResolved()){
           holder.rowArea.setText(context.getResources().getString(R.string.solved));
            holder.rowArea.setTextColor(Color.GREEN);
        }
        else
        {
            holder.rowArea.setText(context.getResources().getString(R.string.unsolved));
            holder.rowArea.setTextColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return ProblemList.size();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Problem> getProblemList() {
        return ProblemList;
    }

    public void setProblemList(List<Problem> ProblemList) {
        this.ProblemList = ProblemList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView rowName;
        public TextView rowArea;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            rowName = itemView.findViewById(R.id.rowName);
            rowArea = itemView.findViewById(R.id.rowArea);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Problem problem = ProblemList.get(position);
            Intent intent = new Intent(context, SolvingActivity.class);
            intent.putExtra("id",problem.getId());
            intent.putExtra("question", problem.getQuestion());
            intent.putExtra("solution", problem.getSolution());
            intent.putExtra("solved", problem.isResolved());
            intent.putExtra("name", problem.getName());
            context.startActivity(intent);
            notifyDataSetChanged();
        }

    }
}
