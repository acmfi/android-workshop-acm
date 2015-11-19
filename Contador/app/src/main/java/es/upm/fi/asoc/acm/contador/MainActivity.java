package es.upm.fi.asoc.acm.contador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Esta es la clase principal (porque tiene los intent-filter adecuados en el AndroidManifest.xml.
 * Cuando se ejecute la app el sdk mandará un intent a esta clase y se ejecutará el método onCreate().
 * Recordad el ciclo de vida de la aplicación, cómo se van ejecutando distintos métodos que debemos
 * sobreescribir para crear la inteligencia de nuestra app.
 */

/**
 * AppCompatActivity es la clase Activity que tiene retrocompatibilidad. Si queremos utilizar una
 * interfaz en nuestro código debemos implementarlo con implements. En este caso se implementa un
 * OnClickListener que usaremos para el botón reset.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Nombramos los atributos de clase
    public static final String NEW_NUMBER = "number"; //Una constante string estática que usaremos para recibir el extra del intent.
    private Integer currentNumber;//Nuestra variable con el modelo de la aplicación, simplemente tendrá el número al que hayamos llegado.
    private TextView tvNumber;//Nuestro objeto Textview que usaremos para mostrar el número del modelo en pantalla.

    /**
     * Se ejecuta el método onCreate y allí controlaremos nuestra vista.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //Llamada a super para que haga lo que necesite

        /**
         * La primera llamada a este método será vacía pero también podrá llamarse desde la segunda
         * activity y esta llamada enviará un Integer como extra en el Intent. Hay que recuperarlo
         * si existe e inicializar el módelo con esa cifra, si no hay extra, se inicia a 0.
         */
        if(getIntent().hasExtra(NEW_NUMBER)){
            currentNumber = getIntent().getIntExtra(NEW_NUMBER,0);//Cogemos el extra, si no hubiese nada sería 0, un método de control del extra.
        } else {
            currentNumber = 0;
        }

        //Se infla la vista, en activity_main.xml
        setContentView(R.layout.activity_main);

        /**
         * Ahora es cuando controlamos todos los widgets de la vista. Se debe hacer buscando cada
         * widget por su identificador, que creamos en el parámetro android:id. Hay que tener en
         * cuenta que al buscar por id se devuelve un objeto View (más genérico) debemos hacer un
         * casting al objeto que necesitemos en cada momento.
         */

        //Capturamos el objeto textview donde irá el número. Recordad que esta variable es de clase.
        tvNumber = (TextView) findViewById(R.id.number);
        //Indicamos que el texto de la variable será igual al número de currentNumber que podrá ser 0 u otro número.
        //Para ello utilizamos el método privado changeNumber que, como se puede ver más abajo, cambia el valor del texto.
        changeNumber(currentNumber);
        //Añadimos un onClickListener para que, al hacer click en el número nos lleve a la lista de números para saltar a esa cifra sin ir una a una.
        //Se utiliza el método de añadir listener creando un nuevo objeto de la interfaz Listener e implementando su método onClick.
        tvNumber.setOnClickListener(new View.OnClickListener() {
            /**
             * Este método crea un nuevo intent para la clase ListNumber y lo inicia.
             * @param view
             */
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ListNumber.class); //Creación de Intent
                startActivity(i);//Inicia el intent, se irá hacia la nueva activity.
            }
        });

        //Capturamos el objeto Button que es el botón de disminuir una cifra.
        Button btnBack = (Button) findViewById(R.id.back);
        //Para controlar el click de este botón utilizamos el método de la llamada directa desde el xml. Al hacer click aquí se ejecuta onBackClick.

        //Capturamos el objeto Button que es el botón de aumentar una cifra.
        Button btnMore = (Button) findViewById(R.id.more);
        //Utilizamos el mismo método para añadir listener que en el caso del textview.
        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentNumber<100){
                    currentNumber = currentNumber + 1;
                    changeNumber(currentNumber);
                } else {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.more_toast), Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button btnReset = (Button) findViewById(R.id.reset);
        btnReset.setOnClickListener(this);
    }

    public void onBackClick(View view) {
        if(currentNumber>0){
            currentNumber = currentNumber - 1;
            changeNumber(currentNumber);
        } else {
            Toast.makeText(MainActivity.this, "Fin", Toast.LENGTH_SHORT).show();
        }
    }

    private void changeNumber(Integer newNumber){
        tvNumber.setText(newNumber.toString());
    }

    @Override
    public void onClick(View view) {
        currentNumber = 0;
        changeNumber(0);
    }
}
