package activities;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

import adapters.OffersAdapter;
import adapters.SearchAdapter;
import listeners.GetOffersListenerClass;
import pojos.OfferClass;
import reguest_parameters_pojos.GetLimitedOffersParam;
import reguest_parameters_pojos.JsonParams;
import web_services_connections_handlers.JsonStringGenerator;
import web_services_connections_handlers.WebServicesUrl;


public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

//    TextView textView;
    ListView listView;

    ProgressDialog dialog;

    ArrayList<OfferClass> offersList=new ArrayList<>();

    ArrayList<String> imageUrlList=new ArrayList<>();
    ArrayList<String> titlesList=new ArrayList<>();
    ArrayList<String> subTitlesList=new ArrayList<>();
    ArrayList<Boolean> isRecommendedList=new ArrayList<>();
//    SearchAdapter searchAdapter;
    GetOffersListenerClass getOffersListener;

//TODO REMOVED---------------
    OffersAdapter offersAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView=(ListView)findViewById(R.id.listView);
        offersAdapter= new OffersAdapter(getBaseContext(),imageUrlList,titlesList,subTitlesList,isRecommendedList);
        getOffersListener=new GetOffersListenerClass(this,offersList,true);
        listView.setOnItemClickListener(getOffersListener);
        listView.setAdapter(offersAdapter);



//        //prepare parameters for Search Web service
//        String parameters= JsonStringGenerator.getSearchLimitedOffersParam("tom", GetLimitedOffersParam.PRICE_SORT,1);
//
//
//        //call Search web Service
//        connectToSearchWebService(parameters);

    }


    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu, menu);
        SearchView searchView=(SearchView)menu.findItem(R.id.action_search).getActionView();
        SearchManager searchManager=(SearchManager)getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {

        Toast.makeText(SearchActivity.this, query, Toast.LENGTH_SHORT).show();
        String parameters= JsonStringGenerator.getSearchLimitedOffersParam(query, GetLimitedOffersParam.PRICE_SORT,1);
        connectToSearchWebService(parameters);

        return false;
    }

    private void connectToSearchWebService(final String parameters) {


        dialog=new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, WebServicesUrl.SEARCH_OFFERS_LIMITED, null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                hideDialog();
//                Log.i("++", response.toString());
                try {
                    String x=response.getString("entity");
                    Log.i("++search",x);
                    JSONArray jsonArrayObjectEntity = new JSONArray(x);

                    for (int i=0; i< jsonArrayObjectEntity.length() ;i++ ){
                        JSONObject jsonObjectOffer = jsonArrayObjectEntity.getJSONObject(i);

                        titlesList.add(((Integer) (jsonObjectOffer.getInt("price"))).toString() + "  L.E");
                        subTitlesList.add(jsonObjectOffer.getString("description"));
                        imageUrlList.add(WebServicesUrl.Image_URL + jsonObjectOffer.getString("imageUrl"));
                        isRecommendedList.add(jsonObjectOffer.getBoolean("recommended"));

                    }

                    offersAdapter.notifyDataSetChanged();



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("++",error.getMessage());
                    }
                }
        ){
            // this is the relevant method
            @Override
            public byte[] getBody() {
                return getRequestBody(parameters).getBytes();
            }
        };


        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }






    public void hideDialog(){
        if(dialog !=null){
            dialog.dismiss();
            dialog=null;
        }
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

    @Override
    public boolean onQueryTextChange(String newText) {
//        textView.setText(newText);
        return false;
    }
}
