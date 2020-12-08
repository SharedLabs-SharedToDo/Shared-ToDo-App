package com.example.sharedto_doapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sharedto_doapp.SubTaskAdapter;
import com.example.sharedto_doapp.models.Task;

import org.parceler.Parcels;

import java.util.Arrays;
import java.util.List;

public class DetailedTaskActivity extends AppCompatActivity {

    Task task;
    SubTaskAdapter subTaskAdapter;
    ImageButton backButton;
    RecyclerView subtasksRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_task);

        task = Parcels.unwrap(getIntent().getParcelableExtra("task"));
        populateDetailedTask(task);

        backButton = findViewById(R.id.back_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void populateDetailedTask(Task task){
        TextView taskTitle = findViewById(R.id.task_title);
        subtasksRV = findViewById(R.id.sub_tasks);
        taskTitle.setText(task.getTitle());

        String subtasks = task.getSubtasks();

        if(subtasks == null) {
            subtasksRV.setVisibility(View.INVISIBLE);
        } else {
            List<String> subtaskslist = Arrays.asList(subtasks.split("\\r?\\n"));
            populateSubtasks(subtaskslist);
        }
    }

    private void populateSubtasks(List<String> subtasks){
        Log.i("Whatever", String.valueOf(subtasks));
        subTaskAdapter = new SubTaskAdapter(subtasks, this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        subtasksRV.setLayoutManager(linearLayoutManager);
        subtasksRV.setAdapter(subTaskAdapter);

    }
}