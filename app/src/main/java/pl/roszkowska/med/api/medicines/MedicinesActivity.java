package pl.roszkowska.med.api.medicines;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import pl.roszkowska.med.MyMedicinesApplication;
import pl.roszkowska.med.R;
import pl.roszkowska.med.api.service.MedicinesService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicinesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MedicinesAdapter medicinesAdapter;
    List<Medicines> medicinesList;
    private MyMedicinesApplication myMedicinesApplication;
    private MedicinesService medicinesService;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicines);

        medicinesList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewListOfMedicines);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        medicinesList.add(new Medicines("nana1","na","nanana","nanan"));
        medicinesList.add(new Medicines("nana2","na","nanana","nanan"));
        medicinesList.add(new Medicines("nana3","na","nanana","nanan"));
        medicinesList.add(new Medicines("nana4","na","nanana","nanan"));
        medicinesList.add(new Medicines("nana5","na","nanana","nanan"));
        medicinesList.add(new Medicines("nana6","na","nanana","nanan"));

        medicinesAdapter = new MedicinesAdapter(this,medicinesList);
        recyclerView.setAdapter(medicinesAdapter);


        ItemTouchHelper.SimpleCallback item = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                medicinesAdapter.notifyItemRemoved(position);
                medicinesList.remove(position);
                //todo trzeba wziac ten obiekt  i przeniesc do mojej apteki
                //todo w tej klasie zakomentowalam twoja metode download wiec to odkomunetuj i zakomentuj medicinesList.add...
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(item);
        itemTouchHelper.attachToRecyclerView(recyclerView);


        //downloadMedicines();
    }

    /*private void downloadMedicines() {
        myMedicinesApplication = (MyMedicinesApplication)getApplication();
        medicinesService = myMedicinesApplication.getMedicinesService();

        Call<List<Medicines>> repo = medicinesService.getMedicines(myMedicinesApplication.getToken().getTokenID());

        repo.enqueue(new Callback<List<Medicines>>() {
            @Override
            public void onResponse(Call<List<Medicines>> call, Response<List<Medicines>> response) {
                for(int i=0; i<response.body().size();i++) {
                    Log.d("TAG", response.body().get(i).toString());
                }
                medicinesAdapter.setMedicinesList(response.body());

            }

            @Override
            public void onFailure(Call<List<Medicines>> call, Throwable t) {

            }
        });

    }*/
}
