package ru.temoteam.mrkotoday.View;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

import ru.temoteam.mrkotoday.Code.Day;
import ru.temoteam.mrkotoday.Code.Global;
import ru.temoteam.mrkotoday.Code.mrkoParser;
import ru.temoteam.mrkotoday.R;

public class LoginActivity extends AppCompatActivity {

    private EditText login;
    private EditText pass;
    private TextView errors;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Global.initilizate(this);

        login = (EditText) findViewById(R.id.login);
        pass = (EditText) findViewById(R.id.pass);
        errors = (TextView) findViewById(R.id.error_text);
        loginButton = (Button) findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Login(login.getText().toString(),pass.getText().toString());
            }
        });

        if (Global.sharedPreferences.contains(Global.SharedPreferencesTags.S_LOGIN)&&Global.sharedPreferences.contains(Global.SharedPreferencesTags.S_PASS)){
            login.setText(Global.sharedPreferences.getString(Global.SharedPreferencesTags.S_LOGIN,null));
            pass.setText(Global.sharedPreferences.getString(Global.SharedPreferencesTags.S_PASS,null));
            new Login(Global.sharedPreferences.getString(Global.SharedPreferencesTags.S_LOGIN,null),Global.sharedPreferences.getString(Global.SharedPreferencesTags.S_PASS,null));
        }
    }

    public class Login extends AsyncTask<Void,Boolean,Boolean>{

        private String login;
        private String pass;

        public Login(String login, String pass){
            if (login.equals("")||pass.equals("")){
                errors.setText(R.string.fields_not_filled);
                if (errors.getVisibility()==View.INVISIBLE)
                errors.setVisibility(View.VISIBLE);
            }
            else {
                this.login = login;
                this.pass = pass;
                execute();
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loginButton.setVisibility(View.INVISIBLE);
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            if(pass.length() != 32)
                pass = getMD5EncryptedString(pass);

                for (int i = 1; i < 10; i++) {
                    try {
                        Global.days = mrkoParser.Companion.parse(login,pass);
                        Iterator<Day> iterator = Global.days.iterator();
                        //Remove days without lessons
                        while (iterator.hasNext()){
                            Day d = iterator.next();
                            if (d.getLessons().length == 0)
                                iterator.remove();
                        }
                        Log.i("Try",i+"");
                        return true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                return false;
        }


        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            loginButton.setVisibility(View.VISIBLE);
            if (aBoolean){
                Global.sharedPreferences.edit().putString(Global.SharedPreferencesTags.S_LOGIN,login).putString(Global.SharedPreferencesTags.S_PASS,pass).apply();
                startActivity(new Intent(LoginActivity.this,DaysActivity.class));
            }
            else {
                errors.setText(R.string.incorrect_login_or_password);
                if (errors.getVisibility()==View.INVISIBLE)
                    errors.setVisibility(View.VISIBLE);

                Global.sharedPreferences.edit().remove(Global.SharedPreferencesTags.S_LOGIN).remove(Global.SharedPreferencesTags.S_PASS).apply();
            }

        }
    }

    private String getMD5EncryptedString(String encTarget){
        MessageDigest mdEnc = null;
        try {
            mdEnc = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Exception while encrypting to md5");
            e.printStackTrace();
        }
        mdEnc.update(encTarget.getBytes(), 0, encTarget.length());
        String md5 = new BigInteger(1, mdEnc.digest()).toString(16);
        while ( md5.length() < 32 ) {
            md5 = "0"+md5;
        }
        return md5;
    }

}
