package pl.roszkowska.med.api.medicines;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pl.roszkowska.med.MyMedicinesApplication;
import pl.roszkowska.med.R;
import pl.roszkowska.med.api.myPharmacy.MyPharmacyDB;
import pl.roszkowska.med.api.myPharmacy.MyPharmacyDBAdapter;
import pl.roszkowska.med.api.service.MedicinesService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The class responsible for displaying the drug database.
 * The possibility of adding the selected drug to your own first aid kit
 */

public class MedicinesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MedicinesAdapter medicinesAdapter;
    List<Medicines> medicinesList;
    private List<String> nameList;
    private List<String> idList;
    private MyMedicinesApplication myMedicinesApplication;
    private MedicinesService medicinesService;
    private Medicines medicines;
    private MyPharmacyDB myPharmacyDB;
    String idMed;


    /**
     * the method that displays activity_medicines layout
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicines);

        medicinesList = new ArrayList<>();
        idList = new ArrayList<>();
        nameList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewListOfMedicines);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        medicinesAdapter = new MedicinesAdapter(this, medicinesList);

        recyclerView.setAdapter(medicinesAdapter);


        medicinesAdapter.setOnItemClickListener(new MedicinesAdapter.OnItemClickListener() {
            /**
             * The method passes parameters, retrieved from the database,
             * which are displayed in the form of tiles in the application window
             * @param position is the position of the medicines in recyclerView
             */
            @Override
            public void onItemClick(int position) {
                String medicinesName = medicinesAdapter.getMedicinesList().get(position).getMedicinesName();
                String composition = medicinesAdapter.getMedicinesList().get(position).getComposition();
                String formOfTheDrag = medicinesAdapter.getMedicinesList().get(position).getFormOfTheDrag();
                String category = medicinesAdapter.getMedicinesList().get(position).getCategory();
                String speciality = medicinesAdapter.getMedicinesList().get(position).getSpeciality();
                String activity = medicinesAdapter.getMedicinesList().get(position).getActivity();
                String indications = medicinesAdapter.getMedicinesList().get(position).getIndications();
                String wayOfGiving = medicinesAdapter.getMedicinesList().get(position).getWayOfGiving();
                String possibleSideEffect = medicinesAdapter.getMedicinesList().get(position).getPossibleSideEffect();
                String dose = medicinesAdapter.getMedicinesList().get(position).getDose();
                String IsPrescription = medicinesAdapter.getMedicinesList().get(position).getIsPrescription();

                medicinesAdapter.notifyItemChanged(position);

                /**
                 * start a new activity with addition of parameters
                 */
                Intent showDetailsMedicinesIntent = new Intent(MedicinesActivity.this, ShowDetailsMedicinesActivity.class);

                showDetailsMedicinesIntent.putExtra("medicinesName", medicinesName);
                showDetailsMedicinesIntent.putExtra("composition", composition);
                showDetailsMedicinesIntent.putExtra("formOfTheDrag", formOfTheDrag);
                showDetailsMedicinesIntent.putExtra("category", category);
                showDetailsMedicinesIntent.putExtra("speciality", speciality);
                showDetailsMedicinesIntent.putExtra("activity", activity);
                showDetailsMedicinesIntent.putExtra("indications", indications);
                showDetailsMedicinesIntent.putExtra("wayOfGiving", wayOfGiving);
                showDetailsMedicinesIntent.putExtra("possibleSideEffect", possibleSideEffect);
                showDetailsMedicinesIntent.putExtra("dose", dose);
                if (IsPrescription.equals("true")) {
                    showDetailsMedicinesIntent.putExtra("IsPrescription", "Tak");
                } else {
                    showDetailsMedicinesIntent.putExtra("IsPrescription", "Nie");
                }

                startActivity(showDetailsMedicinesIntent);
            }
        });

        ItemTouchHelper.SimpleCallback item = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            /**
             * after selecting the appropriate drug, insert into the My Pharmacy table
             * @param viewHolder
             * @param direction the direction in which you can make a swipe on an recycler item
             */
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                myMedicinesApplication = (MyMedicinesApplication) getApplication();
                medicinesService = myMedicinesApplication.getMedicinesService();

                idMed = idList.get(position);
                Call<Medicines> repo = medicinesService.getMedicinesById(myMedicinesApplication.getToken().getTokenID(), idMed);
                repo.enqueue(new Callback<Medicines>() {
                    @Override
                    public void onResponse(Call<Medicines> call, Response<Medicines> response) {
                        if (response.isSuccessful()) {
                            Log.d("TAG", "Udalo sie");
                        }
                        medicines = response.body();
                        insertMedi();

                        Context context = getApplicationContext();
                        CharSequence text = "Dodano do mojej apteki!";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                        downloadMedicines();

                    }

                    @Override
                    public void onFailure(Call<Medicines> call, Throwable t) {
                        Log.d("TAG", "Nie udalo sie");

                    }
                });


            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(item);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        downloadMedicines();
    }

    private void downloadMedicines() {
        myMedicinesApplication = (MyMedicinesApplication) getApplication();

        medicinesService = myMedicinesApplication.getMedicinesService();

        Call<List<Medicines>> repo = medicinesService.getMedicines(myMedicinesApplication.getToken().getTokenID());

        repo.enqueue(new Callback<List<Medicines>>() {
            @Override
            public void onResponse(Call<List<Medicines>> call, Response<List<Medicines>> response) {
                for (int i = 0; i < response.body().size(); i++) {
                    Log.d("TAG", response.body().get(i).toString());
                    idList.add(response.body().get(i).getId().toString());
                    nameList.add(response.body().get(i).getMedicinesName().toString());
                }
                medicinesAdapter.setMedicinesList(response.body());

            }

            @Override
            public void onFailure(Call<List<Medicines>> call, Throwable t) {

            }
        });

    }

    private void insertMedi() {
        myMedicinesApplication = (MyMedicinesApplication) getApplication();
        medicinesService = myMedicinesApplication.getMedicinesService();
        myPharmacyDB = new MyPharmacyDB("false", "", "", medicines);
        Call<MyPharmacyDB> addMed = medicinesService.addMedicines(myMedicinesApplication.getToken().getTokenID(), myPharmacyDB);
        addMed.enqueue(new Callback<MyPharmacyDB>() {
            @Override
            public void onResponse(Call<MyPharmacyDB> call, Response<MyPharmacyDB> response) {
                if (response.isSuccessful()) {
                    Log.d("ADD", "Dodano lek do mojej apteki");
                }
            }

            @Override
            public void onFailure(Call<MyPharmacyDB> call, Throwable t) {
                Log.d("ERROR", "Nie Dodano leku do mojej apteki");

            }
        });
    }

}
