package masterung.th.in.androidthai.mytutorial;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListVideoFragment extends Fragment {


    public ListVideoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Create RecyclerView
        createRecyclerView();

    }   // Main Method

    private void createRecyclerView() {
        final RecyclerView recyclerView = getView().findViewById(R.id.recyclerVideo);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("VideoMasterUNG");

        final int[] timesInts = {0};
        final String tag = "6MarchV1";
        final ArrayList<String> nameStringArrayList = new ArrayList<>();
        final ArrayList<String> iconStringArrayList = new ArrayList<>();
        ArrayList<String> youtubeKeyStringArrayList = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                int amountVideo = (int) dataSnapshot.getChildrenCount();
                Log.d(tag, "amountVideo ==> " + amountVideo);

                List list = new ArrayList();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    ListVideoModel listVideoModel = dataSnapshot1.getValue(ListVideoModel.class);

                    nameStringArrayList.add(listVideoModel.getName());
                    iconStringArrayList.add(listVideoModel.getImage());

                    timesInts[0] += 1;
                }   // for

                Log.d(tag, nameStringArrayList.toString());
                Log.d(tag, iconStringArrayList.toString());

                ListVideoAdapter listVideoAdapter = new ListVideoAdapter(getActivity(), nameStringArrayList, iconStringArrayList, new OnClickItem() {
                    @Override
                    public void onClickItem(View view, int position) {
                        Log.d(tag, "You Click ==> " + position);
                    }
                });

                recyclerView.setAdapter(listVideoAdapter);

            }   // onDataChange

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_video, container, false);
    }

}
