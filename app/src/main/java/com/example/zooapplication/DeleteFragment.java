package com.example.zooapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zooapplication.Database.SQLite;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DeleteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class DeleteFragment extends Fragment {

    private Button bnEliminar;
    private SQLite sqlite;
    EditText etId;

    private OnFragmentInteractionListener mListener;

    public DeleteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete, container, false);
        // Inflate the layout for this fragment

        bnEliminar = view.findViewById(R.id.button_list_eliminar);
        etId = view.findViewById(R.id.editText_list_id);

        bnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlite = new SQLite(getContext());
                sqlite.abrir();
                System.out.println("Entraste");
                String id = etId.getText().toString();
                if (!id.isEmpty()) {
                    int query = sqlite.Eliminar(etId.getText());
                    if (query == (-1)) {
                        Toast.makeText(getContext(),"Ocurrió un error", Toast.LENGTH_SHORT).show();
                    } else if (query == 1) {
                        Toast.makeText(getContext(), "Registro eliminado", Toast.LENGTH_SHORT).show();
                    } else if (query == 0){
                        Toast.makeText(getContext(), "No se encontró el registro", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Ingrese el campo ID", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
