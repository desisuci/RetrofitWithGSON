package com.desisuci.retrofitgson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    TextView textViewResult;
    ApiKontak apiKontak;

    //@BindView(R.id.txt_result) private TextView textViewResult; //cara cepat

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = (TextView) findViewById(R.id.txt_result);
        Gson gson = new GsonBuilder().serializeNulls().create();


        HttpLoggingInterceptor LoggingInterceptor = new HttpLoggingInterceptor();
        LoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(LoggingInterceptor)
                .build();

//make retrofit object for base config of retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiKontak.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
//initialize ApiKontak interface for use the methode of API
        apiKontak = retrofit.create(ApiKontak.class);
        //getAllContact();
        //getContact(1);
        //createContact();
        //deleteContact();
        putContact();
        //patchContact();
    }

    public void getAllContact(){
        Call<List<Kontak>> call = apiKontak.getAllContact();

        call.enqueue(new Callback<List<Kontak>>() {
            @Override
            public void onResponse(Call<List<Kontak>> call, Response<List<Kontak>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code Response : " + response.code());
                    return;
                }
                List<Kontak> kontaks = response.body();
                for (Kontak kontak : kontaks) {
                    String content = " ";
//                    content +="Code"+response.code()+"\n";
                    content +="ID : "+kontak.getId()+"\n";
                    content +="Nama : "+kontak.getName()+"\n";
                    content +="Email : "+kontak.getEmail()+"\n";
                    content +="No Hp : "+kontak.getPhone()+"\n";
                    content +="Alamat : "+kontak.getAddress()+"\n\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Kontak>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

        public void getContact(int id) {
        Call<Kontak> call = apiKontak.getContact(id);
        call.enqueue(new Callback<Kontak>() {
            @Override
            public void onResponse(Call<Kontak> call, Response<Kontak> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code Response : " + response.code());
                    return;
                }
                Kontak kontak = response.body();

                String content = " ";
//                content +="Code"+response.code()+"\n";
                content +="ID : "+kontak.getId()+"\n";
                content +="Nama : "+kontak.getName()+"\n";
                content +="Email : "+kontak.getEmail()+"\n";
                content +="No Hp : "+kontak.getPhone()+"\n";
                content +="Alamat : "+kontak.getAddress()+"\n\n";

                textViewResult.append(content);

            }

            @Override
            public void onFailure(Call<Kontak> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }

        });
        }

        public void createContact(){

//        Kontak kontak = new Kontak("Rifaldi Alfares","iphal212@gmail.com","089458276353",
//                "Rawa Kalong");

            Call<Kontak> call = apiKontak.saveContact("Wullan Syundari","wullanss@gmail.com",
                    "082116642209","BSD");

        //Call<Kontak> call = apiKontak.saveContact(kontak);

        call.enqueue(new Callback<Kontak>() {
            @Override
            public void onResponse(Call<Kontak> call, Response<Kontak> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code Response : " + response.code());
                    return;
                }
                Kontak kontak = response.body();

                String content = " ";
                content +="Code "+response.code()+"\n";
                content +="ID : "+kontak.getId()+"\n";
                content +="Nama : "+kontak.getName()+"\n";
                content +="Email : "+kontak.getEmail()+"\n";
                content +="No Hp : "+kontak.getPhone()+"\n";
                content +="Alamat : "+kontak.getAddress()+"\n\n";

                textViewResult.append(content);
                Log.i("response :",content);
            }

            @Override
            public void onFailure(Call<Kontak> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
        }

        public void deleteContact(){
        Call<Void> call = apiKontak.deleteContact(69);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code Response : " + response.code());
                    return;
                }
                textViewResult.setText("Code Response : " + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
        }

        public void putContact(){

        Kontak baru = new Kontak("Rifaldi Alfares","iphal212@gmail.com","089458276353",
              "Rawa Kalong");

        Call<Kontak> call = apiKontak.putContact(75,baru);
        call.enqueue(new Callback<Kontak>() {
            @Override
            public void onResponse(Call<Kontak> call, Response<Kontak> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code Response : " + response.code());
                    return;
                }
                Kontak kontak = response.body();

                String content = " ";
                content +="Code "+response.code()+"\n";
                content +="ID : "+kontak.getId()+"\n";
                content +="Nama : "+kontak.getName()+"\n";
                content +="Email : "+kontak.getEmail()+"\n";
                content +="No Hp : "+kontak.getPhone()+"\n";
                content +="Alamat : "+kontak.getAddress()+"\n\n";

                textViewResult.append(content);
                Log.i("response :",content);
            }

            @Override
            public void onFailure(Call<Kontak> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
        }

        public void patchContact(){
            Kontak akun = new Kontak("Muhammad Riyan","mriyaans@gmail.com","089458276353",
                    "Jl.Lengkong gudang timur");

            Call<Kontak> call = apiKontak.patchContact(88,akun);
            call.enqueue(new Callback<Kontak>() {
                @Override
                public void onResponse(Call<Kontak> call, Response<Kontak> response) {
                    if (!response.isSuccessful()) {
                        textViewResult.setText("Code Response : " + response.code());
                        return;
                    }
                    Kontak kontak = response.body();

                    String content = " ";
                    content +="Code "+response.code()+"\n";
                    content +="ID : "+kontak.getId()+"\n";
                    content +="Nama : "+kontak.getName()+"\n";
                    content +="Email : "+kontak.getEmail()+"\n";
                    content +="No Hp : "+kontak.getPhone()+"\n";
                    content +="Alamat : "+kontak.getAddress()+"\n\n";

                    textViewResult.append(content);
                    Log.i("response :",content);

                }

                @Override
                public void onFailure(Call<Kontak> call, Throwable t) {
                    textViewResult.setText(t.getMessage());
                }
            });
        }

    }


