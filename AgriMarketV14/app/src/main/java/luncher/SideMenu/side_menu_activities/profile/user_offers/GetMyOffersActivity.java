package luncher.SideMenu.side_menu_activities.profile.user_offers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.lenovo.testslidenerd.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import DB.SqlDB;
import luncher.SideMenu.side_menu_activities.profile.Profile;
import pojos.OfferClass;
import reguest_parameters_pojos.JsonParams;
import webservice_connection_volley.JsonStringGenerator;
import webservice_connection_volley.WebServicesUrl;

public class GetMyOffersActivity extends AppCompatActivity {

    public static final String fileName="SharedFile";
    private ProgressDialog dialog;
    SqlDB mydb;
    ArrayList<OfferClass> userOffersList=new ArrayList<>();
    ListView listView;
    GetMyOffersListAdapterClass getMyOffersListAdapterClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generic_layout);
        listView=(ListView)findViewById(R.id.listView);
        connectToGetUserOffersWebService();

        getMyOffersListAdapterClass=new GetMyOffersListAdapterClass(getBaseContext(),userOffersList);
        listView.setAdapter(getMyOffersListAdapterClass);
        listView.setOnItemClickListener(new GetMyOffersListListenerClass(this));



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //to display back button
    }


    private void connectToGetUserOffersWebService() {
        SharedPreferences sharedPref = getSharedPreferences(fileName, 0);
        int id=sharedPref.getInt("id", -1);
        final String parameters= JsonStringGenerator.getGetUserOffersParam(id);

        dialog=new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, WebServicesUrl.GET_USER_OFFERS, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        Log.i("++ the response is: ", response.toString());
                        try {
                            String x=response.getString("entity");
                            Log.i("++",x);
                            JSONObject jsonObjectEntity = new JSONObject(x);
                            JSONArray jsonArrayOffers=jsonObjectEntity.getJSONArray("offers");
                            for (int i=0; i< jsonArrayOffers.length() ;i++ ){
                                JSONObject jsonObjectOffer = jsonArrayOffers.getJSONObject(i);
                                OfferClass offerObj=new OfferClass();
                                offerObj.setProducctId(jsonObjectOffer.getInt("id"));
                                offerObj.setPrice(jsonObjectOffer.getInt("price"));
                                offerObj.setQuntity(jsonObjectOffer.getInt("quantity"));
                                offerObj.setStart_date(jsonObjectOffer.getString("startDate"));
                                offerObj.setUserPhone(jsonObjectOffer.getString("userPhone"));
                                offerObj.setUserLocation(jsonObjectOffer.getString("userLocation"));
                                offerObj.setDescription(jsonObjectOffer.getString("description"));
                                offerObj.setImageUrl(WebServicesUrl.Image_URL + jsonObjectOffer.getString("imageUrl"));
                                userOffersList.add(offerObj);
                            }
                            getMyOffersListAdapterClass.notifyDataSetChanged();
                            mydb=new SqlDB(getApplicationContext());
                            mydb.deleteUserOffers();
                            mydb.insertUserOffer(userOffersList);
                            ArrayList<OfferClass> list=mydb.getUserOffers();
                            for (OfferClass offerClass:list){
                                Log.i("++PP",offerClass.getDescription());
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("++ error ",error.getMessage());
                    }
                }
        ){
            @Override
            public byte[] getBody() {
                return getRequestBody(parameters).getBytes();
            }
        };

        RequestQueue queue = Volley.newRequestQueue(GetMyOffersActivity.this);
        queue.add(request);
    }

    private String getRequestBody(String parameters){
        JsonParams param = new JsonParams();
        param.setJsonObject(parameters);
        param.setUserService("user");
        param.setKeyService("encrypted");
        param.setCompressLength(3);

        Gson gson = new GsonBuilder().create();
        String body = gson.toJson(param);

        return body;
    }


    public void hideDialog(){
        if(dialog !=null){
            dialog.dismiss();
            dialog=null;
        }
    }



    //To be back when you click back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent mainActivityIntenet=new Intent(this,Profile.class);
                startActivity(mainActivityIntenet);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,Profile.class);
        startActivity(intent);
        finish();
    }



}
