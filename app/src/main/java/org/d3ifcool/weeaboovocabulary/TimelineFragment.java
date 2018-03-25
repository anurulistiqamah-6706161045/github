package org.d3ifcool.weeaboovocabulary;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class TimelineFragment extends Fragment {
    private ControlKosakata array_kosakata;
    private ListView history_kosakata;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.activity_timeline,container,false);

        this.array_kosakata = new ControlKosakata();
        this.history_kosakata = (ListView) rootView.findViewById(R.id.history_kosakata);

        AdapterList<KosakataClass> adapter = new AdapterList<>((Activity) getContext(), array_kosakata.getArray_kosakata(), 2);
        history_kosakata.setAdapter(adapter);
        return rootView;
    }
}
