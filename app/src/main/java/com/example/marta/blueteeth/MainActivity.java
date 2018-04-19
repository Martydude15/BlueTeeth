package com.example.marta.blueteeth;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FloatingActionButton fab, fab2, fab3;
    Animation fabOpen, fabClose, rotateForward, rotateBackward;
    boolean isOpen= false;
    
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Floating action buttons
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab3 = (FloatingActionButton) findViewById(R.id.fab3);

        //Fab open and close animations
        fabOpen = AnimationUtils.loadAnimation(this,R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(this,R.anim.fab_close);
        rotateForward = AnimationUtils.loadAnimation(this,R.anim.rotate_forward);
        rotateBackward = AnimationUtils.loadAnimation(this,R.anim.rotate_backward);


        //Opens the fab upon press
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
            }
        });

        //Short Answer Dialog
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_text_question, null);
                final EditText mQuestion = mView.findViewById(R.id.editQuestion);
                final EditText mAnswer = mView.findViewById(R.id.editAnswer);
                Button mSend = mView.findViewById(R.id.sendbtn);
                Button mCancel = mView.findViewById(R.id.cancelbtn);
               //Makes it to where a button has to be pressed to close the dialog box
                mBuilder.setCancelable(false);

                //Show the dialog box
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();
                //If a text field is empty, show an error message, else send the message and close the dialog box
                mSend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!mQuestion.getText().toString().isEmpty() && !mAnswer.getText().toString().isEmpty())
                        {
                            Toast.makeText(MainActivity.this, "Question sent", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }else{
                            Toast.makeText(MainActivity.this, "Input a question or answer.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                //Cancel button closes dialog box on press
                mCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "Question cancelled.", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
            }
        });

        //Multiple Choice Dialog
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();

                AlertDialog.Builder mBuilder2 = new AlertDialog.Builder(MainActivity.this);
                final View mView2 = getLayoutInflater().inflate(R.layout.dialog_edit_mc, null);
                final EditText mQuestion = mView2.findViewById(R.id.editQuestion2);
                final EditText mChoice1 = mView2.findViewById(R.id.choice1);
                final EditText mChoice2 = mView2.findViewById(R.id.choice2);
                final EditText mChoice3 = mView2.findViewById(R.id.choice3);
                final EditText mChoice4 = mView2.findViewById(R.id.choice4);
                Button mOk = mView2.findViewById(R.id.okbtn);
                Button mCancel2 = mView2.findViewById(R.id.cancelbtn2);
                //Makes it to where a button has to be pressed to close the dialog box
                mBuilder2.setCancelable(false);

                //Show the dialog box
                mBuilder2.setView(mView2);
                final AlertDialog dialog = mBuilder2.create();
                dialog.show();
                //If a text field is empty, show an error message, else send the message and close the dialog box
                mOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!mQuestion.getText().toString().isEmpty() && !mChoice1.getText().toString().isEmpty()&&
                         !mChoice2.getText().toString().isEmpty() && !mChoice3.getText().toString().isEmpty() && !mChoice4.getText().toString().isEmpty())
                        {
                            dialog.dismiss();


                            AlertDialog.Builder mBuilder3 = new AlertDialog.Builder(MainActivity.this);
                            View mView3 = getLayoutInflater().inflate(R.layout.dialog_mcrb_questions, null);
                            TextView mQuestion2 = mView3.findViewById(R.id.mcquestion);
                            RadioGroup mGroup1 = mView3.findViewById(R.id.rgroup1);
                            RadioButton mChoiceBtn1 = mView3.findViewById(R.id.choicerb1);
                            RadioButton mChoiceBtn2 = mView3.findViewById(R.id.choicerb2);
                            RadioButton mChoiceBtn3 = mView3.findViewById(R.id.choicerb3);
                            RadioButton mChoiceBtn4 = mView3.findViewById(R.id.choicerb4);
                            Button mSend2 = mView3.findViewById(R.id.sendbtn2);
                            Button mCancel3 = mView3.findViewById(R.id.cancelbtn3);
                            //Makes it to where a button has to be pressed to close the dialog box
                            mBuilder3.setCancelable(false);
                            //Transfers the strings from the Edit MC dialog
                            String q1 = mQuestion.getText().toString();
                            String c1 = mChoice1.getText().toString();
                            String c2 = mChoice2.getText().toString();
                            String c3 = mChoice3.getText().toString();
                            String c4 = mChoice4.getText().toString();
                            mQuestion2.setText(q1);
                            mChoiceBtn1.setText(c1);
                            mChoiceBtn2.setText(c2);
                            mChoiceBtn3.setText(c3);
                            mChoiceBtn4.setText(c4);


                            //Show the dialog box
                            mBuilder3.setView(mView3);
                            final AlertDialog dialog = mBuilder3.create();
                            dialog.show();



                            mSend2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Toast.makeText(MainActivity.this, "Question has been sent.", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            });

                            //Cancel button closes dialog box on press
                            mCancel3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Toast.makeText(MainActivity.this, "Question cancelled.", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            });
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Input a question or answer choice", Toast.LENGTH_SHORT).show();
                            }
                        }
                });

                //Cancel button closes dialog box on press
                mCancel2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "Question cancelled.", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
            }
        });



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Makes Home fragment the default

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flMain,new HomeFragment());
        ft.commit();

        navigationView.setCheckedItem(R.id.nav_home);
    }

    //Handles the fab animations
    private void animateFab()
    {
        if (isOpen)
        {
            fab.startAnimation(rotateBackward);
            fab2.startAnimation(fabClose);
            fab3.startAnimation(fabClose);
            fab2.setClickable(false);
            fab3.setClickable(false);
            isOpen = false;
        }
        else
        {
            fab.startAnimation(rotateForward);
            fab2.startAnimation(fabOpen);
            fab3.startAnimation(fabOpen);
            fab2.setClickable(true);
            fab3.setClickable(true);
            isOpen=true;
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flMain,new HomeFragment());
            ft.commit();
        } else if (id == R.id.nav_attendees) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flMain,new AttendeeFragment());
            ft.commit();
        } else if (id == R.id.nav_nonAttendees) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flMain,new NonAttendeeFragment());
            ft.commit();
        } else if (id == R.id.nav_list) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flMain,new ListFragment());
            ft.commit();
        } else if (id == R.id.nav_studentAdd) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flMain,new StudentAddFragment());
            ft.commit();
        } else if (id == R.id.nav_location) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flMain,new LocationFragment());
            ft.commit();
        } else if (id == R.id.nav_settings) {
            Intent i = new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
