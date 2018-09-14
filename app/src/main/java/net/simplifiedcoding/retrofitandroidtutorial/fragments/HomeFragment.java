package net.simplifiedcoding.retrofitandroidtutorial.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import net.simplifiedcoding.retrofitandroidtutorial.R;
import net.simplifiedcoding.retrofitandroidtutorial.RecyclerViewClickListener;
import net.simplifiedcoding.retrofitandroidtutorial.activities.MainActivity;
import net.simplifiedcoding.retrofitandroidtutorial.adapters.PronosAdapter;
import net.simplifiedcoding.retrofitandroidtutorial.api.RetrofitClient;
import net.simplifiedcoding.retrofitandroidtutorial.models.DefaultResponse;
import net.simplifiedcoding.retrofitandroidtutorial.models.Pronos;
import net.simplifiedcoding.retrofitandroidtutorial.models.PronosResponse;
import net.simplifiedcoding.retrofitandroidtutorial.models.SelectPronosResponse;
import net.simplifiedcoding.retrofitandroidtutorial.models.User;
import net.simplifiedcoding.retrofitandroidtutorial.storage.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment{

    private RecyclerView recyclerViewp;
    private PronosAdapter adapter;
    private List<Pronos> pronosList;
    private String statut;
    private String newline = System.getProperty("line.separator");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pronos_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewp = view.findViewById(R.id.recyclerViewp);
        recyclerViewp.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerViewp.getContext(),DividerItemDecoration.HORIZONTAL);
        recyclerViewp.addItemDecoration(dividerItemDecoration);
        Call<PronosResponse> call = RetrofitClient.getInstance().getApi().getPronos();

        call.enqueue(new Callback<PronosResponse>() {
            @Override
            public void onResponse(Call<PronosResponse> call, Response<PronosResponse> response) {
                pronosList = response.body().getPronos();

                RecyclerViewClickListener listener = (view, position) -> {
                    switch (pronosList.get(position).getStatut()){
                        case "gagne" :
                            statut = "Ce prono est gagné";
                            break;
                        case "perdu" :
                            statut = "Ce prono est perdu";
                            break;
                        default:
                            statut = "Le statut de ce Prono sera mis à jour à la fin du match";
                            break;
                    }
                    if(view instanceof Button){
                        User user = SharedPrefManager.getInstance(getActivity()).getUser();
                        Call<SelectPronosResponse> callCreateSelectPronos = RetrofitClient.getInstance().getApi().createSelectPronos(pronosList.get(position).getId(),user.getId());
                        callCreateSelectPronos.enqueue(new Callback<SelectPronosResponse>() {
                            @Override
                            public void onResponse(Call<SelectPronosResponse> call, Response<SelectPronosResponse> response) {
                                if (response.code() == 201) {
                                    Toast.makeText(getContext(), "Pronos sélectionné", Toast.LENGTH_LONG).show();

                                } else if (response.code() == 422) {
                                    Toast.makeText(getContext(), "Ce pronostic a déjà été sélectionné", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<SelectPronosResponse> call, Throwable t) {

                            }
                        });
                    }
                    else {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                        builder1.setMessage("Résultat : " + pronosList.get(position).getResultat() + newline + newline + statut);
                        builder1.setCancelable(true);
                        builder1.setPositiveButton(
                                "Vu",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                    }
                };
                adapter = new PronosAdapter(getActivity(), pronosList,listener);
                recyclerViewp.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<PronosResponse> call, Throwable t) {

            }
        });
    }
}