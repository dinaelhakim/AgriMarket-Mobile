package luncher.SideMenu.side_menu_activities.profile.user_offers;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import java.util.ArrayList;
import DB.SqlDB;
import pojos.OfferClass;

/**
 * Created by Lenovo on 29/05/2016.
 */
public class GetMyOffersListListenerClass implements AdapterView.OnItemClickListener {
    SqlDB mydb;
    GetMyOffersActivity getMyOffersActivity;
    ArrayList<OfferClass> arrayList;
    public GetMyOffersListListenerClass(GetMyOffersActivity getMyOffersActivity) {
        mydb=new SqlDB(getMyOffersActivity);
        this.getMyOffersActivity=getMyOffersActivity;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        arrayList=mydb.getUserOffers();

        OfferClass offerObj=arrayList.get(position);
        Intent intent=new Intent(getMyOffersActivity, UserOffersDetailsActivity.class);

        intent.putExtra("offer", offerObj);
        getMyOffersActivity.startActivity(intent);
        getMyOffersActivity.finish();
    }
}
