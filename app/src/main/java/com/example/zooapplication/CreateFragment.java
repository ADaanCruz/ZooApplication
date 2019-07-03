package com.example.zooapplication;

import android.content.Context;
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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.zooapplication.Database.SQLite;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class CreateFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private EditText etId, etHabitat, etAlimentacion, etNombre;
    private Spinner spClasificacion, spEspecie;
    private RadioGroup rgSexo;
    private CalendarView cvIngreso;
    private RadioButton rbMacho, rbHembra;
    private Button bnGuardar;

    private SQLite sqlite;
    private String a, b, sexo;
    private String ingreso;

    public CreateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create, container, false);
        etId = view.findViewById(R.id.editText_create_id);
        spClasificacion = view.findViewById(R.id.spinner_create_clasificacion);
        spEspecie = view.findViewById(R.id.spinner_create_especie);
        rgSexo = view.findViewById(R.id.radioGroup_create_sexo);
        rbMacho = view.findViewById(R.id.radioButton_create_macho);
        rbHembra = view.findViewById(R.id.radioButton_create_hembra);
        cvIngreso = view.findViewById(R.id.calendarView_create_ingreso);
        etHabitat = view.findViewById(R.id.editText_create_habitat);
        etAlimentacion = view.findViewById(R.id.editText_create_alimentacion);
        etNombre = view.findViewById(R.id.editText_create_nombre);
        bnGuardar = view.findViewById(R.id.button_create_guardar);

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
                System.out.println(opc);
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
                            System.out.println(opc);
                            if (opc == 1) {
                                b = Objects.requireNonNull(adapter1.getItem(1)).toString();
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
                } else if (opc == 2) {
                    final ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                            Objects.requireNonNull(getContext()),
                            R.array.especie2,
                            android.R.layout.simple_spinner_item
                    );
                    spEspecie.setAdapter(adapter2);
                    a = Objects.requireNonNull(adapter.getItem(2)).toString();
                    spEspecie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String opcion = String.valueOf(spEspecie.getSelectedItemId());
                            int opc = Integer.parseInt(opcion);
                            System.out.println(opc);
                            if (opc == 1) {
                                b = Objects.requireNonNull(adapter2.getItem(1)).toString();
                            } else if (opc == 2) {
                                b = Objects.requireNonNull(adapter2.getItem(2)).toString();
                            } else if (opc == 3) {
                                b = Objects.requireNonNull(adapter2.getItem(3)).toString();
                            } else if (opc == 4) {
                                b = Objects.requireNonNull(adapter2.getItem(4)).toString();
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                } else if (opc == 3) {
                    final ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                            Objects.requireNonNull(getContext()),
                            R.array.especie3,
                            android.R.layout.simple_spinner_item
                    );
                    spEspecie.setAdapter(adapter2);
                    a = Objects.requireNonNull(adapter.getItem(3)).toString();
                    spEspecie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String opcion = String.valueOf(spEspecie.getSelectedItemId());
                            int opc = Integer.parseInt(opcion);
                            System.out.println(opc);
                            if (opc == 1) {
                                b = Objects.requireNonNull(adapter2.getItem(1)).toString();
                            } else if (opc == 2) {
                                b = Objects.requireNonNull(adapter2.getItem(2)).toString();
                            } else if (opc == 3) {
                                b = Objects.requireNonNull(adapter2.getItem(3)).toString();
                            } else if (opc == 4) {
                                b = Objects.requireNonNull(adapter2.getItem(4)).toString();
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

        rbMacho.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rbMacho.isChecked()) {
                    sexo = "MACHO";
                }
            }
        });

        rbHembra.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rbMacho.isChecked()) {
                    sexo = "HEMBRA";
                }
            }
        });

        bnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etId.getText().toString(),
                        alimentacion = etAlimentacion.getText().toString(),
                        nombre = etNombre.getText().toString(),
                        habitat = etHabitat.getText().toString();
                if (
                    !id.isEmpty() &&
                    !alimentacion.isEmpty() &&
                    !nombre.isEmpty() &&
                    !habitat.isEmpty() &&
                    !ingreso.isEmpty() &&
                    !a.isEmpty() &&
                    !b.isEmpty() &&
                    !sexo.isEmpty()
                ) {
                    Toast.makeText(
                            getContext(),
                            nombre.toUpperCase() + " " +
                            sexo + " " +
                            ingreso + " " +
                            habitat.toUpperCase() + " " +
                            alimentacion.toUpperCase(),
                            Toast.LENGTH_SHORT).show();
                    if (sqlite.addRegistroAnimal(Integer.parseInt(id),
                            a,
                            b,
                            nombre.toUpperCase(),
                            sexo,
                            ingreso,
                            habitat.toUpperCase(),
                            alimentacion.toUpperCase())
                    ) {
                        Toast.makeText(getContext(), "Registro añadido", Toast.LENGTH_SHORT).show();
                        etId.setText("");
                        etNombre.setText("");
                        ingreso = "";
                        etHabitat.setText("");
                        etAlimentacion.setText("");
                        spClasificacion.setId(0);
                        spEspecie.setId(0);
                        rbMacho.setChecked(false);
                        rbHembra.setChecked(false);
                    }
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
