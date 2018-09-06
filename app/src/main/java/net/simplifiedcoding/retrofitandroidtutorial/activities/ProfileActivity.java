package net.simplifiedcoding.retrofitandroidtutorial.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import net.simplifiedcoding.retrofitandroidtutorial.R;
import net.simplifiedcoding.retrofitandroidtutorial.fragments.HomeFragment;
import net.simplifiedcoding.retrofitandroidtutorial.fragments.SettingsFragment;
import net.simplifiedcoding.retrofitandroidtutorial.fragments.UsersFragment;
import net.simplifiedcoding.retrofitandroidtutorial.models.User;
import net.simplifiedcoding.retrofitandroidtutorial.storage.SharedPrefManager;

public class ProfileActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FloatingActionButton btnCreatePronos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);
        navigationView.setOnNavigationItemSelectedListener(this);
        FloatingActionButton btnCreatePronos = (FloatingActionButton) findViewById(R.id.btn_createPronos);
        btnCreatePronos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCreatePronos();
            }
        });
        displayFragment(new HomeFragment());
    }

    private void displayFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.relativeLayout, fragment)
                .commit();
    }

    public void showCreatePronos() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ProfileActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog_create_pronos, null);
        dialogBuilder.setView(dialogView);

        dialogBuilder.setTitle("Créer un Pronos");
        dialogBuilder.setPositiveButton("Publier le Pronos", new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialog, int whichButton) {

                final EditText et_equipe1 = (EditText) ((AlertDialog)dialog).findViewById(R.id.et_equipe1);
                final EditText et_equipe2 = (EditText) ((AlertDialog)dialog).findViewById(R.id.et_equipe2);
                final EditText et_cote1 = (EditText) ((AlertDialog)dialog).findViewById(R.id.et_cote1);
                final EditText et_cote2 = (EditText) ((AlertDialog)dialog).findViewById(R.id.et_cote2);
                final EditText et_matchNull = (EditText) ((AlertDialog)dialog).findViewById(R.id.et_matchNull);
                final EditText et_resultat = (EditText) ((AlertDialog)dialog).findViewById(R.id.et_resultat);

                final String equipe1 = et_equipe1.getText().toString().trim();
                final String equipe2 = et_equipe2.getText().toString().trim();
                final String cote1 = et_cote1.getText().toString().trim();
                final String cote2 = et_cote2.getText().toString().trim();
                final String matchNull = et_matchNull.getText().toString().trim();
                final String resultat = et_resultat.getText().toString().trim();
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }
    @Override
    protected void onStart() {
        super.onStart();

        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;

        switch(item.getItemId()){
            case R.id.menu_home:
                fragment = new HomeFragment();
                break;
            case R.id.menu_users:
                fragment = new UsersFragment();
                break;
            case R.id.menu_settings:
                fragment = new SettingsFragment();
                break;
        }

        if(fragment != null){
            displayFragment(fragment);
        }

        return false;
    }
}
