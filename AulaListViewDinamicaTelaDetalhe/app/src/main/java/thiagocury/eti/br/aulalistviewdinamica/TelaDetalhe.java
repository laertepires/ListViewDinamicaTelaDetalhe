package thiagocury.eti.br.aulalistviewdinamica;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class TelaDetalhe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_detalhe);

        if(getIntent().getSerializableExtra("p") != null){
            Pessoa p = (Pessoa) getIntent().getSerializableExtra("p");
            Toast.makeText(getBaseContext(),"Detalhe: "+p.toString(),Toast.LENGTH_LONG).show();
        }
    }
}
