package net.simplifiedcoding.retrofitandroidtutorial.activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import net.simplifiedcoding.retrofitandroidtutorial.R;
import net.simplifiedcoding.retrofitandroidtutorial.api.RetrofitClient;
import net.simplifiedcoding.retrofitandroidtutorial.models.SelectPronos;
import net.simplifiedcoding.retrofitandroidtutorial.models.SelectPronosResponse;
import net.simplifiedcoding.retrofitandroidtutorial.models.User;
import net.simplifiedcoding.retrofitandroidtutorial.storage.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatsActivity extends AppCompatActivity {

    private ProgressBar pb_gains, pb_pertes;
    private int pb_pertes_compteur = 0, pb_gains_compteur = 0, i = 0;
    private List<SelectPronos> selectPronosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        pb_gains = (ProgressBar) findViewById(R.id.pb_gains);
        pb_pertes = (ProgressBar) findViewById(R.id.pb_pertes);

        User user = SharedPrefManager.getInstance(this).getUser();

        Call<SelectPronosResponse> callSelectPronos = RetrofitClient.getInstance().getApi().getSelectPronos(user.getId());

        callSelectPronos.enqueue(new Callback<SelectPronosResponse>() {
            @Override
            public void onResponse(Call<SelectPronosResponse> callSelectPronos, Response<SelectPronosResponse> response) {
                selectPronosList = response.body().getSelectPronos();
                for(SelectPronos p : selectPronosList){
                    i++;
                    if(p.getStatut() == "gagne"){
                        pb_gains_compteur++;
                        Log.d("NB GAINS : ", String.valueOf(pb_gains_compteur));
                    }
                    else if(p.getStatut() == "perdu"){
                        pb_pertes_compteur++;
                        Log.d("NB PERTES : ", String.valueOf(pb_pertes_compteur));
                    }
                }
                pb_gains.setBackgroundColor(Color.GREEN);
                pb_pertes.setBackgroundColor(Color.RED);
                pb_gains.setProgress(pb_gains_compteur);
                pb_pertes.setProgress(pb_pertes_compteur);

            }
            @Override
            public void onFailure(Call<SelectPronosResponse> callSelectPronos, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}
