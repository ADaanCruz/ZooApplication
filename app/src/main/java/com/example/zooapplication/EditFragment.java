package com.example.zooapplication;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zooapplication.Database.SQLite;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class EditFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private TextView tvClasificacion, tvEspecie;
    private Spinner spClasificacion, spEspecie;

    private TextView tvSexo;
    private RadioGroup rgSexo;
    private RadioButton rbMacho, rbHembra;

    private TextView tvId, tvHabitat, tvAlimentacion, tvNombre;
    private EditText etId, etHabitat, etAlimentacion, etNombre;

    private CalendarView cvIngreso;

    private Button bnBuscar, bnGuardar;
    private String ingreso, a, b, sexo;

    private SQLite sqlite;

    public EditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        // Inflate the layout for this fragment
        etId = view.findViewById(R.id.editText_edit_id);

        tvClasificacion = view.findViewById(R.id.textView_edit_clasificacion);
        spClasificacion = view.findViewById(R.id.spinner_edit_clasificacion);

        tvEspecie = view.findViewById(R.id.textView_edit_especie);
        spEspecie = view.findViewById(R.id.spinner_edit_especie);

        tvSexo = view.findViewById(R.id.textView_edit_sexo);
        rgSexo = view.findViewById(R.id.radioGroup_edit_sexo);
        rbMacho = view.findViewById(R.id.radioButton_edit_macho);
        rbHembra = view.findViewById(R.id.radioButton_edit_hembra);

        tvNombre = view.findViewById(R.id.textView_edit_nombre);
        etNombre = view.findViewById(R.id.editText_edit_nombre);

        tvAlimentacion = view.findViewById(R.id.textView_edit_alimentacion);
        etAlimentacion = view.findViewById(R.id.editText_edit_alimentacion);

        tvHabitat = view.findViewById(R.id.textView_edit_habitat);
        etHabitat = view.findViewById(R.id.editText_edit_habitat);

        cvIngreso = view.findViewById(R.id.calendarView_edit_ingreso);

        bnBuscar = view.findViewById(R.id.button_edit_buscar);
        bnGuardar = view.findViewById(R.id.button_edit_guardar);

        tvClasificacion.setVisibility(View.GONE);
        spClasificacion.setVisibility(View.GONE);
        tvEspecie.setVisibility(View.GONE);
        spEspecie.setVisibility(View.GONE);
        tvSexo.setVisibility(View.GONE);
        rgSexo.setVisibility(View.GONE);
        tvNombre.setVisibility(View.GONE);
        etNombre.setVisibility(View.GONE);
        tvHabitat.setVisibility(View.GONE);
        etHabitat.setVisibility(View.GONE);
        tvAlimentacion.setVisibility(View.GONE);
        etAlimentacion.setVisibility(View.GONE);
        cvIngreso.setVisibility(View.GONE);
        bnGuardar.setVisibility(View.GONE);

        sqlite = new SQLite(getContext());
        sqlite.abrir();

        cvIngreso.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int day) {
                ingreso = day + "/" +  (month + 1) + "/" + year;
                Toast.makeText(getContext(), ingreso, Toast.LENGTH_SHORT).show();
            }
        });

        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                Objects.requireNonNull(getContext()),
                R.array.clasificación,
                android.R.layout.simple_spinner_item
        );

        spClasificacion.setAdapter(adapter);
        spClasificacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String opcion = String.valueOf(spClasificacion.getSelectedItemId());
                int opc = Integer.parseInt(opcion);
                System.out.println(opcion);
                if (opc == 0) {
                    final ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                            Objects.requireNonNull(getContext()),
                            R.array.especie0,
                            android.R.layout.simple_spinner_item
                    );
                    spEspecie.setAdapter(adapter1);
                } else if (opc == 1) {
                    final ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                            Objects.requireNonNull(getContext()),
                            R.array.especie1,
                            android.R.layout.simple_spinner_item
                    );
                    spEspecie.setAdapter(adapter1);
                    a = Objects.requireNonNull(adapter.getItem(1)).toString();
                    spEspecie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String opcion = String.valueOf(spEspecie.getSelectedItemId());
                            int opc = Integer.parseInt(opcion);
                            System.out.println(opcion);
                            if (opc == 0) {

                            } else if (opc == 1) {
                                b = Objects.requireNonNull(adapter1.getItem(1)).toString();
                                Toast.makeText(getContext(), a + " " + b, Toast.LENGTH_SHORT).show();
                            } else if (opc == 2) {
                                b = Objects.requireNonNull(adapter1.getItem(2)).toString();
                            } else if (opc == 3) {
                                b = Objects.requireNonNull(adapter1.getItem(3)).toString();
                            } else if (opc == 4) {
                                b = Objects.requireNonNull(adapter1.getItem(4)).toString();
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etId.getText().toString();
                if(!id.isEmpty()) {
                    if (sqlite.getCant(Integer.parseInt(id)).getCount() == 1) {
                        tvClasificacion.setVisibility(View.VISIBLE);
                        spClasificacion.setVisibility(View.VISIBLE);
                        tvEspecie.setVisibility(View.VISIBLE);
                        spEspecie.setVisibility(View.VISIBLE);
                        tvSexo.setVisibility(View.VISIBLE);
                        rgSexo.setVisibility(View.VISIBLE);
                        tvNombre.setVisibility(View.VISIBLE);
                        etNombre.setVisibility(View.VISIBLE);
                        tvHabitat.setVisibility(View.VISIBLE);
                        etHabitat.setVisibility(View.VISIBLE);
                        tvAlimentacion.setVisibility(View.VISIBLE);
                        etAlimentacion.setVisibility(View.VISIBLE);
                        cvIngreso.setVisibility(View.VISIBLE);
                        bnGuardar.setVisibility(View.VISIBLE);
                        int intId = Integer.parseInt(id);
                        Cursor cursor = sqlite.getCant(intId);
                        String g1 = null, g2 = null, g3 = null, g4 = null;
                        if (cursor.moveToFirst()) {
                            do {
                                g1 = cursor.getString(3);
                                g2 = cursor.getString(5);
                                g3 = cursor.getString(6);
                                g4 = cursor.getString(7);
                            } while (cursor.moveToNext());
                        }
                        etNombre.setText(g2);
                        etHabitat.setText(g3);
                        etAlimentacion.setText(g4);
                        ingreso = (g1);
                    } else {
                        Toast.makeText(getContext(), "Error: No existe ese ID", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Error: No has puesto un ID", Toast.LENGTH_SHORT).show();
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
        void onFragmentInteraction(Uri uri);
    }
}
