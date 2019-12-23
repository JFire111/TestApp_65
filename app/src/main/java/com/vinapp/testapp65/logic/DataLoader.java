package com.vinapp.testapp65.logic;

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

public class DataLoader {

    private ArrayList<Specialty> specialties;
    private ArrayList<Worker> workers;
    private JSONObject responseJSON;

    public void loadData(String url) {
        loadWorkers(requestToServer(url));
        loadSpecialties(requestToServer(url));
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
        Set<Specialty> specialtyList = new HashSet<>();
        JSONArray dataJSON;
        try {
            dataJSON = jsonObject.getJSONArray("response");
            for (int i = 0; i < dataJSON.length(); i++) {
                JSONArray specialtiesJSON = dataJSON.getJSONObject(i).getJSONArray("specialty");
                for (int j = 0; j < specialtiesJSON.length(); j++) {
                    Specialty specialty = new Specialty();
                    specialty.setId(specialtiesJSON.getJSONObject(j).getInt("specialty_id"));
                    specialty.setName(specialtiesJSON.getJSONObject(j).getString("name"));
                    if (specialtyList.contains(specialty.getId())) {
                    } else {
                        specialtyList.add(specialty);
                    }
                }
            }
        } catch (Exception exc){
            exc.printStackTrace();
        }
        for (int i = 0; i < specialtyList.size(); i++) {
            Log.e("specs", specialtyList.size() + "");
        }
        //this.specialties = specialtyList;
    }

    private void loadWorkers(JSONObject jsonObject) {
        ArrayList<Worker> workersList = new ArrayList<>();
        ArrayList<Specialty> specialtiesList = new ArrayList<>();
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
                    workersList.add(worker);
                }
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        this.workers = workersList;
    }

    public ArrayList<Specialty> getSpecialties() {
        return specialties;
    }

    public ArrayList<Worker> getWorkers() {
        return workers;
    }
}
