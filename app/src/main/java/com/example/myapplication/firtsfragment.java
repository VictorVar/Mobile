package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import static android.content.Context.MODE_PRIVATE;
import static android.widget.Toast.LENGTH_SHORT;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link firtsfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class firtsfragment extends Fragment {
    TextView dato;
    Button button3;
    Button button;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public firtsfragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment firtsfragment.
     */
    // TODO: Rename and change types and number of parameters
    public static firtsfragment newInstance(String param1, String param2) {
        firtsfragment fragment = new firtsfragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View vista= inflater.inflate(R.layout.fragment_firtsfragment, container, false);
        button=vista.findViewById(R.id.button);
        dato= vista.findViewById(R.id.textView9);
        button3=vista.findViewById(R.id.button3);
        button3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getContext(),"Turno canceladooo", LENGTH_SHORT).show();
                return false;
            }
        });
        button3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Deje precionado para cancelar",LENGTH_SHORT).show();
                dato.setText("");
            }
        });
        button.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {

                escanear();
                MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.audio);
                mp.start();
            }
        });
        return vista;
    }
    public void escanear(){
        IntentIntegrator intent= IntentIntegrator.forSupportFragment(firtsfragment.this);
        intent.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intent.setPrompt("Escanear codigo");
        intent.setCameraId(0);
        intent.setOrientationLocked(false);
        intent.setBeepEnabled(false);
        intent.setCaptureActivity(CapturaActivityPortrait.class);
        intent.setBarcodeImageEnabled(false);
        intent.initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result= IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents()== null){
                Toast.makeText(getContext(), "Cancelaste el escaneo", LENGTH_SHORT).show();
            }
        else{
            dato.setText(result.getContents().toString());
        }}else{
            super.onActivityResult(requestCode, resultCode, data);
        }
        }
    }