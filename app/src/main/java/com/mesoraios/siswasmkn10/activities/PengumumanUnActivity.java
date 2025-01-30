package com.mesoraios.siswasmkn10.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mesoraios.siswasmkn10.R;
import com.mesoraios.siswasmkn10.model.UNResponse;
import com.mesoraios.siswasmkn10.networking.ServiceClient;
import com.mesoraios.siswasmkn10.networking.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PengumumanUnActivity extends AppCompatActivity {

    TextView tvNama,tvNISN;
    TextView tvMath,tvIndo,tvEnglish,tvKejuruan;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pengumuman_un);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvNama = findViewById(R.id.tv_un_nama);
        tvNISN = findViewById(R.id.tv_un_nisn);

        tvMath = findViewById(R.id.tv_un_math);
        tvIndo = findViewById(R.id.tv_un_indo);
        tvEnglish = findViewById(R.id.tv_un_english);
        tvKejuruan = findViewById(R.id.tv_un_kejuruan);

        pd = new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();

        String nis = getSharedPreferences("session",MODE_PRIVATE).getString("nis","");

        ServiceClient service = ServiceGenerator.createService(ServiceClient.class);
        Call<UNResponse> requestUN = service.readHasilUN("un","readHasilUN",nis);

        requestUN.enqueue(new Callback<UNResponse>() {
            @Override
            public void onResponse(Call<UNResponse> call, Response<UNResponse> response) {
                pd.dismiss();
                if (response.body().getHasilUN().equals("Tidak ditemukan")){
                    Toast.makeText(PengumumanUnActivity.this,"Data tidak ditemukan",Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    tvNama.setText(String.valueOf(response.body().getHasilUN().getName()));
                    tvNISN.setText(String.valueOf(response.body().getHasilUN().getNISN()));
                    tvMath.setText(String.valueOf(response.body().getHasilUN().getMath()));
                    tvIndo.setText(String.valueOf(response.body().getHasilUN().getIndo()));
                    tvEnglish.setText(String.valueOf(response.body().getHasilUN().getEnglish()));
                    tvKejuruan.setText(String.valueOf(response.body().getHasilUN().getKejuruan()));
                }
            }

            @Override
            public void onFailure(Call<UNResponse> call, Throwable throwable) {
                pd.dismiss();
                Toast.makeText(PengumumanUnActivity.this,""+ throwable.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}