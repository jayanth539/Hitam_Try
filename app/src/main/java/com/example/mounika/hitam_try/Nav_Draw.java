package com.example.mounika.hitam_try;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.PopupMenu;
import android.text.InputType;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mounika.hitam_try.BusTracking.BusActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.zip.Inflater;


public class Nav_Draw extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
 FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav__draw);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firebaseAuth = FirebaseAuth.getInstance();



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav__draw, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(final MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_About_us) {
        startActivity(new Intent(Nav_Draw.this,AboutUsActivity.class));
        } else if (id == R.id.nav_EPICS) {
            startActivity(new Intent(Nav_Draw.this,EPICSActivity.class));
        } else if (id == R.id.nav_feedback) {
            startActivity(new Intent(Nav_Draw.this,FeedbackActivity.class
            ));
        } else if (id == R.id.nav_bus) {

            final int[] m_Text = new int[1];
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Enter The Bus Route");

           // Set up the input
            final EditText input = new EditText(this);

           // Specify the type of input expected;

            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            builder.setView(input);

            // Set up the buttons
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    m_Text[0] = Integer.valueOf(String.valueOf(input.getText()));
                    if(m_Text[0] > 0 && m_Text[0]<12) {
                        int no = m_Text[0];
                        checkPassword(no);
                    }else{
                        Toast.makeText(Nav_Draw.this,"Enter Route Between 1 and 12",Toast.LENGTH_LONG).show();
                    }
                }

            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();


        } else if (id == R.id.nav_academics) {

            //startActivity(new Intent(Nav_Draw.this, BusActivity.class));

           /* PopupMenu popupMenu = new PopupMenu(this,findViewById(R.id.nav_academics));
            MenuInflater inflater = popupMenu.getMenuInflater();
            inflater.inflate(R.menu.academics_popup_menu,popupMenu.getMenu());
            popupMenu.show(); */

        }


        return true;
    }

    private void checkPassword(final int Route) {
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        final EditText passw = new EditText(this);
        passw.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_NUMBER_VARIATION_PASSWORD | InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD);

        builder2.setView(passw);

        builder2.setTitle("Enter The PassCode for Route " + Route);
        final String email = "route" + Route + "@hitam.org";
        builder2.setPositiveButton("OK",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                          firebaseAuth.signInWithEmailAndPassword(email, String.valueOf(passw.getText())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {


                              @Override
                              public void onComplete(@NonNull Task<AuthResult> task) {


                                  if(task.isSuccessful()){
                                      BusActivity activity = new BusActivity();
                                      activity.setBusRouteNumber(Route);
                                      Intent intent = new Intent(Nav_Draw.this,activity.getClass());
                                    //  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                      startActivity(intent);
                                  }else
                                  {
                                      Toast.makeText(getApplicationContext(),"The password is invalid or the user does not have a password",Toast.LENGTH_LONG).show();
                                  }

                              }
                          });
            }
        });

        builder2.show();
    }
}
