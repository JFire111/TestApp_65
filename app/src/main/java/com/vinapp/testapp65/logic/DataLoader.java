package com.vinapp.testapp65.logic;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.vinapp.testapp65.logic.data.Specialty;
import com.vinapp.testapp65.logic.data.Worker;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

public class DataLoader extends AsyncTask<Void, Void, Void> {

    private final String DATA_URL = "https://gitlab.65apps.com/65gb/static/raw/master/testTask.json";

    private ArrayList<Specialty> specialties;
    private ArrayList<Worker> workers;
    private Context context;

    private JSONObject responseJSON;

    public DataLoader(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        DatabaseManager databaseManager = new DatabaseManager(context);
        loadWorkers(requestToServer(DATA_URL));
        loadSpecialties(requestToServer(DATA_URL));
        for (int i = 0; i < workers.size(); i++) {
            databaseManager.addWorker(workers.get(i));
        }
        for (int i = 0; i < specialties.size(); i++) {
            databaseManager.addSpecialty(specialties.get(i));
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    private JSONObject requestToServer(String requestUrl) {
        StringBuffer response = new StringBuffer();
        String line;
        try {
            URL url = new URL(requestUrl);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            connection.disconnect();
            reader.close();
            responseJSON = new JSONObject(response.toString());
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return responseJSON;
    }

    private void loadSpecialties(JSONObject jsonObject) {
        ArrayList<Specialty> specialtyList = new ArrayList<>();
        Set<String> specialtyNamesSet = new HashSet<>();
        JSONArray dataJSON;
        try {
            dataJSON = jsonObject.getJSONArray("response");
            for (int i = 0; i < dataJSON.length(); i++) {
                JSONArray specialtiesJSON = dataJSON.getJSONObject(i).getJSONArray("specialty");
                for (int j = 0; j < specialtiesJSON.length(); j++) {
                    String specialtyName = specialtiesJSON.getJSONObject(j).getString("name");
                    if (specialtyNamesSet.add(specialtyName)) {
                        Specialty specialty = new Specialty();
                        specialty.setId(specialtiesJSON.getJSONObject(j).getInt("specialty_id"));
                        specialty.setName(specialtyName);
                        specialtyList.add(specialty);
                    }
                }
            }
        } catch (Exception exc){
            exc.printStackTrace();
        }
        this.specialties = specialtyList;
    }

    private void loadWorkers(JSONObject jsonObject) {
        ArrayList<Worker> workersList = new ArrayList<>();
        JSONArray dataJSON;
        try {
            dataJSON = jsonObject.getJSONArray("response");
            for (int i = 0; i < dataJSON.length(); i++) {
                JSONObject workerJSON = dataJSON.getJSONObject(i);
                JSONArray specialtiesJSON = workerJSON.getJSONArray("specialty");
                for (int j = 0; j < specialtiesJSON.length(); j++) {
                    Worker worker = new Worker();
                    Specialty specialty = new Specialty();
                    specialty.setName(specialtiesJSON.getJSONObject(j).getString("name"));
                    specialty.setId(specialtiesJSON.getJSONObject(j).getInt("specialty_id"));
                    worker.setFirstName(workerJSON.getString("f_name"));
                    worker.setLastName(workerJSON.getString("l_name"));
                    worker.setBirthday(workerJSON.getString("birthday"));
                    worker.setSpecialty(specialty);
                    worker.setAvatarUrl(workerJSON.getString("avatr_url"));
                    workersList.add(worker);
                }
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        this.workers = workersList;
    }
}
