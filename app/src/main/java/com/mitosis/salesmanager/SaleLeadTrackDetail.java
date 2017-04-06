package com.mitosis.salesmanager;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.mitosis.salesmanager.Constants.addressLine1;
import static com.mitosis.salesmanager.Constants.addressLine2;
import static com.mitosis.salesmanager.Constants.appointmentDate;
import static com.mitosis.salesmanager.Constants.city;
import static com.mitosis.salesmanager.Constants.contactName;
import static com.mitosis.salesmanager.Constants.country;
import static com.mitosis.salesmanager.Constants.distanceArr;
import static com.mitosis.salesmanager.Constants.email;
import static com.mitosis.salesmanager.Constants.getUserLocation;
import static com.mitosis.salesmanager.Constants.imageUrl;
import static com.mitosis.salesmanager.Constants.landMark;
import static com.mitosis.salesmanager.Constants.latitide;
import static com.mitosis.salesmanager.Constants.leadDetailsId;
import static com.mitosis.salesmanager.Constants.leadName;
import static com.mitosis.salesmanager.Constants.longitude;
import static com.mitosis.salesmanager.Constants.mobileNumber;
import static com.mitosis.salesmanager.Constants.notes;
import static com.mitosis.salesmanager.Constants.repId;
import static com.mitosis.salesmanager.Constants.saddressLine1;
import static com.mitosis.salesmanager.Constants.saddressLine2;
import static com.mitosis.salesmanager.Constants.sappointmentDate;
import static com.mitosis.salesmanager.Constants.scity;
import static com.mitosis.salesmanager.Constants.scontactName;
import static com.mitosis.salesmanager.Constants.scountry;
import static com.mitosis.salesmanager.Constants.sdistanceArr;
import static com.mitosis.salesmanager.Constants.semail;
import static com.mitosis.salesmanager.Constants.simageUrl;
import static com.mitosis.salesmanager.Constants.slandMark;
import static com.mitosis.salesmanager.Constants.slatitide;
import static com.mitosis.salesmanager.Constants.sleadDetailsId;
import static com.mitosis.salesmanager.Constants.sleadName;
import static com.mitosis.salesmanager.Constants.slongitude;
import static com.mitosis.salesmanager.Constants.smobileNumber;
import static com.mitosis.salesmanager.Constants.snotes;
import static com.mitosis.salesmanager.Constants.sortappointdateasc;
import static com.mitosis.salesmanager.Constants.sortaz;
import static com.mitosis.salesmanager.Constants.sortza;
import static com.mitosis.salesmanager.Constants.srepId;
import static com.mitosis.salesmanager.Constants.sstate;
import static com.mitosis.salesmanager.Constants.sstatus;
import static com.mitosis.salesmanager.Constants.state;
import static com.mitosis.salesmanager.Constants.status;
import static com.mitosis.salesmanager.Constants.statusattended;
import static com.mitosis.salesmanager.Constants.stelephoneNumber;
import static com.mitosis.salesmanager.Constants.szipCode;
import static com.mitosis.salesmanager.Constants.telephoneNumber;
import static com.mitosis.salesmanager.Constants.zipCode;
import static com.mitosis.salesmanager.SaleLeadTrackAdapter.repid;

/**
 * Created by mitosis on 27/3/17.
 */

public class SaleLeadTrackDetail extends Fragment {

 private TextView  name,total, complete,pending;
 ListView leadlist;
    SaleLeadTrackAdapter adapter;
    RadioButton alphaa, distance;
    RadioGroup sort;
    String firstnamee;
    String latituess="0.0",longtituees="0.0";
    double distances=0.0;
    double sdistances=0.0;
    List<Integer> integersArray = new ArrayList<>();
    public static ArrayList<String> distanceArrs=new ArrayList<>();

    public static Double lat2;
    public static  Double lang2;
    public String useridd;
    ArrayList<String> latdoub=new ArrayList<String>();
     ArrayList<String> longdoub=new ArrayList<String>();
    private String date,ascedingorder,descendingorder,statusatttended;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.saleleadtrackdetail, container, false);
        setHasOptionsMenu(true);

        name=(TextView)view.findViewById(R.id.name);
        total=(TextView)view.findViewById(R.id.total);
        complete=(TextView)view.findViewById(R.id.completed);
        pending=(TextView)view.findViewById(R.id.pending);
        leadlist=(ListView)view.findViewById(R.id.leadlist);
        firstnamee = getArguments().getString("firstname");
        String lastnamee = getArguments().getString("lastname");
        String pendingcountt = getArguments().getString("pendingcount");
        String completecountt = getArguments().getString("completecount");
        String totalcountt = getArguments().getString("totalcount");
         useridd = getArguments().getString("userid");
        String usernamee = getArguments().getString("usernames");
        name.setText(firstnamee);

        total.setText(totalcountt);
        complete.setText(completecountt);
        pending.setText(pendingcountt);
         replocation(getUserLocation+usernamee);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        String registerUserURL = Constants.leadListOfRepresentative+useridd;
        contactName.clear();leadName.clear();status.clear();telephoneNumber.clear();mobileNumber.clear();
        email.clear();addressLine1.clear();addressLine2.clear();city.clear();state.clear();zipCode.clear();landMark.clear();leadDetailsId.clear();imageUrl.clear();
        appointmentDate.clear();latitide.clear();longitude.clear();
        StringRequest registerRequest = new StringRequest(Request.Method.GET, registerUserURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                objectparsing(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("RegisterActivity", error.getMessage() != null ? error.getMessage() : "");
            }
        });
        requestQueue.add(registerRequest);
        leadlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = contactName.get(position);
                String statuz = status.get(position);
                String Addressline1 = addressLine1.get(position);
                String Addressline2 = addressLine2.get(position);
                String citi = city.get(position);
                String statE = state.get(position);
                String zipcode = zipCode.get(position);
                String mobnum = mobileNumber.get(position);
                String mile=distanceArr.get(position);
                String lat=latitide.get(position);
                String lng = longitude.get(position);
                String leadid = leadDetailsId.get(position);
                String date=appointmentDate.get(position);
                String image=imageUrl.get(position);
                String notesss=notes.get(position);
                Bundle args = new Bundle();
                Fragment fragment = new RepresentativeDetails();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                args.putString("Name", name);
                args.putString("Status", statuz);
                args.putString("AddressLine1", Addressline1);
                args.putString("AddressLine2", Addressline2);
                args.putString("City", citi);
                args.putString("State", statE);
                args.putString("ZipCode", zipcode);
                args.putString("Mile", mile);
                args.putString("mobile", mobnum);
                args.putString("latitude", lat);
                args.putString("longitude", lng);
                args.putString("idLead", leadid);
                args.putString("appointmentDate", date);
                args.putString("imageUrl", image);
                args.putString("NOtes", notesss);

                fragment.setArguments(args);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter,
                        R.anim.fragment_slide_left_exit,
                        R.anim.fragment_slide_right_enter,
                        R.anim.fragment_slide_right_exit);
                fragmentTransaction.replace(R.id.fragment_layout_for_activity_navigation, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        String[] items = sortappointdateasc.split("26");
        String part1 = items[0];
        String part2 = items[1];
        date=part1+useridd+part2;
        String[] sortazzz = sortaz.split("26");
        String azpart1 = sortazzz[0];
        String azpart2 = sortazzz[1];
        ascedingorder  =azpart1+useridd+azpart2;
        String[] sortzaaa = sortza.split("26");
        String zapart1 = sortzaaa[0];
        String zapart2 = sortzaaa[1];
        descendingorder  =zapart1+useridd+zapart2;

        String[] statuss = statusattended.split("26");
        String status1 = statuss[0];
        String status2 = statuss[1];
        statusatttended  =status1+useridd+status2;

        return view;
    }
    void replocation(String s){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        String registerUserURL =s;
        StringRequest registerRequest = new StringRequest(Request.Method.GET, registerUserURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject json = new JSONObject(response);
                    latituess= json.getString("latitude");
                    longtituees= json.getString("longitude");
                    //lat.add(json.getString("latitude"));
                    // longtitue.add(json.getString("longitude"));
                    //if(latituess!=null||longtituees!=null)
                    if(!latituess.equals("null")||!longtituees.equals("null"))
                    {
                        lat2 = Double.parseDouble(latituess);
                        lang2 = Double.parseDouble(longtituees);
                    }
                    else {
                        lat2 = Double.parseDouble("0.0");
                        lang2 = Double.parseDouble("0.0");
                    }
                }catch (JSONException e) {
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("RegisterActivity", error.getMessage() != null ? error.getMessage() : "");
            }
        });
        requestQueue.add(registerRequest);
    }
    public void objectparsing (String response){
        latitide.clear();longitude.clear();contactName.clear();status.clear();leadName.clear();telephoneNumber.clear();email.clear();addressLine1.clear();addressLine2.clear();city.clear();state.clear();zipCode.clear();country.clear();
        landMark.clear();leadDetailsId.clear();imageUrl.clear();repId.clear();appointmentDate.clear();notes.clear();
        distanceArr.clear();
        try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray =jsonObject.getJSONArray("leadList");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject json = jsonArray.getJSONObject(i);
                    contactName.add(json.getString("contactName"));
                    status.add(json.getString("status"));
                    leadName.add(json.getString("leadName"));
                    telephoneNumber.add(json.getString("telephoneNumber"));
                    mobileNumber.add(json.getString("mobileNumber"));
                    email.add(json.getString("email"));
                    addressLine1.add(json.getString("addressLine1"));
                    addressLine2.add(json.getString("addressLine2"));
                    city.add(json.getString("city"));
                    state.add(json.getString("state"));
                    zipCode.add(json.getString("zipCode"));
                    country.add(json.getString("country"));
                    landMark.add(json.getString("landMark"));
                    leadDetailsId.add(json.getString("leadDetailsId"));
                    imageUrl.add(json.getString("imageUrl"));
                    repId.add(json.getString("repId"));
                    appointmentDate.add(json.getString("appointmentDate"));
                    latitide.add(json.getString("latitide"));
                    longitude.add(json.getString("longitude"));
                    notes.add(json.getString("notes"));
                }
            } catch (JSONException e) {
            }
        try {
            Location location1 = new Location("locationA");
            location1.setLatitude(lat2);
            location1.setLongitude(lang2);
            Location location2 = new Location("locationB");

               for (int i = 0; i < contactName.size(); i++) {
                   if (latitide.get(i).equals("null")||lat2==0) {
                       distanceArr.add("0.0");

                   } else {

                       location2.setLatitude(Double.valueOf(latitide.get(i)));
                       location2.setLongitude(Double.valueOf(longitude.get(i)));
                       //    distances = location1.distanceTo(location2)/1000/kilometer
                       distances = location1.distanceTo(location2)/1000;//meter
                       distanceArr.add(String.valueOf((int) Math.round(distances)));
                   }
               }

            }
        //if(!latitide.equals("null") || !longitude.equals("null"))
        // if (latitide.equals("0.0") ||longitude.equals("0.0"))
        catch (Exception e) {
            e.printStackTrace();
        }

        adapter=new SaleLeadTrackAdapter(getContext(),contactName,status,leadName, mobileNumber,addressLine1,addressLine2,city,state,zipCode,country,landMark,leadDetailsId,imageUrl,repId,appointmentDate,latitide,longitude,notes,distanceArr);
        leadlist.setAdapter(adapter);
}
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        MenuItem item4 = menu.findItem(R.id.action_details);
        item4.setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().setTitle(firstnamee);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_mainMenu3:
                final Dialog dialog = new Dialog(getContext());
                showChangeLangDialog();
                return true;
            case R.id.action_search:
                Toast.makeText(getActivity(), "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    void Sortingfunction(String sss){

      /*  ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please for the server validation process..");
        progressDialog.setTitle("Loading..");
        progressDialog.setCancelable(false);
        progressDialog.show();

*/


            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            String registerUserURL = sss;
            scontactName.clear();sstatus.clear();saddressLine1.clear();sleadName.clear();stelephoneNumber.clear();
            smobileNumber.clear();semail.clear();scountry.clear();slandMark.clear();sleadDetailsId.clear();simageUrl.clear();
            saddressLine2.clear();scity.clear();sstate.clear();szipCode.clear();sdistanceArr.clear();
            srepId.clear();sappointmentDate.clear();slatitide.clear();slongitude.clear();snotes.clear();
            StringRequest registerRequest = new StringRequest(Request.Method.GET, registerUserURL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {



                    try {
                        JSONArray jsonArray = new JSONArray(response);


                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject json = jsonArray.getJSONObject(i);
                            scontactName.add(json.getString("contactName"));
                            sstatus.add(json.getString("status"));
                            sleadName.add(json.getString("leadName"));
                            stelephoneNumber.add(json.getString("telephoneNumber"));
                            smobileNumber.add(json.getString("mobileNumber"));
                            semail.add(json.getString("email"));
                            saddressLine1.add(json.getString("addressLine1"));
                            saddressLine2.add(json.getString("addressLine2"));
                            scity.add(json.getString("city"));
                            sstate.add(json.getString("state"));
                            szipCode.add(json.getString("zipCode"));
                            scountry.add(json.getString("country"));
                            slandMark.add(json.getString("landMark"));
                            sleadDetailsId.add(json.getString("leadDetailsId"));
                            simageUrl.add(json.getString("imageUrl"));
                            srepId.add(json.getString("repId"));
                            sappointmentDate.add(json.getString("appointmentDate"));
                            slatitide.add(json.getString("latitide"));
                            slongitude.add(json.getString("longitude"));
                            snotes.add(json.getString("notes"));
                            adapter = new SaleLeadTrackAdapter(getContext(), scontactName, sstatus, sleadName, smobileNumber, saddressLine1, saddressLine2, scity, sstate, szipCode, scountry, slandMark, sleadDetailsId, simageUrl, srepId, sappointmentDate, slatitide, slongitude,snotes,sdistanceArr);
                            leadlist.setAdapter(adapter);
                        }
                    }
                    catch (JSONException e) {

                    }
                    try {
                        Location location1 = new Location("locationA");
                        location1.setLatitude(lat2);
                        location1.setLongitude(lang2);
                        Location location2 = new Location("locationB");
                        if(lat2!=0||lang2!=0)
                        {
                            for (int i = 0; i < scontactName.size(); i++) {
                                if(slatitide.get(i).equals("null")){
                                    sdistanceArr.add("0.0");
                                }else{
                                    location2.setLatitude(Double.valueOf(slatitide.get(i)));
                                    location2.setLongitude(Double.valueOf(slongitude.get(i)));
                                    //    distances = location1.distanceTo(location2)/1000/kilometer
                                    sdistances = location1.distanceTo(location2)/1000;//meter
                                    sdistanceArr.add(String.valueOf((int) Math.round(sdistances)));
                                }
                            }
                        }
                        else{
                            sdistanceArr.add("0");

                        } }

                    //if(!latitide.equals("null") || !longitude.equals("null"))
                    // if (latitide.equals("0.0") ||longitude.equals("0.0"))
                    catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("RegisterActivity", error.getMessage() != null ? error.getMessage() : "");
                }
            });
            requestQueue.add(registerRequest);

    }
    public void showChangeLangDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.menusort, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setTitle("Sort By");
        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
             //   SaleLeadTrackDetail yhbhyn=new SaleLeadTrackDetail();
                sort = (RadioGroup) dialogView.findViewById(R.id.sort);
                int selectedId = sort.getCheckedRadioButtonId();
             /*   alphaa = (RadioButton) dialogView.findViewById(R.id.date);
                distance = (RadioButton) dialogView.findViewById(R.id.distance);*/
                switch (selectedId) {
                    case R.id.sortaz:
                        Sortingfunction(ascedingorder);
                        break;
                    case R.id.sortza:
                        Sortingfunction(descendingorder);
                        break;
                    case R.id.status:
                        Sortingfunction(statusatttended);
                        break;

                    case R.id.date:
                        Sortingfunction(date);
                        break;

                    case R.id.distance:
                        for (int i = 0; i < distanceArr.size(); i++) {
                            integersArray.add(Integer.parseInt(distanceArr.get(i)));
                        }
                        for (Integer integer: integersArray) {
                            System.out.println(integer);
                        }

                        for(int i=0; i < integersArray.size()-1; i++ ) {
                            for (int j = 0; j < integersArray.size() - 1; j++) {
                                if (integersArray.get(j) > integersArray.get(j + 1)) {
                                    int temp = integersArray.get(j);
                                    integersArray.set(j, integersArray.get(j + 1));
                                    integersArray.set(j + 1, temp);
                                }
                            }
                        }
                        for (int i = 0; i < integersArray.size(); i++){
                            distanceArrs.add(String.valueOf(integersArray.get(i)));
                        }
                        for (String i: distanceArrs) {
                            System.out.println(i);
                        }

                        for (Integer integer: integersArray) {
                            System.out.println(integer);
                        }
                        adapter=new SaleLeadTrackAdapter(getContext(),contactName,status,leadName, mobileNumber,addressLine1,addressLine2,city,state,zipCode,country,landMark,leadDetailsId,imageUrl,repId,appointmentDate,latitide,longitude,notes,distanceArrs);
                        leadlist.setAdapter(adapter);
                        break;

                }
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }
}
/*
    private class RepLocation extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {

            return null;
        }
    }
*/