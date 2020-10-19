package mx.unam.petagram.activities;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;

import mx.unam.petagram.R;
import mx.unam.petagram.helpers.Mail;


public class ContactActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar myActionbar;
    private TextView titleActionBar;
    private EditText tiName;
    private EditText tiEmail;
    private EditText tiSubject;
    private EditText tiMessage;
    private Button btnSendEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        //obtener el action bar
        myActionbar = (Toolbar) findViewById(R.id.miActionBarContact);
        setSupportActionBar(myActionbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //Cambiar el mensaje del action bar del por defecto al de esta actividad
        titleActionBar = (TextView) findViewById(R.id.tvTBtitulo);
        titleActionBar.setText(getString(R.string.title_actionBar_contact));
        //habiliatar la navegacion de regreso al padre
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tiName = (EditText) findViewById(R.id.etName);
        tiEmail = (EditText) findViewById(R.id.etMail);
        tiSubject = (EditText) findViewById(R.id.etSubject);
        tiMessage = (EditText) findViewById(R.id.etMessage);

        btnSendEmail = (Button) findViewById(R.id.btnSendEmail);
        btnSendEmail.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.btnSendEmail:
                EnviarEmail();
                break;
        }
    }

    private void EnviarEmail() {
        String[] recipients = { tiEmail.getText().toString() };
        SendEmailAsyncTask email = new SendEmailAsyncTask();
        email.activity = this;
        String user = getResources().getString(R.string.user);
        String password = getResources().getString(R.string.pasword);
        email.m = new Mail(user, password);
        email.m.set_from(user);
        email.m.setBody(tiMessage.getText().toString());
        email.m.set_to(recipients);
        email.m.set_subject(tiName.getText().toString() + " - " +
                tiSubject.getText().toString());
        email.execute();
    }

    public void displayMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("StaticFieldLeak")
    private class SendEmailAsyncTask extends AsyncTask<Void, Void, Boolean> {
        Mail m;
        ContactActivity activity;

        public SendEmailAsyncTask() {
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                m.send();
                return true;
            } catch (Exception e) {
                activity.displayMessage(e.getMessage());
                return true;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(aBoolean){
                activity.displayMessage(getResources().getString(R.string.message_sent));
                tiName.setText("");
                tiEmail.setText("");
                tiSubject.setText("");
                tiMessage.setText("");
            }else{
                activity.displayMessage(getResources().getString(R.string.message_error));
            }
        }
    }
}
