package co.com.luisprmat.training.attendance.view.students;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import java.util.List;

import co.com.luisprmat.training.attendance.R;
import co.com.luisprmat.training.attendance.model.entity.Student;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.ViewHolder> {
    Context context;
    List<Student> students;

    public StudentsAdapter(Context context, List<Student> students) {
        this.context = context;
        this.students = students;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_student, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentsAdapter.ViewHolder holder, int position) {
        holder.getTvStudent().setText(students.get(position).getName());

        String course = context.getString(R.string.text_course, students.get(position).getCourse());
        holder.getTvCourse().setText(course);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private MaterialTextView tvStudent, tvCourse;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvStudent = itemView.findViewById(R.id.tvStudent);
            tvCourse = itemView.findViewById(R.id.tvCourse);
        }

        public MaterialTextView getTvStudent() {
            return tvStudent;
        }

        public MaterialTextView getTvCourse() {
            return tvCourse;
        }
    }

}
