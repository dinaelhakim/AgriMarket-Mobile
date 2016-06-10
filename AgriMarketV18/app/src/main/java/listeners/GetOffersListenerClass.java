package listeners;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import java.util.ArrayList;

import activities.GetOffersActivity;
import activities.OfferDetailsActivity;
import activities.SearchActivity;
import pojos.OfferClass;

public class GetOffersListenerClass implements AdapterView.OnItemClickListener{


    GetOffersActivity getOffersActivity;
    ArrayList<OfferClass> arrayListOfOffers;
    SearchActivity searchActivity;
    boolean isFromSearchActivity=false;

    public GetOffersListenerClass(GetOffersActivity getOffersActivity,ArrayList<OfferClass> arrayListOfOffers) {
        this.getOffersActivity=getOffersActivity;
        this.arrayListOfOffers=arrayListOfOffers;
    }

    public GetOffersListenerClass(SearchActivity searchActivity,ArrayList<OfferClass> arrayListOfOffers,boolean isFromSearchActivity){
        this.searchActivity=searchActivity;
        this.arrayListOfOffers=arrayListOfOffers;
        this.isFromSearchActivity=isFromSearchActivity;
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(!isFromSearchActivity){
            OfferClass offerObj=arrayListOfOffers.get(position);
            Intent intent=new Intent(getOffersActivity, OfferDetailsActivity.class);
            intent.putExtra("offer", offerObj);
            intent.putExtra("from","GetOffersActivity");
            getOffersActivity.startActivity(intent);
        }


        //SearchActivity list listener Action
        if(isFromSearchActivity){

        }


    }
}
