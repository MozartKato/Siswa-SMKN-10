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
import com.mesoraios.siswasmkn10.model.SppResponse;
import com.mesoraios.siswasmkn10.networking.ServiceClient;
import com.mesoraios.siswasmkn10.networking.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SPPDetailActivity extends AppCompatActivity {

    TextView tvTahunAjaran;
    TextView tvJuli,tvAgustus,tvSeptember,tvOktober,tvNovember,tvDesember,tvJanuari,tvFebruari,tvMaret,tvApril,tvMei,tvJuni;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sppdetail);

        tvTahunAjaran = findViewById(R.id.tv_detail_spp_tahun_ajaran);
        tvJuli = findViewById(R.id.tv_ket_spp_juli);
        tvAgustus = findViewById(R.id.tv_ket_spp_agustus);
        tvSeptember = findViewById(R.id.tv_ket_spp_september);
        tvOktober = findViewById(R.id.tv_ket_spp_oktober);
        tvNovember = findViewById(R.id.tv_ket_spp_november);
        tvDesember = findViewById(R.id.tv_ket_spp_desember);
        tvJanuari = findViewById(R.id.tv_ket_spp_januari);
        tvFebruari = findViewById(R.id.tv_ket_spp_februari);
        tvMaret = findViewById(R.id.tv_ket_spp_maret);
        tvApril = findViewById(R.id.tv_ket_spp_april);
        tvMei = findViewById(R.id.tv_ket_spp_mei);
        tvJuni = findViewById(R.id.tv_ket_spp_juni);

        pd = new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();

        String tingkatan = getSharedPreferences("session",MODE_PRIVATE).getString("tingkatan","");
        String tahunAjaran = getSharedPreferences("session",MODE_PRIVATE).getString("tahunAjaran","");
        String kelas = getSharedPreferences("session",MODE_PRIVATE).getString("kelas","");
        String nis = getSharedPreferences("session",MODE_PRIVATE).getString("nis","");

        ServiceClient service = ServiceGenerator.createService(ServiceClient.class);
        Call<SppResponse> requestSpp = service.readSpp("loginSiswa","readSpp",tingkatan,tahunAjaran,kelas,nis);

        requestSpp.enqueue(new Callback<SppResponse>() {
            @Override
            public void onResponse(Call<SppResponse> call, Response<SppResponse> response) {
                pd.dismiss();
                if (!response.body().getHasil().getJuli().isEmpty()){
                    tvTahunAjaran.setText(tahunAjaran);
                    tvJuli.setText(response.body().getHasil().getJuli());
                    tvAgustus.setText(response.body().getHasil().getAgustus());
                    tvSeptember.setText(response.body().getHasil().getSeptember());
                    tvOktober.setText(response.body().getHasil().getOktober());
                    tvNovember.setText(response.body().getHasil().getNovember());
                    tvDesember.setText(response.body().getHasil().getDesember());
                    tvJanuari.setText(response.body().getHasil().getJanuari());
                    tvFebruari.setText(response.body().getHasil().getFebruari());
                    tvMaret.setText(response.body().getHasil().getMaret());
                    tvApril.setText(response.body().getHasil().getApril());
                    tvMei.setText(response.body().getHasil().getMei());
                    tvJuni.setText(response.body().getHasil().getJuni());
                }
            }

            @Override
            public void onFailure(Call<SppResponse> call, Throwable throwable) {
                pd.dismiss();
                Toast.makeText(SPPDetailActivity.this,""+ throwable.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}