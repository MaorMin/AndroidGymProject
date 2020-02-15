package com.example.gymproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class WorkoutExerciseAdapter extends RecyclerView.Adapter<WorkoutExerciseAdapter.WorkoutExerciseViewHolder> {

    private List<Exercise> exercises;
    private WorkoutExerciseListener listener;

    interface WorkoutExerciseListener{
        void onCollapseClickListener(LinearLayout layout, ImageView arrowDown, ImageView arrowUp);
        void onExpendClickListener(LinearLayout layout, ImageView arrowDown, ImageView arrowUp);
        void onWorkoutExerciseLongClicked(int position, View view, ImageView recycleBin);
        void onDeleteListener(int position);
        void onClickListener(ImageView img);

    }

    public void setListener(WorkoutExerciseListener listener){
        this.listener = listener;
    }

    public WorkoutExerciseAdapter(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public class WorkoutExerciseViewHolder extends RecyclerView.ViewHolder{

        TextView exeName;
        TextView bodyPart;
        TextView weight;
        TextView sets;
        TextView reps;
        ImageView exeImg;
        ImageView expandCard;
        ImageView collapseCard;
        LinearLayout expandableLayout;
        ImageView recycleBin;

        public WorkoutExerciseViewHolder(@NonNull View itemView) {
            super(itemView);

            exeName = itemView.findViewById(R.id.workout_exercise_name);
            bodyPart = itemView.findViewById(R.id.workout_exercise_body_part);
            weight = itemView.findViewById(R.id.exercise_weight);
            sets = itemView.findViewById(R.id.exercise_sets);
            reps = itemView.findViewById(R.id.exercise_reps);
            exeImg =  itemView.findViewById(R.id.workout_exercise_img);
            expandCard = itemView.findViewById(R.id.exercise_expand_btn);
            collapseCard = itemView.findViewById(R.id.exercise_collapse_btn);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);
            recycleBin = itemView.findViewById(R.id.recycle_bin);


            expandCard.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    if (listener != null)
                        listener.onExpendClickListener(expandableLayout, expandCard, collapseCard);
                }
            });

            collapseCard.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    if (listener != null)
                        listener.onCollapseClickListener(expandableLayout, expandCard, collapseCard);
                }
            });


            itemView.setOnLongClickListener(new View.OnLongClickListener(){

                @Override
                public boolean onLongClick(View view) {
                    if (listener != null)
                        listener.onWorkoutExerciseLongClicked(getAdapterPosition(), view, recycleBin);
                    return true;
                }
            });

            recycleBin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        listener.onDeleteListener(getAdapterPosition());
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        listener.onClickListener(recycleBin);
                    }
                }
            });


        }
    }

    @NonNull
    @Override
    public WorkoutExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_exercise_layout,parent,false);
        WorkoutExerciseViewHolder WorkoutExerciseViewHolder = new WorkoutExerciseViewHolder(view);

        return WorkoutExerciseViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutExerciseViewHolder holder, int position) {

        Exercise exercise = exercises.get(position);
        holder.exeName.setText(exercise.getName());
        holder.exeImg.setImageResource(exercise.getExeImgId());
        holder.bodyPart.setText(exercise.getBodyPart());
        holder.weight.setText("Weight:" + exercise.getWeight() + "KG");
        holder.sets.setText("Sets:" + exercise.getSets());
        holder.reps.setText("Reps:" + exercise.getReps());

    }



    @Override
    public int getItemCount() {
        return exercises.size();
    }
}
