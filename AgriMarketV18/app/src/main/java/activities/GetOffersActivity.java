package activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
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
import listeners.GetOffersListenerClass;
import adapters.OffersAdapterClass;
import pojos.OfferClass;
import reguest_parameters_pojos.GetLimitedOffersParam;
import reguest_parameters_pojos.JsonParams;
import web_services_connections_handlers.JsonStringGenerator;
import web_services_connections_handlers.WebServicesUrl;

public class GetOffersActivity extends AppCompatActivity {

    ListView listView;
    FloatingActionButton fab;
    String parameters,categoryName;
    ProgressDialog dialog;
    ArrayList<OfferClass> offersList=new ArrayList<>();
    OffersAdapterClass getMyOffersListAdapterClass;

    int categoryId;

    ArrayList<String> imageUrlList=new ArrayList<>();
    ArrayList<Object> titelsList=new ArrayList<>();
    ArrayList<String> subTitelsList=new ArrayList<>();
    ArrayList<Boolean> isRecommendedList=new ArrayList<>();
    OffersAdapter offersAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_list_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);




        fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Replace with your own action, productId=" + categoryId, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                //       Intent intent=new Intent(GetOffersActivity.this,);
                //       intent.putExtra("productId",categoryId);
                //      startActivity(intent);
            }
        });




        listView= (ListView) findViewById(R.id.listView);
        Log.i("++", "GetOffersActivity");
        Intent intent = getIntent();
        categoryId = intent.getIntExtra("categoryId", -1);
        categoryName=intent.getStringExtra("categoryName");
        Log.i("++offerid= ", ((Integer) categoryId).toString());


        parameters= JsonStringGenerator.getGetLimitedOffersParam(categoryId, GetLimitedOffersParam.DATE_SORT, 1);
        connectToGetLimitedOffers(parameters,"startDate");

        offersAdapter=new OffersAdapter(getBaseContext(),imageUrlList,titelsList,subTitelsList,isRecommendedList);
        listView.setAdapter(offersAdapter);
        listView.setOnItemClickListener(new GetOffersListenerClass(GetOffersActivity.this, offersList));
    }


    private void connectToGetLimitedOffers(final String parameters, final String title) {
        dialog=new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, WebServicesUrl.GET_OFFERS_LIMITEd, null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                hideDialog();
                Log.i("++", response.toString());
                try {
                    String x=response.getString("entity");
                    Log.i("++",x);
                    JSONArray jsonArrayObjectEntity = new JSONArray(x);

                    for (int i=0; i< jsonArrayObjectEntity.length() ;i++ ){
                        JSONObject jsonObjectOffer = jsonArrayObjectEntity.getJSONObject(i);
                        OfferClass offerObj=new OfferClass();
                        offerObj.setProducctId(categoryId);
                        offerObj.setPrice(jsonObjectOffer.getInt("price"));
                        offerObj.setQuntity(jsonObjectOffer.getInt("quantity"));
                        offerObj.setStart_date(jsonObjectOffer.getString("startDate"));
                        offerObj.setUserPhone(jsonObjectOffer.getString("userPhone"));
                        offerObj.setUserLocation(jsonObjectOffer.getString("userLocation"));
                        offerObj.setDescription(jsonObjectOffer.getString("description"));
                        offerObj.setImageUrl(WebServicesUrl.Image_URL + jsonObjectOffer.getString("imageUrl"));
                        offersList.add(offerObj);

                        if(title.equals("startDate")){
                            titelsList.add(jsonObjectOffer.getString("startDate"));
                        }else if(title.equals("price")){
                            titelsList.add(((Integer) (jsonObjectOffer.getInt("price"))).toString()+ "  L.E");
                        }else if(title.equals("quantity")){
                            titelsList.add(((Integer)(jsonObjectOffer.getInt("quantity"))).toString());
                        }

                        subTitelsList.add(jsonObjectOffer.getString("description"));
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sort_menu, menu);
        return true;
    }


    //To be back when you click back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_sort_by_price:

                offersList.clear();
                imageUrlList.clear();
                titelsList.clear();
                subTitelsList.clear();
                isRecommendedList.clear();
                parameters= JsonStringGenerator.getGetLimitedOffersParam(categoryId, GetLimitedOffersParam.PRICE_SORT, 1);
                connectToGetLimitedOffers(parameters,"price");
                offersAdapter.notifyDataSetChanged();
                showMsg("Price");
                break;
            case R.id.action_sort_by_quantity:
                offersList.clear();
                imageUrlList.clear();
                titelsList.clear();
                subTitelsList.clear();
                isRecommendedList.clear();
                parameters= JsonStringGenerator.getGetLimitedOffersParam(categoryId, GetLimitedOffersParam.QUANTITY_SORT, 1);
                connectToGetLimitedOffers(parameters,"quantity");
                offersAdapter.notifyDataSetChanged();
                showMsg("quantity");
                break;
            case R.id.action_sort_by_date:
                offersList.clear();
                imageUrlList.clear();
                titelsList.clear();
                subTitelsList.clear();
                isRecommendedList.clear();
                parameters= JsonStringGenerator.getGetLimitedOffersParam(categoryId, GetLimitedOffersParam.DATE_SORT, 1);
                connectToGetLimitedOffers(parameters,"startDate");
                offersAdapter.notifyDataSetChanged();
                showMsg("Date");
                break;
            case R.id.search:
                Intent intent = new Intent(GetOffersActivity.this,SearchActivity.class);
                startActivity(intent);
                showMsg("search");
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    private void showMsg(String msg){
        Toast.makeText(GetOffersActivity.this,msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
