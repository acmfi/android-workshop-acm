package es.upm.fi.asoc.acm.contador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta es la segunda activity, que se utilizará para cambiar directamente a un número de una lista
 * de números. Quizás te estés preguntando que una lista de números no es la mejor manera de hacerlo.
 * Si ese es el caso, tienes razón es una mierdecilla de comportamiento y cualquier UX que se precie
 * lo tiraría por un retrete. Pero nosotros estamos aqui para aprender y hacer listas en Android es
 * algo que se DEBE aprender. Piensa en todas las apps que sueles utilizar, ¿cuántas son, básicamente,
 * listas? Pos eso. Al lío.
 */
public class ListNumber extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        //Como siempre, hay que tener en cuenta el ciclo de vida de Android y añadir el inflado del
        //xml de la vista en el onCreate.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_number);

        ArrayList<Integer> numbers = new ArrayList<>(); //Creamos un array donde tendremos nuestros números

        //Rellenamos el array con 100 números
        for (Integer i = 0; i <=100; i++) {
            numbers.add(i);
        }

        final ListView list = (ListView) findViewById(R.id.listView);//Cogemos el widget de la vista con el método habitual

        //Las listas en Android se crean con el patrón adapter. Tenemos los datos por un lado y por otro los datos.
        //El adapter es una clase intermedia que adapta los datos al formato que necesite la vista.
        //Así que creamos un adapter ArrayAdapter que ya nos ofrece el sistema.
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(
                this, //El contexto, que es esta misma activity
                android.R.layout.simple_list_item_1, //El patrón al que se adaptarán los datos, en este caso un simple textview
                numbers); //La lista de datos.
        list.setAdapter(adapter); //Se añade el adapter al widget
        //Se crea un listener para capturar el evento de click en cada item.
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Integer selectedNumber = (Integer) list.getItemAtPosition(position); //Tomamos el elemento en la posición seleccionada, será un Integer.
                //Creamos un intent para volver a la vista anterior pero con el número seleccionado como extra.
                Intent i = new Intent(ListNumber.this, MainActivity.class);
                i.putExtra(MainActivity.NEW_NUMBER, selectedNumber);
                startActivity(i);
            }
        });
    }
}
