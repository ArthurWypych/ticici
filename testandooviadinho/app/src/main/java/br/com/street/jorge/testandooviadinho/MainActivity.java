package br.com.street.jorge.testandooviadinho;


import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ClickRecyclerView_Interface {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager garotogay;
    adapter adapter;
    private List<Model> listamodelo = new ArrayList<>();
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setaRecyclerView();

        setaButtons();
        listenersButtons();
        Model viadinho = new Model();
        viadinho.setviado("prostituta");

        //Adiciona a pessoa1 e avisa o adapter que o conteúdo
        //da lista foi alterado
        listamodelo.add(viadinho);
        adapter.notifyDataSetChanged();


        //Constraint = (ConstraintLayout) findViewById(R.id.putaria);

    }

    //Constraint = (ConstraintLayout) findViewById(R.id.ConstraintLayout);

    public void setaRecyclerView(){

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        garotogay = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(garotogay);


        adapter = new adapter(this, listamodelo, this);
        mRecyclerView.setAdapter(adapter);
    }

    public void setaButtons(){
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
    }
    @Override
    public void onCustomClick(Object object) {
        Model viadinho = (Model) object;
        String gay = viadinho.getviado();
    }

    public void listenersButtons() {

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Cria uma nova pessoa chamada Renan Teles
                Model viadinho = new Model();
                viadinho.setviado("prostituta");

                //Adiciona a pessoa1 e avisa o adapter que o conteúdo
                //da lista foi alterado
                listamodelo.add(viadinho);
                adapter.notifyDataSetChanged();

            }
        });
    }

}
