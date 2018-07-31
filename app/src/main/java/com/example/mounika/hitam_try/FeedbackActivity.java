package com.example.mounika.hitam_try;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedbackActivity extends AppCompatActivity {
    private Spinner Designation;
    private EditText feedback_Type;
    private EditText name_of_person;
    private EditText Comp_desc;
    private RatingBar rating;
    private DatabaseReference dbreference;
    private Button sendManualFeedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        findViewById(R.id.btn_feedback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFeedback();
            }
        });

        sendManualFeedback = (Button)findViewById(R.id.manualButton);
        dbreference = FirebaseDatabase.getInstance().getReference("Feedbacks");
        dbreference.setValue("Hi,Hello");
        feedback_Type = (EditText)findViewById(R.id.feedback_type);
        Designation = (Spinner)findViewById(R.id.designation);
        Comp_desc = (EditText)findViewById(R.id.complaint);
        name_of_person = (EditText)findViewById(R.id.name_of_person);
        rating =(RatingBar)findViewById(R.id.ratingBar);
        sendManualFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnSubmit();
            }
        });
        
    }

    private void OnSubmit() {
        String desc = Comp_desc.getText().toString().trim();
        String desgn = Designation.getSelectedItem().toString();
        String name = name_of_person.getText().toString().trim();
        String TypeOfFeedback = feedback_Type.getText().toString().trim();
        Float ratingResult = rating.getRating();

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(desc) && !TextUtils.isEmpty(desgn)) {

            String id = dbreference.push().getKey();

            FeedbackObject Feedback = new FeedbackObject(desgn,TypeOfFeedback,name,desc,ratingResult);
            dbreference.child(id).setValue(Feedback);

            Toast.makeText(this,"Your Feedback Has been Submitted,Thank You",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"You Should Fill all the fields",Toast.LENGTH_LONG).show();

        }
    }

    private void sendFeedback() {
        final Intent _Intent = new Intent(android.content.Intent.ACTION_SEND);
        _Intent.setType("text/html");
        _Intent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{ getString(R.string.mail_feedback_email) });
        _Intent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.mail_feedback_subject));
        _Intent.putExtra(android.content.Intent.EXTRA_TEXT, getString(R.string.mail_feedback_message));
        startActivity(Intent.createChooser(_Intent, getString(R.string.title_send_feedback)));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav__draw, menu);
        return true;
    }



}
