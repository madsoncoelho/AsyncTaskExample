package com.everis.bootcamp.threading

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO: 018 - fazer o handle do clique do botão
        btnLoadData.setOnClickListener {
            launchAstrosTask()
        }
    }


    //TODO: 013 - Criar função para exibir os dados carregados
    private fun showData(list: List<AstrosPeople>?) {
        tvData.text = ""
        list?.forEach { people ->
            tvData.append("${people.name} - ${people.craft}\n\n")
        }
    }

    //TODO: 014 - Criar função para exibir a ProgressBar
    private fun showLoadingIndicator() {
        progressBar.visibility = View.VISIBLE
    }

    //TODO: 015 - Criar função para esconder a ProgressBar
    private fun hideLoadingIndicator() {
        progressBar.visibility = View.GONE
    }

    //TODO: 017 - Criar função para lançar a Task
    private fun launchAstrosTask() {
        val task = AstrosTask()
        task.execute()
    }


    //TODO: 016 - Criar classe interna para rodar a tarefa assincrona
    inner class AstrosTask(): AsyncTask<Void, Int, List<AstrosPeople>>() {
        private val repository = AstrosRepository()

        override fun onPreExecute() {
            super.onPreExecute()
            showLoadingIndicator()
        }

        override fun doInBackground(vararg params: Void?): List<AstrosPeople> {
            return repository.loadData()
        }

        override fun onPostExecute(result: List<AstrosPeople>?) {
            super.onPostExecute(result)
            hideLoadingIndicator()
            showData(result)
        }
    }

}
