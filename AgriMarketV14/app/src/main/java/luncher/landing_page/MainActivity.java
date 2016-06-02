package luncher.landing_page;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.lenovo.testslidenerd.GenericAdapter;
import com.example.lenovo.testslidenerd.GenericListener;
import com.example.lenovo.testslidenerd.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import luncher.SideMenu.sidemenu_setting.SideMenuAdapter;
import luncher.SideMenu.sidemenu_setting.SideMenuListener;
import reguest_parameters_pojos.JsonParams;
import responce_pojos.GetMainCategoriesResponcePojo;
import webservice_connection_volley.JsonStringGenerator;
import webservice_connection_volley.WebServicesUrl;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ListView listView;
    private String[] setting;
    private ActionBarDrawerToggle drawerListener;
    private ListView mainListView;

    //WebService GETMAINCATEGORIES------

    private ProgressDialog dialog;
    ArrayList<String> categoriesNames=new ArrayList();
    ArrayList<String> imagesUrl=new ArrayList();
    String parameters = JsonStringGenerator.getMainCategoriesParameters("en");
    GenericAdapter genericAdapter;
    //----------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog=new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, WebServicesUrl.GET_MAIN_CATEGORIES, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        Log.i("++",response.toString());
                        try {
                            String x=response.getString("entity");
                            JSONObject jsonObjectEntity = new JSONObject(x);
                            JSONArray jsonArrayCategories=jsonObjectEntity.getJSONArray("categories");
                            for (int i=0; i< jsonArrayCategories.length() ;i++ ){
                                JSONObject jsonObjectName = jsonArrayCategories.getJSONObject(i);

                                //Name
                                String nameEN=jsonObjectName.getString("nameEn");
                                Log.i("++",nameEN);
                                categoriesNames.add(i,nameEN);
                                //imageUrl
                                String imageUri=jsonObjectName.getString("imageUrl");
                                Log.i("++",imageUri);
                                imagesUrl.add(i,WebServicesUrl.Image_URL+imageUri);
                            }
                            genericAdapter.notifyDataSetChanged();


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





//Home Page -----------------------------------------------------
        mainListView=(ListView)findViewById(R.id.MainListView);
        genericAdapter=new GenericAdapter(this, categoriesNames,imagesUrl);
        mainListView.setAdapter(genericAdapter);
        mainListView.setOnItemClickListener(new GenericListener(-1, this));


//ListVew
        listView=(ListView)findViewById(R.id.drawerList);
        setting=getResources().getStringArray(R.array.setting);
//-------------------------------------------------------
//To render List with elements : use method (1) or (2)
//(1)Simple List with default Adapter
//(2)Custom List image + text
        SideMenuAdapter myAdapter=new SideMenuAdapter(getBaseContext(),setting);
        listView.setAdapter(myAdapter);
//---------------------------------------------------------
        listView.setOnItemClickListener(new SideMenuListener(this,listView,setting));
//DrawerLayout
        drawerLayout=(DrawerLayout)findViewById(R.id.drawlayout);
        drawerListener=new ActionBarDrawerToggle(this,drawerLayout,R.string.drawer_open,R.string.drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
//                Toast.makeText(MainActivity.this,"Drawer is Opened", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
//                Toast.makeText(MainActivity.this,"Drawer is Closed", Toast.LENGTH_LONG).show();
            }
        };

        drawerLayout.addDrawerListener(drawerListener);

//display back arow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
//To display List icon
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerListener.syncState();
    }

//To Open and close list using list icon
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerListener.onOptionsItemSelected(item)){

            return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerListener.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }


    @Override
    protected void onResume() {
        super.onResume();


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
}