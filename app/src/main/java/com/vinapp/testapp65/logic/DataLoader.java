package com.vinapp.testapp65.logic;

import com.vinapp.testapp65.logic.data.Specialty;
import com.vinapp.testapp65.logic.data.Worker;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class DataLoader {

    private ArrayList<Specialty> specialties = new ArrayList<>();
    private ArrayList<Worker> workers = new ArrayList<>();
    private JSONObject responseJSON;

    public JSONObject requestToServer(String requestUrl) {
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

    private void setSpecialties(JSONObject jsonObject) {
        Specialty specialty = new Specialty();
        JSONArray specialtyJSON;
        try {
            specialtyJSON = jsonObject.getJSONArray("specialty");
            //specialty.setId(specialtyJSON.getJSONObject());
        } catch (Exception exc){
            exc.printStackTrace();
        }
    }

    private void setWorkers(JSONObject jsonObject) {

    }

    public ArrayList<Specialty> getSpecialties() {
        return specialties;
    }

    public ArrayList<Worker> getWorkers() {
        return workers;
    }
}
