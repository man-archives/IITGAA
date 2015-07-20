package iitgaa.summer15.manparvesh.iitgaa.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import iitgaa.summer15.manparvesh.iitgaa.R;
import iitgaa.summer15.manparvesh.iitgaa.helper.ParseUtils;
import iitgaa.summer15.manparvesh.iitgaa.helper.PrefManager;

public class LoginActivity extends ActionBarActivity implements View.OnClickListener {

    private EditText inputEmail;
    private EditText inputName;
    private EditText inputNum;
    private AutoCompleteTextView inputCountry;
    String[] country_names;

    private Button btnLogin;
    private PrefManager pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Verifying parse configuration. This is method is for developers only.
        ParseUtils.verifyParseConfiguration(this);

        pref = new PrefManager(getApplicationContext());
        if (pref.isLoggedIn()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);

            finish();
        }

        setContentView(R.layout.activity_login);

        //if using Lollipop or better version, use custom color for navigation bar
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.navigationBarColor));
        }





        inputName = (EditText) findViewById(R.id.name);
        inputName.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

        inputCountry = (AutoCompleteTextView) findViewById(R.id.country);
        country_names=getResources().getStringArray(R.array.countries);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,country_names);
        inputCountry.setThreshold(1);
        inputCountry.setAdapter(adapter);


        inputNum = (EditText) findViewById(R.id.phone);
        inputNum.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });
        inputEmail = (EditText) findViewById(R.id.email);
        inputEmail.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);
    }


    private void login() {
        String email = inputEmail.getText().toString();
        String country = inputCountry.getText().toString();
        String name = inputName.getText().toString();
        String phone= inputNum.getText().toString();

        if (isValidEmail(email)) {

            pref.createLoginSession(email,name,phone,country);

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);

            Toast.makeText(getApplicationContext(), "Logging in..", Toast.LENGTH_SHORT).show();

            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Please enter valid email address!", Toast.LENGTH_LONG).show();
        }
    }

    public final boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                login();
                break;
            default:
        }
    }
}
