package com.mesoraios.siswasmkn10.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mesoraios.siswasmkn10.R;
import com.mesoraios.siswasmkn10.model.LoginResponse;
import com.mesoraios.siswasmkn10.networking.ServiceClient;
import com.mesoraios.siswasmkn10.networking.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText etNIS,etPass;
    Spinner spTingkat,spTahun;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etNIS = findViewById(R.id.et_nis);
        etPass = findViewById(R.id.et_pass);
        spTingkat = findViewById(R.id.sp_tingkatan);
        spTahun = findViewById(R.id.sp_tahunAjaran);

        pd = new ProgressDialog(this);
    }

    public void login(View view){
        pd.setMessage("Login...");
        pd.setCancelable(false);
        pd.show();

        if(etNIS.getText().toString().isEmpty()){
            pd.dismiss();
            Toast.makeText(this, "NIS tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if(etPass.getText().toString().isEmpty()){
            pd.dismiss();
            Toast.makeText(this, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        String nis = etNIS.getText().toString().trim();
        String pass = etPass.getText().toString().trim();
        String tingkatan = spTingkat.getSelectedItem().toString();
        String tahun = spTahun.getSelectedItem().toString();

        ServiceClient service = ServiceGenerator.createService(ServiceClient.class);
        Call<LoginResponse> requestLogin = service.loginSiswa("loginSiswa","login",tingkatan,tahun,nis,pass);
        requestLogin.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                pd.dismiss();
                if (response.body().getHasil().equals("success")){
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "Login gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable throwable) {
                pd.dismiss();
                Toast.makeText(LoginActivity.this, "Koneksi Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}