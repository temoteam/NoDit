package ru.temoteam.nodit.View;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONException;

import java.io.IOException;
import java.util.Arrays;

import ru.temoteam.nodit.Code.Global;
import ru.temoteam.nodit.Code.Parser;
import ru.temoteam.nodit.Code.mrkoParser;
import ru.temoteam.nodit.R;

public class LoginActivity extends AppCompatActivity {

    private MaterialEditText login;
    private MaterialEditText pass;
    private TextView errors;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Global.initilizate(this);

        login = (MaterialEditText) findViewById(R.id.login);
        pass = (MaterialEditText) findViewById(R.id.pass);
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
        private boolean hasData = false;

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
            try {
                String data = "";
                for (int i = 1; i < 10; i++) {
                    try {
                        data = mrkoParser.Companion.parse(login, pass);
                        publishProgress(true);
                        Log.i("Try",i+"");
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                Global.lessons=Parser.parseLessons(data);
                return true;
            }  catch (JSONException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onProgressUpdate(Boolean... values) {
            super.onProgressUpdate(values);
            if (values[0]){
                hasData=true;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            loginButton.setVisibility(View.VISIBLE);
            if (aBoolean){
                Global.sharedPreferences.edit().putString(Global.SharedPreferencesTags.S_LOGIN,login).putString(Global.SharedPreferencesTags.S_PASS,pass).apply();
                Log.i("SUCCESS", Arrays.deepToString(Global.lessons));
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
            else {
                if (hasData)
                    errors.setText(R.string.incorrect_login_or_password);
                else
                    errors.setText(R.string.unknown_error);

                if (errors.getVisibility()==View.INVISIBLE)
                    errors.setVisibility(View.VISIBLE);

                Global.sharedPreferences.edit().remove(Global.SharedPreferencesTags.S_LOGIN).remove(Global.SharedPreferencesTags.S_PASS).apply();
            }
        }
    }
}
