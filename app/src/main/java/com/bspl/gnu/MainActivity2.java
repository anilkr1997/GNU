package com.bspl.gnu;

import androidx.appcompat.app.AppCompatActivity;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bspl.gnu.emailsercice.EmailService;
import com.bspl.gnu.emailsercice.SendMail;
import com.bspl.gnu.services.WhatsappAccessibilityService;

import java.net.URLEncoder;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
EmailService mEmailService;
    //Declaring EditText
    private EditText editTextEmail;
    private EditText editTextSubject;
    private EditText editTextMessage;

    //Send button
    private Button buttonSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        mEmailService = new EmailService( mConfig.getEmailHost(), mConfig.getEmailPort() )
//                .setEnableSelfSigned( mConfig.getEmailSelfSignedCertEnabled() )
//                .setStartTls( mConfig.getEmailSTARTTLSEnabled() )
//                .setAuth( new EmailService.Authenticator( mConfig.getEmailUsername(), mConfig.getEmailPassword() ) );


        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextSubject = (EditText) findViewById(R.id.editTextSubject);
        editTextMessage = (EditText) findViewById(R.id.editTextMessage);

        buttonSend = (Button) findViewById(R.id.buttonSend);

        //Adding click listener
        buttonSend.setOnClickListener(this);

    }
    private void sendEmail() {
        //Getting content for email
        String email = editTextEmail.getText().toString().trim();
        String subject = editTextSubject.getText().toString().trim();
        String message = editTextMessage.getText().toString().trim();

        //Creating SendMail object
        SendMail sm = new SendMail(this, email, subject, message);

        //Executing sendmail to send email
        sm.execute();
    }

    @Override
    public void onClick(View v) {
       // sendEmail();

        if (!isAccessibilityOn (this, WhatsappAccessibilityService.class)) {
            Intent intent = new Intent (Settings.ACTION_ACCESSIBILITY_SETTINGS);
            this.startActivity (intent);
        }

//        PackageManager packageManager = getApplicationContext().getPackageManager();
//        Intent i = new Intent(Intent.ACTION_VIEW);


//        try {
//            String url = "https://api.whatsapp.com/send?phone=" + "+91 8601500190" + "&text=" + URLEncoder.encode("hey this is anil kumar Maurya", "UTF-8");
//            i.setPackage("com.whatsapp");
//            i.setData(Uri.parse(url));
//            if (i.resolveActivity(packageManager) != null) {
//                startActivity(i);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
    private boolean isAccessibilityOn (Context context, Class<? extends AccessibilityService> clazz) {
        int accessibilityEnabled = 0;
        final String service = context.getPackageName () + "/" + clazz.getCanonicalName ();
        try {
            accessibilityEnabled = Settings.Secure.getInt (context.getApplicationContext ().getContentResolver (), Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException ignored) {  }

        TextUtils.SimpleStringSplitter colonSplitter = new TextUtils.SimpleStringSplitter (':');

        if (accessibilityEnabled == 1) {
            String settingValue = Settings.Secure.getString (context.getApplicationContext ().getContentResolver (), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (settingValue != null) {
                colonSplitter.setString (settingValue);
                while (colonSplitter.hasNext ()) {
                    String accessibilityService = colonSplitter.next ();

                    if (accessibilityService.equalsIgnoreCase (service)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}