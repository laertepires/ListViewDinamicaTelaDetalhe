package thiagocury.eti.br.aulalistviewdinamica;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class Tela1 extends AppCompatActivity {

    //Widgets
    private EditText etNome;
    private EditText etIdade;
    private Button btnOK;
    //Lista
    private ListView lvPessoas;
    //Adapter
    private ArrayAdapter adapter;
    //Objeto
    private Pessoa p;
    //ArrayList
    private ArrayList<Pessoa> pessoas;

    //posição selecionada
    private int posSelec = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela1);

        //Referencias
        etNome = (EditText) findViewById(R.id.t1_et_nome);
        etIdade = (EditText) findViewById(R.id.t1_et_idade);
        btnOK = (Button) findViewById(R.id.t1_btn_ok);
        lvPessoas = (ListView) findViewById(R.id.t1_lv_pessoas);

        //Instanciar ArrayList
        pessoas = new ArrayList<Pessoa>(); //aqui ele está vazio

        adapter = new ArrayAdapter<Pessoa>(
            Tela1.this,
            android.R.layout.simple_list_item_1,
            pessoas);
        //Setando o adapter na ListView
        lvPessoas.setAdapter(adapter);

        //Clique do botão
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p = new Pessoa();
                p.setNome(etNome.getText().toString());
                p.setIdade(
                    Integer.parseInt(etIdade.getText().toString()));

                //Inserindo objeto dentro do ArrayList
                pessoas.add(p);

                //Notificar o Adapter
                adapter.notifyDataSetChanged();

                Toast.makeText(
                        getBaseContext(),
                        "Pessoa cadastrada com sucesso!",
                        Toast.LENGTH_LONG).show();
            }//fecha classe
        });




        lvPessoas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                posSelec = i;

                //Resgatando objeto                     //posição escolhida
                Pessoa p = (Pessoa) lvPessoas.getItemAtPosition(posSelec);

                Intent it = new Intent(Tela1.this,TelaDetalhe.class);
                it.putExtra("p",p);
                startActivity(it);
            }//fecha onitemclick
        });//fecha onitemclick





        lvPessoas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                posSelec = i;
                AlertDialog.Builder msg = new AlertDialog.Builder(Tela1.this);
                msg.setTitle("afasdf");
                msg.setMessage("Voce tem certeza que deseja excluir?!?!");
                msg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Removendo objeto pessoa!
                        pessoas.remove(posSelec);
                        //Notificar o adapter
                        adapter.notifyDataSetChanged();
                        Toast.makeText(
                                getBaseContext(),
                                "Pessoa removida!",
                                Toast.LENGTH_SHORT).show();
                    }
                });

                msg.setNegativeButton("Nao", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getBaseContext(),"ok, então..",Toast.LENGTH_LONG).show();
                    }
                });
                msg.show();
                return true;
            }
        });

    }//fecha onCreate
}//fecha classe
