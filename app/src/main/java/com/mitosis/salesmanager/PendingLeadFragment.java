package com.mitosis.salesmanager;

import android.app.Dialog;
import android.content.DialogInterface;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import static com.mitosis.salesmanager.Constants.leadListOfRepresentative;
import static com.mitosis.salesmanager.Constants.paddressLine1;
import static com.mitosis.salesmanager.Constants.paddressLine2;
import static com.mitosis.salesmanager.Constants.pappointmentDate;
import static com.mitosis.salesmanager.Constants.pcity;
import static com.mitosis.salesmanager.Constants.pcontactName;
import static com.mitosis.salesmanager.Constants.pemail;
import static com.mitosis.salesmanager.Constants.pendingleadListOfRepresentative;
import static com.mitosis.salesmanager.Constants.pimageUrl;
import static com.mitosis.salesmanager.Constants.plandMark;
import static com.mitosis.salesmanager.Constants.platitude;
import static com.mitosis.salesmanager.Constants.pleadDetailsId;
import static com.mitosis.salesmanager.Constants.pleadname;
import static com.mitosis.salesmanager.Constants.plongitude;
import static com.mitosis.salesmanager.Constants.pmobileNumber;
import static com.mitosis.salesmanager.Constants.pnotes;
import static com.mitosis.salesmanager.Constants.prepId;
import static com.mitosis.salesmanager.Constants.pstate;
import static com.mitosis.salesmanager.Constants.pstatus;
import static com.mitosis.salesmanager.Constants.ptelephonenumber;
import static com.mitosis.salesmanager.Constants.pzipCode;

/**
 * Created by mitosis on 20/2/17.
 */

public class PendingLeadFragment extends Fragment {
    ListView pendinglist;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pendinglead_list, container, false);
        setHasOptionsMenu(true);
        pendinglist = (ListView) view.findViewById(R.id.pending_listview);
        pending(pendingleadListOfRepresentative + 45);



        pendinglist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String name = pcontactName.get(position);
                String statuz = pstatus.get(position);
                String Addressline1 = paddressLine1.get(position);
                String Addressline2 = paddressLine2.get(position);
                String citi = pcity.get(position);
                String statE = pstate.get(position);
                String zipcode = pzipCode.get(position);
                String mobnum = pmobileNumber.get(position);
              //  String mile=pdistanceArr.get(position);
                String lat=platitude.get(position);
                String lng = plongitude.get(position);
                String leadid = pleadDetailsId.get(position);
                String date=pappointmentDate.get(position);
                String image=pimageUrl.get(position);
                String notesss=pnotes.get(position);
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
              //  args.putString("Mile", mile);
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
        return view;
    }

    void pending(String s) {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        String registerUserURL = s;
        StringRequest registerRequest = new StringRequest(Request.Method.GET, registerUserURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

pcontactName.clear();pstatus.clear();pleadname.clear();ptelephonenumber.clear();pmobileNumber.clear();pemail.clear();
                paddressLine1.clear();paddressLine2.clear();pcity.clear();pstate.clear();pzipCode.clear();plandMark.clear();
                pleadDetailsId.clear();pimageUrl.clear();prepId.clear();platitude.clear();plongitude.clear();pappointmentDate.clear();pnotes.clear();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        pcontactName.add(jsonObject.getString("contactName"));
                        pstatus.add(jsonObject.getString("status"));
                        pleadname.add(jsonObject.getString("leadName"));
                        ptelephonenumber.add(jsonObject.getString("mobileNumber"));
                        pmobileNumber.add(jsonObject.getString("mobileNumber"));
                        pemail.add(jsonObject.getString("email"));
                        paddressLine1.add(jsonObject.getString("addressLine1"));
                        paddressLine2.add(jsonObject.getString("addressLine2"));
                        pcity.add(jsonObject.getString("city"));
                        pstate.add(jsonObject.getString("state"));
                        pzipCode.add(jsonObject.getString("zipCode"));
                        plandMark.add(jsonObject.getString("landMark"));
                        pleadDetailsId.add(jsonObject.getString("leadDetailsId"));
                        pimageUrl.add(jsonObject.getString("imageUrl"));
                        prepId.add(jsonObject.getString("repId"));
                        platitude.add(jsonObject.getString("latitide"));
                        plongitude.add(jsonObject.getString("longitude"));
                        pappointmentDate.add(jsonObject.getString("appointmentDate"));
                        pnotes.add(jsonObject.getString("notes"));
                        //lat.add(json.getString("latitude"));
                        // longtitue.add(json.getString("longitude"));


                        Pendingadapter adapter=new Pendingadapter(getContext(),pcontactName,pstatus,pleadname,ptelephonenumber,pmobileNumber,pemail,paddressLine1,paddressLine2,
                                pcity,pstate,pzipCode,plandMark,pleadDetailsId,pimageUrl,prepId,platitude,plongitude,pappointmentDate,pnotes);
                        pendinglist.setAdapter( adapter);
                    }

                } catch (JSONException e) {

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
}