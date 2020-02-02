package com.example.gymproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class MyWorkoutAdapter extends RecyclerView.Adapter<MyWorkoutAdapter.MyWorkoutViewHolder> {

    private List<Workout> workouts;

    public MyWorkoutAdapter(List<Workout> workouts) {
        this.workouts = workouts;
    }

    public class MyWorkoutViewHolder extends RecyclerView.ViewHolder{

        TextView workoutName;
        TextView workoutSubtext;
        ImageView workoutImg;

        public MyWorkoutViewHolder(@NonNull View itemView) {
            super(itemView);

            workoutName = itemView.findViewById(R.id.workout_name);
            workoutSubtext = itemView.findViewById(R.id.workout_sub_text);
            workoutImg =  itemView.findViewById(R.id.workout_img);
        }
    }

    @NonNull
    @Override
    public MyWorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_card_layout,parent,false);
        MyWorkoutViewHolder MyWorkoutViewHolder = new MyWorkoutViewHolder(view);

        return MyWorkoutViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyWorkoutViewHolder holder, int position) {

        Workout workout = workouts.get(position);
        holder.workoutName.setText(workout.getName());
      //  holder.workoutSubtext.setText(workout.getSubText());
        holder.workoutImg.setImageResource(workout.getImgId());

    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }
}
