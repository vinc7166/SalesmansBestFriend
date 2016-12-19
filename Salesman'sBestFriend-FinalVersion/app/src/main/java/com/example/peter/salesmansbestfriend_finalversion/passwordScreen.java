package com.example.peter.salesmansbestfriend_finalversion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.util.Formatter;

public class passwordScreen extends Activity {
    String password, storedPassword, encryptedPassword, finalPassword;
    File f;
    Context context;
    EditText p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_screen);
        try {
            f = new File(this.getExternalFilesDir(null), "SalesBFPasswordHash.txt");
            FileInputStream instr = new FileInputStream(f);
            finalPassword="";
            char c;
            int i;
            do {
                i=instr.read();
                if(i != -1){
                    c = (char)i;
                    finalPassword+=c;
                }
            }while(i != -1);
            instr.close();
        }catch(Exception E){}

        p = (EditText)findViewById(R.id.edtPassword);
        View.OnClickListener O = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password = p.getText().toString();
                context = view.getContext();

                if(f.length()==0) {
                    try {
                        f = new File(context.getExternalFilesDir(null), "SalesBFPasswordHash.txt");
                        FileOutputStream outstr = new FileOutputStream(f, false);
                        outstr.write(password.getBytes());
                        outstr.close();
                        Toast.makeText(view.getContext(), "Password has been set.  Please close the app and re-enter your new password.", Toast.LENGTH_LONG).show();
                    } catch (Exception E) {}
                }
                else{
                    try {
                        storedPassword = encrypt(password);
                        encryptedPassword = encrypt(finalPassword);
                    }catch (Exception E){}

                    if(storedPassword.equals(encryptedPassword)){
                        Intent I = new Intent(context, employeeList.class);
                        startActivity(I);
                        finish();
                    }
                    else{
                        Toast.makeText(context, "Password is incorrect", Toast.LENGTH_LONG).show();
                    }
                }
            }
        };
        Button B = (Button)findViewById(R.id.btnSubmit);
        B.setOnClickListener(O);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_password_screen, menu);
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

    public static String encrypt(String x) throws Exception{
        String sha1 = "";
        MessageDigest d = MessageDigest.getInstance("SHA-1");
        d.reset();
        d.update(x.getBytes("UTF-8"));
        sha1 = byteToHex(d.digest());
        return sha1;
    }

    private static String byteToHex(final byte[] hash)
    {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}

