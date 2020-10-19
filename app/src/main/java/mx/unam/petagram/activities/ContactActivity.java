package mx.unam.petagram.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import org.apache.commons.lang3.StringUtils;

import mx.unam.petagram.Constants;
import mx.unam.petagram.helpers.JavaMailAPI;
import mx.unam.petagram.R;

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
                sendEmail();
                break;
        }
    }

    private void sendEmail() {
        String toEmail = Constants.EMAIL;
        String subject = tiSubject.getText().toString();
        String message = this.formatMessage();
        JavaMailAPI mail = new JavaMailAPI(this, toEmail, subject, message);
        mail.execute();
    }

    private String formatMessage(){
        String contactEmail = tiEmail.getText().toString();
        String message = tiMessage.getText().toString();
        return StringUtils.join(contactEmail, "\n", message);
    }

}
