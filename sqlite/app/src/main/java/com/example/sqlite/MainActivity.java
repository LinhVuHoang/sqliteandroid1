package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
private DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelper(this);
      //  insertCredentital();
        getAllCredential();
        updateCredentital();
        deleteCredential();
    }


    private void getAllCredential() {
        List<Credential> credentialList = db.getAllCredential();
        for(Credential credential:credentialList){
            Log.d("Credential","access_token: "+credential.getAccess_token()+ "| id:" +credential.getId());
        }
    }

    private void insertCredentital() {
        Credential credential =  new Credential("DaEhvF70NMAd2Itk4VIZia33cazkdDjp2OsJIzMDzcWY4eti2yjy81oj4z61gdsqKzzsWmoQj0SAFsRA1649075538",
                "lEzdMC5m5Cwk6I7YdJ7QSv1T1wp46kygwiVxNaOGfNQN2BKNfQkYvAEFsAvb73SbRHcrfgmpg3Ni2VHX1649075538",
                2,
                "2022-04-11T12:32:18.460428Z",
                "2022-04-04T12:32:18.000000Z",
                "2022-04-04T12:32:18.000000Z",
                1);
        String message  =db.insertDB(credential);
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
    private void updateCredentital(){
        Credential credential = new Credential();
        credential.setId(2);
        credential.setAccess_token("linhvuhoang2");
        credential.setRefresh_token("update refresh_token");
        String message = db.updateCredential(credential);
        List<Credential> credentialList = db.getAllCredential();
        for(Credential credential1:credentialList){
            Log.d("Credential","access_token: "+credential1.getAccess_token()+ "| id:" +credential1.getId());
        }
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    private void deleteCredential() {
        db.deleteCredential(1);
        List<Credential> credentialList = db.getAllCredential();
        for(Credential credential1:credentialList){
            Log.d("Credential","access_token: "+credential1.getAccess_token()+ "| id:" +credential1.getId());
        }
    }
@Override
    protected void onStop(){
        super.onStop();
        db.close();
}
}