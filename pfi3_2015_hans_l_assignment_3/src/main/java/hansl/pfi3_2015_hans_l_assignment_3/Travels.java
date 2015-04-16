package hansl.pfi3_2015_hans_l_assignment_3;


import android.os.AsyncTask;
import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Travels extends Fragment{

    ArrayList<Journey> journeyList = new ArrayList<Journey>();

    public Travels() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_travels,
                container, false);

        ExpandableListView expl = (ExpandableListView)view.findViewById(R.id.expandableListView);
        expl.setAdapter(new Adapter(getActivity(),journeyList));

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();
        if (id == R.id.action_search){
            Log.i("action_search","started");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void finishedSearch(){

    }

    private class DoInBackground extends AsyncTask<String,Void,Long> {
        @Override
        protected Long doInBackground(String... params) {
            //Search
            Journeys journeys = Parser.getJourneys(params[0]); //There can be many in the params Array
            //And put the Journeys in our list.
            journeyList.clear();
            journeyList.addAll(journeys.getJourneys());
            return null;
        }

        @Override
        protected void onPostExecute(Long result) { //Called when the AsyncTask is all done
            finishedSearch();
        }
    }


}
