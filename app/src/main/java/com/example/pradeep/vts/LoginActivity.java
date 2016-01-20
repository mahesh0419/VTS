package com.example.pradeep.vts;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    Button mLogin;
    EditText mName;
    EditText mVehicleId;
    EditText mCustomerId;
    TextInputLayout mTimage,mTname,mTcustomerId;
    Toolbar toolbar;
    String name,custid;
    public static final String LOGIN_URL = "http://simplifiedcoding.16mb.com/UserRegistration/volleyLogin.php";

    public static final String KEY_USERNAME="username";
    public static final String KEY_PASSWORD="password";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        toolbar= (Toolbar) findViewById(R.id.toolbar);
        //TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        setSupportActionBar(toolbar);
        getSupportActionBar().show();




        mTname = (TextInputLayout) findViewById(R.id.input_layout_name);
        mTcustomerId = (TextInputLayout) findViewById(R.id.input_layout_customer_id);



       mName= (EditText) findViewById(R.id.input_name);
        mCustomerId= (EditText) findViewById(R.id.input_customer_id);


        mName.getBackground().setColorFilter(getResources().getColor(R.color.color), PorterDuff.Mode.SRC_ATOP);
        mCustomerId.getBackground().setColorFilter(getResources().getColor(R.color.color), PorterDuff.Mode.SRC_ATOP);


        mLogin= (Button) findViewById(R.id.btn_login);


        mName.addTextChangedListener(new MyTextWatcher(mName));
        mCustomerId.addTextChangedListener(new MyTextWatcher(mCustomerId));



        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();

                submitForm();
            }
        });

    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
    }*/
    private void userLogin() {
        name = mName.getText().toString().trim();
        custid = mCustomerId.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success")){
                            openProfile();
                        }else{
                            Toast.makeText(LoginActivity.this,response,Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                //map.put(KEY_USERNAME,username);
               // map.put(KEY_PASSWORD,password);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void openProfile(){
        //Intent intent = new Intent(this, ActivityUserProfile.class);
       // intent.putExtra(KEY_USERNAME, username);
       // startActivity(intent);
    }
    private boolean validateName() {
        if (mName.getText().toString().trim().isEmpty()) {
            mTname.setError(getString(R.string.err_msg_name));
            requestFocus(mName);
            return false;
        } else {
            mTname.setErrorEnabled(false);
        }

        return true;
    }



    private boolean validateNumber() {
        if (mCustomerId.getText().toString().trim().isEmpty()) {
            mTcustomerId.setError(getString(R.string.err_msg_password));
            requestFocus(mCustomerId);
            return false;
        } else {
            mTcustomerId.setErrorEnabled(false);
        }


        return true;
    }



    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }



    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_name:
                    validateName();
                    break;
                case R.id.input_customer_id:
                    validateNumber();
                    break;


            }
        }
    }


    private void submitForm() {
        if (!validateName()) {
            return;
        }

        if (!validateNumber()) {
            return;
        }





        Toast.makeText(getApplicationContext(), "Login Sucessfully !", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(LoginActivity.this,MenuActivity.class);
        startActivity(intent);
    }

}
