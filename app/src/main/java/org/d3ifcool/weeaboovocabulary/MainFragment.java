package org.d3ifcool.weeaboovocabulary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * MainFragment For Main Activity
 */

public class MainFragment extends Fragment {

    /**
     * Deklarasi Variabel
     */

    private TextView jam,menit;
    private Button reset,save;
    private int data_jam,data_menit;
    private ListView list_jadwal;
    private ControlJadwal array_jadwal;

    /**
     * Main Method Fragment
     */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        /**
         * Inisialisasi Variabel
         */

        View rootView= inflater.inflate(R.layout.activity_main,container,false);
        array_jadwal = new ControlJadwal();
        this.jam = (TextView)rootView.findViewById(R.id.jam);
        this.menit = (TextView) rootView.findViewById(R.id.menit);
        this.reset = (Button) rootView.findViewById(R.id.reset);
        this.save = (Button) rootView.findViewById(R.id.save);
        this.data_jam = 0;
        this.data_menit = 0;
        this.list_jadwal = (ListView)rootView.findViewById(R.id.list_jadwal);

        /**
         * Event Handler Onclick
         */

        this.jam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data_jam >= 23){
                    data_jam = 0;
                    jam.setText("0"+Integer.toString(data_jam));
                }else {
                    data_jam++;
                    if (data_jam <10){
                        jam.setText("0"+Integer.toString(data_jam));
                    }else {
                        jam.setText(Integer.toString(data_jam));
                    }

                }
            }
        });
        this.menit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data_menit >= 59){
                    data_menit = 0;
                    data_jam ++;
                    menit.setText("0"+Integer.toString(data_menit));
                    if (data_jam <10){
                        jam.setText("0"+Integer.toString(data_jam));
                    }else {
                        jam.setText(Integer.toString(data_jam));
                    }
                }else {
                    data_menit++;
                    if (data_menit<10){
                        menit.setText("0"+Integer.toString(data_menit));
                    }else {
                        menit.setText(Integer.toString(data_menit));
                    }
                }
            }
        });
        this.reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jam.setText("00");
                menit.setText("00");
                data_jam = 0;
            }
        });
        this.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                array_jadwal.getArray_jadwal().add(new JadwalClass(jam.getText().toString(),menit.getText().toString()));
                updateList();
            }
        });
        list_jadwal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (array_jadwal.getArray_jadwal().get(position).getStatus() == 1){
                    Intent intent = new Intent(getContext().getApplicationContext(),KosakataActivity.class);
                    startActivity(intent);
                }
            }
        });
        return rootView;
    }

    /**
     * Update List View Item
     */

    private void updateList(){
        AdapterList<JadwalClass> adapterList = new AdapterList<>((Activity) getContext(),array_jadwal.getArray_jadwal(),1);
        list_jadwal.setAdapter(adapterList);
    }
}
